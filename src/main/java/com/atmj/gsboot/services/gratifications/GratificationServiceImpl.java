package com.atmj.gsboot.services.gratifications;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.dbaccess.entities.MetadataEntity;
import com.atmj.dbaccess.repository.MetadataRepository;
import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.dbaccess.repositories.GratificationsRepository;
import com.atmj.gsboot.dbaccess.repositories.UsersRepository;
import com.atmj.gsboot.services.dailies.Daily;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.util.date.DateUtil;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

@Service
public class GratificationServiceImpl implements GratificationService {

	@Autowired
	private GratificationsRepository gratificationRepository;

	@Autowired
	private UsersRepository employeesrepository;

	@Autowired
	private MetadataRepository metadataRepository;

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(GratificationServiceImpl.class);

	/**
	 * pagamos la propina
	 */
	@Override
	public Daily save(GratificationEntity g, String user) {
		UserEntity employee = employeesrepository.findByUsername(user);
		g.setPaydate(new DateUtil().getNow());
		g.setEmployeepay(employee);
		gratificationRepository.save(g);
		return dailyService.getDailyEmployee();
	}

	private void generateTicket(GratificationEntity g, String path) {
		File file = new File(path.concat("ticket.pdf"));
		file.setWritable(true);
		try (PdfWriter writer = new PdfWriter(file)) {
			PdfDocument pdf = new PdfDocument(writer);
			PageSize page = PageSize.A4.rotate();
			Document document = new Document(pdf, page);
			PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD, PdfEncodings.WINANSI);
			document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n" + g.getAmount().toString() + "  DRAGONES").setFont(font)
					.setFontSize(14).setTextAlignment(TextAlignment.CENTER));
			// Create a List
			com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List();
			// Add ListItem objects
			list.add(new ListItem("Ticket número:" + g.getIdgratification() + " Entrega de ticket:"
					+ DateUtil.getStringDateFormatddMMyyyyHHmmss(g.getCreationdate())))
					.add(new ListItem(
							"Utilizar a partir de:" + DateUtil.getStringDateFormatddMMyyyyHHmm(g.getUsefromdate())))
					.add(new ListItem(
							"Caduca a partir de:" + DateUtil.getStringDateFormatddMMyyyyHHmm(g.getExpirationdate())))
					.add(new ListItem("Cliente:" + g.getClient() + " Empleado:" + g.getEmployee().getAlias()));
			// Add the list
			list.setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			document.add(list);
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el ticket.");
		}
	}

	@Override
	public GratificationEntity searchGratificationActive(Long id) {
		return gratificationRepository.findByIdgratificationAndPaydateIsNull(id, new DateUtil().getNow());
	}

	@Override
	public List<GratificationEntity> lastNumGratifications() {
		return gratificationRepository.findByCreationdateBetweenAndPaydateIsNull(
				DateUtil.addDays(DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow()), -1), new DateUtil().getNow());
	}

	@Override
	public void registerNumberGratification(GratificationEntity g, String user, String path) {
		UserEntity employee = employeesrepository.findByUsername(user);
		Calendar c = Calendar.getInstance();
		Date expirationdate;
		Date usefromdate;
		Optional<MetadataEntity> optionalmetadata = metadataRepository.findById("amountgratifications");
		if (optionalmetadata.isPresent()) {
			g.setAmount(Integer.valueOf(optionalmetadata.get().getValue()));
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			usefromdate = c.getTime();
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			expirationdate = c.getTime();
			g.setCreationdate(new DateUtil().getNow());
			g.setEmployee(employee);
			g.setUsefromdate(usefromdate);
			g.setExpirationdate(expirationdate);
			generateTicket(gratificationRepository.save(g), path);
		}
	}
}
