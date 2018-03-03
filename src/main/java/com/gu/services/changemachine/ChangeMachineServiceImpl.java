package com.gu.services.changemachine;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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

import com.gu.dbaccess.entities.AwardsChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.entities.PaymentEntity;
import com.gu.dbaccess.entities.TPVEntity;
import com.gu.dbaccess.entities.TakeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.MachinesRepository;
import com.gu.dbaccess.repositories.TPVRepository;
import com.gu.dbaccess.repositories.TakingsRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;
import com.gu.util.string.Util;

public class ChangeMachineServiceImpl implements ChangeMachineService {

	@Autowired
	private DailyService dailyservice;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineTotalRepository changeMachineTotalRepository;

	@Autowired
	private TakingsRepository takingsRepository;

	@Autowired
	private MachinesRepository machinesRepository;

	@Autowired
	private TPVRepository tpvrepository;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ChangeMachineServiceImpl.class);

	public BigDecimal getIncomeTotalMonth() {
		return changeMachineRepository
				.sumIncomeBetweenDates(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
	}

	public void reset() {
		Date from = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		TakeEntity take = new TakeEntity();
		Date now = new Date();
		ChangeMachineTotalEntity cmt = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity cmtn = new ChangeMachineTotalEntity();
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(Constants.CHANGEMACHINE);
		take.setTakedate(now);
		cmtn.setCreationdate(now);
		cmtn.setTotal(cmt.getTotal().subtract(changeMachineRepository.sumByCreationdateBetween(from, now))
				.subtract(tpvrepository.sumByCreationdateAndPayment(pay, from, now)));
		changeMachineTotalRepository.save(cmtn);
		takingsRepository.save(take);
	}

	public BigDecimal getTotal() {
		return changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc().getTotal();
	}

	public BigDecimal getAwards() {
		BigDecimal awards = BigDecimal.ZERO;
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(Constants.CHANGEMACHINE);
		Date takedate = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		BigDecimal tpvs = tpvrepository.sumByCreationdateAndPayment(pay, takedate, new Date());
		BigDecimal awardscm = changeMachineRepository.sumByCreationdateBetween(takedate, new Date());
		if (tpvs != null) {
			awards = tpvs;
		}
		if (awardscm != null) {
			awards = awards.add(awardscm);
		}
		return awards;
	}

	public Map<String, Object> ticketsByDay(Date date) {
		Map<String, Object> map = null;
		BigDecimal amount = BigDecimal.ZERO;
		List<ChangeMachineEntity> lcm = changeMachineRepository.findByCreationdate(date);
		if (lcm != null && !lcm.isEmpty()) {
			Iterator<ChangeMachineEntity> ilcm = lcm.iterator();
			while (ilcm.hasNext()) {
				amount = amount.add(ilcm.next().getAmount());
			}
			map = new HashMap<>();
			map.put(ConstantsJsp.OPERATIONS, lcm);
			map.put(Constants.AMOUNT, amount);
		}
		return map;
	}

	public ChangeMachineEntity findById(Long idchangemachine) {
		return changeMachineRepository.findById(idchangemachine).orElse(null);
	}

	public Daily save(ChangeMachineEntity cm) {
		Date today = new Date();
		cm.setCreationdate(today);
		changeMachineRepository.save(cm);
		return dailyservice.getDailyEmployee(today);
	}

	public void loadDataTicketServer() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(getConnection().getInputStream()))) {
			readFile(in);
		} catch (IOException e) {
			logger.error("No se pueden obtener los datos");
		} finally {
			logger.debug("Fin del método");
		}
	}

	private URLConnection getConnection() throws IOException {
		ChangeMachineEntity cmentity = changeMachineRepository.findFirstByOrderByCreationdateDesc();
		String address = "http://88.27.244.77:3080/TicketServer/reportTicketsDateTime.php?";
		String startdate = "StartDate=";
		String endate = "&EndDate=";
		String space = "%20";
		String restaddress = "&User=admin&Pass".concat("word=1234");
		String name = "ccm";
		String pass = "ccm10";
		String authString = name + ":" + pass;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		address = address.concat(startdate).concat(DateUtil.getStringDateFormatyyyy_MM_dd(cmentity.getCreationdate()))
				.concat(space).concat(DateUtil.getStringDateFormatHHmm(cmentity.getCreationdate())).concat(endate)
				.concat(DateUtil.getStringDateFormatyyyy_MM_dd(new Date())).concat(space)
				.concat(DateUtil.getStringDateFormatHHmm(new Date())).concat(restaddress);
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		return connection;
	}

	private void readFile(BufferedReader in) {
		String response;
		String result = "";
		try (FileWriter fichero = new FileWriter(System.getenv(Constants.OPENSHIFT_DATA_DIR).concat("/prueba.html"))) {
			for (int i = 0; i < 10; i++)
				while ((response = in.readLine()) != null)
					result = result.concat(response);
			in.close();
			loadData(result);
		} catch (IOException e) {
			logger.error("No se puede abrir el fichero html");
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
		Long idtpv;
		while (itrs.hasNext()) {
			nodes = itrs.next().childNodes();
			if (((TextNode) nodes.get(5).childNode(0)).getWholeText().equals("CLOSE")) {
				node = (TextNode) nodes.get(3).childNode(0);
				award = node.getWholeText();
				node = (TextNode) nodes.get(6).childNode(0);
				date = DateUtil.getDate(node.getWholeText());
				node = (TextNode) nodes.get(8).childNode(0);
				samount = node.getWholeText();
				amount = new BigDecimal(samount.substring(0, samount.length() - 1).replaceFirst(",", ""));
				node = (TextNode) nodes.get(9).childNode(0);
				scomments = node.getWholeText();
				if (award.equals("TPV")) {
					idtpv = Long.valueOf(scomments);
					if (!tpvrepository.findById(idtpv).isPresent()) {
						TPVEntity tpv = new TPVEntity();
						PaymentEntity pay = new PaymentEntity();
						pay.setIdpayment(Constants.MAQUINACAMBIO);
						tpv.setPay(pay);
						tpv.setCreationdate(date);
						tpv.setAmount(amount);
						tpv.setIdtpv(idtpv);
						tpvrepository.save(tpv);
					}
				} else {
					loadChangeMachineEntity(award, scomments, date, nodes, amount);
				}
			}
		}
	}

	private void loadChangeMachineEntity(String award, String scomments, Date date, List<Node> nodes,
			BigDecimal amount) {
		AwardsChangeMachineEntity awardentity = new AwardsChangeMachineEntity();
		MachineEntity machine;
		ChangeMachineEntity cm = new ChangeMachineEntity();
		TextNode node = (TextNode) nodes.get(0).childNode(0);
		cm.setIdchangemachine(Long.valueOf(node.getWholeText()));
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

	}

	public List<Long> findLostNumbers() {
		List<Long> lostNumbers = new ArrayList<>();
		TakeEntity take = takingsRepository.findFirstByOrderByIdtakeDesc();
		List<ChangeMachineEntity> cms = changeMachineRepository.findByCreationdateBetween(take.getTakedate(),
				new Date());
		int size = cms.size();
		long first = cms.get(0).getIdchangemachine();
		long last = cms.get(size - 1).getIdchangemachine();
		int i = 0;
		for (long l = first; l < last && i < size && l <= cms.get(i).getIdchangemachine(); i++, l++) {
			if (cms.get(i).getIdchangemachine() != l) {
				lostNumbers.add(l);
			}
		}
		return lostNumbers;
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
}
