package com.atmj.gsboot.services.changemachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.AwardsChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineTotalEntity;
import com.atmj.gsboot.dbaccess.entities.CollectionEntity;
import com.atmj.gsboot.dbaccess.entities.MachineEntity;
import com.atmj.gsboot.dbaccess.entities.PaymentEntity;
import com.atmj.gsboot.dbaccess.entities.TPVEntity;
import com.atmj.gsboot.dbaccess.entities.TakeEntity;
import com.atmj.gsboot.dbaccess.repositories.ChangeMachineRepository;
import com.atmj.gsboot.dbaccess.repositories.ChangeMachineTotalRepository;
import com.atmj.gsboot.dbaccess.repositories.MachinesRepository;
import com.atmj.gsboot.dbaccess.repositories.TPVRepository;
import com.atmj.gsboot.dbaccess.repositories.TakingsRepository;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.date.DateUtil;
import com.atmj.gsboot.util.string.Util;
import com.atmj.services.EmailService;
import com.atmj.services.MetadataService;

@Service
public class ChangeMachineServiceImpl implements ChangeMachineService {

	private final EmailService emailService;

	private final MetadataService metadataservice;

	private final ChangeMachineRepository changeMachineRepository;

	private final ChangeMachineTotalRepository changeMachineTotalRepository;

	private final MachinesRepository machinesRepository;

	private final TakingsRepository takingsRepository;

