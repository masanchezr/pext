package com.sboot.golden.services.changemachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.AwardsChangeMachineEntity;
import com.sboot.golden.dbaccess.entities.ChangeMachineEntity;
import com.sboot.golden.dbaccess.entities.ChangeMachineTotalEntity;
import com.sboot.golden.dbaccess.entities.CollectionEntity;
import com.sboot.golden.dbaccess.entities.MachineEntity;
import com.sboot.golden.dbaccess.entities.PaymentEntity;
import com.sboot.golden.dbaccess.entities.TPVEntity;
import com.sboot.golden.dbaccess.entities.TakeEntity;
import com.sboot.golden.dbaccess.repositories.ChangeMachineRepository;
import com.sboot.golden.dbaccess.repositories.ChangeMachineTotalRepository;
import com.sboot.golden.dbaccess.repositories.MachinesRepository;
import com.sboot.golden.dbaccess.repositories.TPVRepository;
import com.sboot.golden.dbaccess.repositories.TakingsRepository;
import com.sboot.golden.services.dailies.Daily;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.services.mails.EmailService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.date.DateUtil;
import com.sboot.golden.util.string.Util;

@Service
public class ChangeMachineServiceImpl implements ChangeMachineService {

	@Autowired
	private DailyService dailyservice;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineTotalRepository changeMachineTotalRepository;

	@Autowired
	private MachinesRepository machinesRepository;

	@Autowired
	private TakingsRepository takingsRepository;

	@Autowired
	private TPVRepository tpvrepository;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ChangeMachineServiceImpl.class);

	private static final String BASIC = "Basic ";

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
	public Daily save(ChangeMachineEntity cm) {
		Date today = new DateUtil().getNow();
		cm.setCreationdate(today);
		changeMachineRepository.save(cm);
		return dailyservice.getDailyEmployee();
	}

	@Override
	public void loadDataTicketServer() {
		Date from = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(getConnection(from).getInputStream()))) {
			readFile(in);
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		} finally {
			logger.debug(java.util.logging.Level.SEVERE.getName());
		}
	}

	private URLConnection getConnection(Date from) throws IOException {
		String address = "http://"
				.concat(System.getenv(Constants.IPGOLDEN).concat(":3080/TicketServer/reportTicketsDateTime.php?"));
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
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", BASIC.concat(authStringEnc));
		return connection;
	}

	private void readFile(BufferedReader in) {
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
				amount = new BigDecimal(samount.substring(0, samount.length() - 3).replaceFirst(",", ""));
				node = (TextNode) nodes.get(11).childNode(0);
				scomments = node.getWholeText();
				if (award.equals("TPV") && Util.isNumeric(scomments)) {
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
		if (!tpvrepository.findById(idtpv).isPresent()) {
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
					machine.setIdmachine(Long.valueOf(sub));
				} else {
					sub = scomments.substring(1, 3);
					if (Util.isNumeric(sub)) {
						machine.setIdmachine(Long.valueOf(sub));
					} else {
						machine.setIdmachine(Long.valueOf(scomments.substring(1, 2)));
					}
				}
				cm.setMachine(machine);
			} else if (award.equals("RECARGAS")) {
				awardentity.setIdawardchangemachine(3L);
				machine = machinesRepository.findByNameticket(scomments);
				cm.setMachine(machine);
			} else {
				awardentity.setIdawardchangemachine(1L);
				machine = machinesRepository.findByNameticket(scomments);
				cm.setMachine(machine);
			}
		} else {
			awardentity.setIdawardchangemachine(1L);
			cm.setMachine(machine);
		}
		cm.setAward(awardentity);
		cm.setCreationdate(date);
		cm.setAmount(amount);
		changeMachineRepository.save(cm);
		subtractChangeMachineTotal(ip, amount);
	}

	@Override
	public void subtractChangeMachineTotal(String ip, BigDecimal amount) {
		if ("127.0.0.1".equals(ip)) {
			ChangeMachineTotalEntity totalentity = changeMachineTotalRepository
					.findFirstByOrderByIdchangemachinetotalDesc();
			ChangeMachineTotalEntity total = new ChangeMachineTotalEntity();
			total.setVisible(totalentity.getVisible().subtract(amount));
			total.setCreationdate(new Date());
			total.setDeposit(totalentity.getDeposit());
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
	public List<ChangeMachineEntity> recharges(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		AwardsChangeMachineEntity pay = new AwardsChangeMachineEntity();
		pay.setIdawardchangemachine(Constants.RECHARGES);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return changeMachineRepository.findByAwardAndCreationdateBetween(pay, from, until);
	}

	@Override
	public void entryToVisible(BigDecimal amount) {
		ChangeMachineTotalEntity last = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity entity = new ChangeMachineTotalEntity();
		entity.setCreationdate(new DateUtil().getNow());
		entity.setDeposit(last.getDeposit().subtract(amount));
		entity.setVisible(last.getVisible().add(amount));
		changeMachineTotalRepository.save(entity);
	}

	@Override
	public List<CollectionEntity> manualpayments(Long idtake) {
		List<CollectionEntity> collection = null;
		TakeEntity take = takingsRepository.findById(idtake).orElse(new TakeEntity());
		TakeEntity takeEntity = takingsRepository.findById(idtake + 1L).orElse(null);
		if (takeEntity != null) {
			collection = new ArrayList<>();
			CollectionEntity c;
			Date until = takeEntity.getTakedate();
			List<Object[]> objects = changeMachineRepository.sumByCreationdateBetweenAndAward(take.getTakedate(),
					until);
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
