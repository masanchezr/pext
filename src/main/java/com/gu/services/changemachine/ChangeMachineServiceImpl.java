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
import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.AwardsChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.entities.TakeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.MachinesRepository;
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

	public BigDecimal getIncomeTotalMonth() {
		return changeMachineRepository
				.sumIncomeBetweenDates(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
	}

	public void reset() {
		Date from = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		TakeEntity take = new TakeEntity();
		ChangeMachineTotalEntity cmt = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity cmtn = new ChangeMachineTotalEntity();
		take.setTakedate(new Date());
		cmtn.setCreationdate(new Date());
		cmtn.setTotal(cmt.getTotal().subtract(changeMachineRepository.sumByCreationdateBetween(from, new Date())));
		changeMachineTotalRepository.save(cmtn);
		takingsRepository.save(take);
	}

	public BigDecimal getTotal() {
		return changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc().getTotal();
	}

	public BigDecimal getAwards() {
		return changeMachineRepository
				.sumByCreationdateBetween(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
	}

	public Map<String, ?> ticketsByDay(Date date) {
		Map<String, Object> map = null;
		BigDecimal amount = BigDecimal.ZERO;
		List<ChangeMachineEntity> lcm = changeMachineRepository.findByCreationdate(date);
		if (lcm != null && !lcm.isEmpty()) {
			Iterator<ChangeMachineEntity> ilcm = lcm.iterator();
			while (ilcm.hasNext()) {
				amount = amount.add(ilcm.next().getAmount());
			}
			map = new HashMap<String, Object>();
			map.put(ConstantsJsp.OPERATIONS, lcm);
			map.put(Constants.AMOUNT, amount.plus());
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
		String response;
		String award;
		String samount;
		String result = "";
		try {
			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			// Aquí leemos el resultado que nos devolvió el servidor, en efecto, lo que
			// respondió form.php y luego de enviar los datos
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			FileWriter fichero = new FileWriter(System.getenv(Constants.OPENSHIFT_DATA_DIR).concat("/prueba.html"));
			for (int i = 0; i < 10; i++)
				while ((response = in.readLine()) != null)
					result = result.concat(response);
			fichero.close();
			in.close();
		} catch (IOException e) {

		}
		Document doc = Jsoup.parse(result);
		Elements trs = doc.getElementsByClass("tbl-row");
		ListIterator<Element> itrs = trs.listIterator();
		List<Node> nodes;
		ChangeMachineEntity cm;
		TextNode node;
		AwardsChangeMachineEntity awardentity = new AwardsChangeMachineEntity();
		MachineEntity machine;
		while (itrs.hasNext()) {
			nodes = itrs.next().childNodes();
			if (((TextNode) nodes.get(5).childNode(0)).getWholeText().equals("CLOSE")) {
				cm = new ChangeMachineEntity();
				node = (TextNode) nodes.get(0).childNode(0);
				cm.setIdchangemachine(Long.valueOf(node.getWholeText()));
				node = (TextNode) nodes.get(3).childNode(0);
				award = node.getWholeText();
				machine = machinesRepository.findByNameticket(award);
				if (machine == null) {
					node = (TextNode) nodes.get(9).childNode(0);
					if (award.equals("TECNAUSA")) {
						award = node.getWholeText();
						awardentity.setIdawardchangemachine(2L);
						String sub = award.substring(1, 4);
						machine = new MachineEntity();
						if (Util.isNumeric(sub)) {
							machine.setIdmachine(Long.valueOf(sub));
						} else {
							sub = award.substring(1, 3);
							if (Util.isNumeric(sub)) {
								machine.setIdmachine(Long.valueOf(sub));
							} else {
								machine.setIdmachine(Long.valueOf(award.substring(1, 2)));
							}
						}
						cm.setMachine(machine);
					} else {
						award = node.getWholeText();
						awardentity.setIdawardchangemachine(1L);
						machine = machinesRepository.findByNameticket(award);
						cm.setMachine(machine);
					}
				} else {
					awardentity.setIdawardchangemachine(1L);
					cm.setMachine(machine);
				}
				cm.setAward(awardentity);
				node = (TextNode) nodes.get(6).childNode(0);
				cm.setCreationdate(DateUtil.getDate(node.getWholeText()));
				node = (TextNode) nodes.get(8).childNode(0);
				samount = node.getWholeText();
				cm.setAmount(new BigDecimal(samount.substring(0, samount.length() - 1).replaceFirst(",", "")));
				changeMachineRepository.save(cm);
			}
		}
	}

	public List<Long> findLostNumbers() {
		List<Long> lostNumbers = new ArrayList<Long>();
		TakeEntity take = takingsRepository.findFirstByOrderByIdtakeDesc();
		List<ChangeMachineEntity> cms = changeMachineRepository.findByCreationdateBetween(take.getTakedate(),
				new Date());
		int size = cms.size();
		long first = cms.get(0).getIdchangemachine();
		long last = cms.get(size - 1).getIdchangemachine();
		int i = 0;
		for (long l = first; l < last && i < size && l <= cms.get(i).getIdchangemachine(); i++, l++) {
			if (cms.get(i).getIdchangemachine() != l) {
				lostNumbers.add(l++);
			}
		}
		return lostNumbers;
	}
}