	private final TPVRepository tpvrepository;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ChangeMachineServiceImpl.class);

	private static final String BASIC = "Basic ";

	public ChangeMachineServiceImpl(EmailService emailService, MetadataService metadataservice,
			ChangeMachineRepository changeMachineRepository, ChangeMachineTotalRepository changeMachineTotalRepository,
			MachinesRepository machinesRepository, TakingsRepository takingsRepository, TPVRepository tpvrepository) {
		this.emailService = emailService;
		this.metadataservice = metadataservice;
		this.changeMachineRepository = changeMachineRepository;
		this.changeMachineTotalRepository = changeMachineTotalRepository;
		this.machinesRepository = machinesRepository;
		this.takingsRepository = takingsRepository;
		this.tpvrepository = tpvrepository;
	}

	@Override
	public void reset(String sdate) {
		TakeEntity take = new TakeEntity();
		Date now;
		if (Util.isEmpty(sdate)) {
			now = new DateUtil().getNow();
		} else {
			now = DateUtil.getDate(sdate);
		}
		take.setTakedate(now);
		takingsRepository.save(take);
	}

	@Override
	public ChangeMachineTotalEntity getTotal() {
		return changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
	}

	@Override
	public BigDecimal getAwards() {
		BigDecimal awards = BigDecimal.ZERO;
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(Constants.CHANGEMACHINE);
		Date takedate = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		BigDecimal tpvs = tpvrepository.sumByCreationdateAndPayment(pay, takedate, new DateUtil().getNow());
		BigDecimal awardscm = changeMachineRepository.sumByCreationdateBetween(takedate, new DateUtil().getNow());
		if (tpvs != null) {
			awards = tpvs;
		}
		if (awardscm != null) {
			awards = awards.add(awardscm);
		}
		return awards;
	}

	/**
	 * Dinero invertido en la máquina de cambio
	 */
	@Override
	public BigDecimal getIncomeTotalMonth() {
		return changeMachineRepository.sumIncomeBetweenDates(
				takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new DateUtil().getNow());
	}

	@Override
	public ChangeMachineEntity findById(Long idchangemachine) {
		return changeMachineRepository.findById(idchangemachine).orElse(null);
	}

	@Override
	public void loadDataTicketServer() {
		/**
		 * Busco la última fecha de recaudación
		 */
		Date from = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(getConnectionReportTicketsDate(from).getInputStream()))) {
			readFileReportTicketsDate(in);
		} catch (IOException | URISyntaxException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		} finally {
			logger.debug(java.util.logging.Level.SEVERE.getName());
		}
		try (BufferedReader in = new BufferedReader(new InputStreamReader(getConnectionEvents().getInputStream()))) {
			readFileEvents(in);
		} catch (IOException | URISyntaxException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		} finally {
			logger.debug(java.util.logging.Level.SEVERE.getName());
		}
	}

	private void readFileEvents(BufferedReader in) {
		String response;
		String result = "";
		try {
			for (int i = 0; i < 10; i++)
				while ((response = in.readLine()) != null)
					result = result.concat(response);
			in.close();
			loadDataEvents(result);
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}

	}

	private void loadDataEvents(String result) {
		ChangeMachineTotalEntity cmt = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity entity;
		Date from = cmt.getCreationdate();
		Document doc = Jsoup.parse(result);
		Elements trs = doc.getElementsByClass("tbl-row");
		ListIterator<Element> itrs = trs.listIterator();
		List<Node> nodes;
		TextNode node;
		Date date;
		String type;
		String text;
		String[] split;
		Element element;
		BigDecimal amount;
		while (itrs.hasNext()) {
			nodes = itrs.next().childNodes();
			node = (TextNode) nodes.get(0).childNode(0);
			// cuidado viene con milisegundos
			date = DateUtil.getDate(node.getWholeText());
			if (date.after(from)) {
				element = (Element) nodes.get(1);
				element = (Element) element.childNodes().get(0);
				node = (TextNode) element.childNodes().get(0);
				type = node.getWholeText();
				if (type.equals("Recargas por apuestas")) {
					node = (TextNode) nodes.get(2).childNode(0);
					text = node.getWholeText();
					split = text.split(":");
					amount = new BigDecimal(split[split.length - 1]);
					entity = new ChangeMachineTotalEntity();
					entity.setVisible(cmt.getVisible().add(amount));
					entity.setDeposit(cmt.getDeposit());
					entity.setCreationdate(new Date());
					changeMachineTotalRepository.save(entity);
				}
			}
		}
	}

	private URLConnection getConnectionEvents() throws IOException, URISyntaxException {
		String ip = metadataservice.getValue("ipgoldenusera");
		String address = "http://".concat(ip.concat(":3080/TicketServer/listLogs.php?"));
		String restaddress = "&User=root&Pass".concat("word=ccm10");
		String name = "ccm";
		String pass = "ccm10";
		String authString = name + ":" + pass;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		address = address.concat(restaddress);
		URL url = new URI(address).toURL();
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", BASIC.concat(authStringEnc));
		return connection;
	}

	/**
	 * Monto la url con la última fecha de recaudación hasta este mismo instante
	 * 
	 * @param from
	 * @return URLConnection
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private URLConnection getConnectionReportTicketsDate(Date from) throws IOException, URISyntaxException {
		String ip = metadataservice.getValue("ipgoldenusera");
		String address = "http://".concat(ip.concat(":3080/TicketServer/reportTicketsDateTime.php?"));
		String startdate = "StartDate=";
		String endate = "&EndDate=";
		String space = "%20";
		String restaddress = "&User=root&Pass".concat("word=ccm10");
		String name = "ccm";
		String pass = "ccm10";
		String authString = name + ":" + pass;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		address = address.concat(startdate).concat(DateUtil.getStringDateFormatyyyyMMdd(from)).concat(space)
				.concat(DateUtil.getStringDateFormatHHmm(from)).concat(endate)
				.concat(DateUtil.getStringDateFormatyyyyMMdd(new DateUtil().getNow())).concat(space)
				.concat(DateUtil.getStringDateFormatHHmm(new DateUtil().getNow())).concat(restaddress);
		URL url = new URI(address).toURL();
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", BASIC.concat(authStringEnc));
		return connection;
	}

	private void readFileReportTicketsDate(BufferedReader in) {
		String response;
		String result = "";
		try {
			for (int i = 0; i < 10; i++)
				while ((response = in.readLine()) != null)
					result = result.concat(response);
			in.close();
			loadData(result);
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
	}

	private void loadData(String result) {
		String award;
		String samount;
		Document doc = Jsoup.parse(result);
		Elements trs = doc.getElementsByClass("tbl-row");
		ListIterator<Element> itrs = trs.listIterator();
		List<Node> nodes;
		TextNode node;
		Date date;
		BigDecimal amount;
		String scomments;
		String ip;
		Long idtpv;
		while (itrs.hasNext()) {
			nodes = itrs.next().childNodes();
			if (((TextNode) nodes.get(6).childNode(0)).getWholeText().equals("CLOSE")) {
				node = (TextNode) nodes.get(3).childNode(0);
				award = node.getWholeText();
				node = (TextNode) nodes.get(7).childNode(0);
				date = DateUtil.getDate(node.getWholeText());
				node = (TextNode) nodes.get(9).childNode(0);
				ip = node.getWholeText();
				node = (TextNode) nodes.get(10).childNode(0);
				samount = node.getWholeText();
				amount = new BigDecimal(samount.substring(0, samount.length() - 1).replaceFirst(",", ""));
				node = (TextNode) nodes.get(11).childNode(0);
				scomments = node.getWholeText();
				if (award.equals(Constants.sTPV) && Util.isNumeric(scomments)) {
					idtpv = Long.valueOf(scomments);
					loadTPV(ip, date, amount, idtpv);
				} else {
					node = (TextNode) nodes.get(2).childNode(0);
					Long id = Long.valueOf(node.getWholeText());
					if (!changeMachineRepository.existsById(id)) {
						loadChangeMachineEntity(ip, award, scomments, date, id, amount);
					}
				}
			}
		}
		compareTotal(doc);
	}

	private void loadTPV(String ip, Date date, BigDecimal amount, Long idtpv) {
		if (!tpvrepository.existsById(idtpv)) {
			TPVEntity tpv = new TPVEntity();
			PaymentEntity pay = new PaymentEntity();
			pay.setIdpayment(Constants.MAQUINACAMBIO);
			tpv.setPay(pay);
			tpv.setCreationdate(date);
			tpv.setAmount(amount);
			tpv.setIdtpv(idtpv);
			tpvrepository.save(tpv);
			subtractChangeMachineTotal(ip, amount);
		}
	}

	private void compareTotal(Document doc) {
		String totaltext = doc.text();
		totaltext = totaltext.substring(totaltext.indexOf("Total = "));
		char[] array = totaltext.toCharArray();
		char[] carray = new char[9];
		int c = 0;
		boolean finalized = false;
		for (int i = 8; i < totaltext.length() && !finalized; i++) {
			if (Character.isDigit(array[i])) {
				carray[c] = array[i];
				c++;
			} else if (array[i] == '.') {
				carray[c] = array[i];
				carray[c + 1] = array[i + 1];
				carray[c + 2] = array[i + 2];
				finalized = true;
			}
		}
		String sarray = String.valueOf(carray).trim();
		BigDecimal total = new BigDecimal(sarray);
		BigDecimal totalbbdd = getAwards();
		if (total.compareTo(totalbbdd) != 0) {
			emailService.sendSimpleMessage("mangeles.sanchez0807@gmail.com", "NO COINCIDEN PREMIOS TICKET SERVER",
					"No coinciden premios dados");
		}
	}

	private void loadChangeMachineEntity(String ip, String award, String scomments, Date date, Long id,
			BigDecimal amount) {
		AwardsChangeMachineEntity awardentity = new AwardsChangeMachineEntity();
		MachineEntity machine;
		ChangeMachineEntity cm = new ChangeMachineEntity();
		cm.setIdchangemachine(id);
		machine = machinesRepository.findByNameticket(award);
		if (machine == null) {
			if (award.equals("TECNAUSA")) {
				awardentity.setIdawardchangemachine(2L);
				String sub = scomments.substring(1, 4);
				machine = new MachineEntity();
				if (Util.isNumeric(sub)) {
					machine.setOrdermachine(Long.valueOf(sub));
				} else {
					sub = scomments.substring(1, 3);
					if (Util.isNumeric(sub)) {
						machine.setOrdermachine(Long.valueOf(sub));
					} else {
						machine.setOrdermachine(Long.valueOf(scomments.substring(1, 2)));
					}
				}
				machine = machinesRepository.findByOrdermachineAndOnoffTrue(machine.getOrdermachine());
			} else if (award.equals("RECARGAS")) {
				awardentity.setIdawardchangemachine(3L);
				machine = machinesRepository.findByNameticket(scomments);
			} else {
				awardentity.setIdawardchangemachine(1L);
				machine = machinesRepository.findByNameticket(scomments);
			}
		} else {
			awardentity.setIdawardchangemachine(1L);
		}
		cm.setMachine(machine);
		cm.setAward(awardentity);
		cm.setCreationdate(date);
		cm.setAmount(amount);
		changeMachineRepository.save(cm);
		subtractChangeMachineTotal(ip, amount);
	}

	@Override
	public void subtractChangeMachineTotal(String ip, BigDecimal amount) {
		if (Constants.LOCALHOST.equals(ip)) {
			ChangeMachineTotalEntity total = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
			total.setVisible(total.getVisible().subtract(amount));
			changeMachineTotalRepository.save(total);
		}
	}

	@Override
	public List<ChangeMachineEntity> getOperationsTicketServer(Date date) {
		return changeMachineRepository.searchByCreationdate(date);
	}

	@Override
	public List<ChangeMachineEntity> getOperationsTicketServerBetweenDates(Date from, Date until) {
		return changeMachineRepository
				.findByAwardIsNotNullAndMachineIsNotNullAndCreationdateBetweenOrderByCreationdate(from, until);
	}

	@Override
	public List<ChangeMachineEntity> recharges(Date from, Date until) {
		AwardsChangeMachineEntity pay = new AwardsChangeMachineEntity();
		pay.setIdawardchangemachine(Constants.RECHARGES);
		return changeMachineRepository.findByAwardAndCreationdateBetween(pay, from, until);
	}

	@Override
	public void entryToVisible(BigDecimal amount) {
		ChangeMachineTotalEntity entity = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity newTotal = new ChangeMachineTotalEntity();
		newTotal.setDeposit(entity.getDeposit().subtract(amount));
		newTotal.setVisible(entity.getVisible().add(amount));
		newTotal.setCreationdate(new Date());
		changeMachineTotalRepository.save(newTotal);
	}

	@Override
	public List<CollectionEntity> manualpayments(Long idtake) {
		List<CollectionEntity> collection = null;
		TakeEntity take = takingsRepository.findById(idtake).orElse(new TakeEntity());
		TakeEntity takeEntity = takingsRepository.findById(idtake - 1L).orElse(null);
		List<Long> awards = new ArrayList<>();
		awards.add(1L);
		awards.add(4L);
		if (takeEntity != null) {
			collection = new ArrayList<>();
			CollectionEntity c;
			List<Object[]> objects = changeMachineRepository.sumByCreationdateBetweenAndAward(take.getTakedate(),
					takeEntity.getTakedate(), awards);
			Iterator<Object[]> iobjects = objects.iterator();
			Object[] o;
			while (iobjects.hasNext()) {
				o = iobjects.next();
				c = new CollectionEntity();
				c.setMachine((MachineEntity) o[0]);
				c.setAmount((BigDecimal) o[1]);
				collection.add(c);
			}
		}
		return collection;
	}

	@Override
	public Iterable<TakeEntity> getAllTakings() {
		return takingsRepository.findAll();
	}
}
