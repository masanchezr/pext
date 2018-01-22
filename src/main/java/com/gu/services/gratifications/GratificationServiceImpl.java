package com.gu.services.gratifications;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.dbaccess.repositories.EmployeesRepository;
import com.gu.dbaccess.repositories.GratificationsRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class GratificationServiceImpl implements GratificationService {

	@Autowired
	private GratificationsRepository gratificationRepository;

	@Autowired
	private EmployeesRepository employeesrepository;

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/**
	 * pagamos la propina
	 */
	public Daily save(GratificationEntity g, String user) {
		EmployeeEntity employee = employeesrepository.findByUsername(user);
		g.setPaydate(new Date());
		g.setEmployeepay(employee);
		gratificationRepository.save(g);
		return dailyService.getDailyEmployee(new Date());
	}

	private void generateTicket(GratificationEntity g, String path) {
		File file = new File(path.concat("ticket.pdf"));
		PdfWriter writer;
		try {
			writer = new PdfWriter(file);
			PdfDocument pdf = new PdfDocument(writer);
			/*
			 * PageRotationEventHandler eventHandler = new PageRotationEventHandler();
			 * pdf.addEventHandler(PdfDocumentEvent.START_PAGE, eventHandler);
			 */
			PageSize page = PageSize.A4.rotate();
			Document document = new Document(pdf, page);
			PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
			document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n10 DRAGONES").setFont(font).setFontSize(14)
					.setTextAlignment(TextAlignment.CENTER));
			// Create a List
			com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List();
			// Add ListItem objects
			list.add(new ListItem("Ticket número:" + g.getIdgratification() + " Entrega de ticket:"
					+ DateUtil.getStringDateFormatdd_MM_yyyyHHmmss(g.getCreationdate())))
					.add(new ListItem(
							"Utilizar a partir de:" + DateUtil.getStringDateFormatdd_MM_yyyyHHmm(g.getUsefromdate())))
					.add(new ListItem(
							"Caduca a partir de:" + DateUtil.getStringDateFormatdd_MM_yyyyHHmm(g.getExpirationdate())))
					.add(new ListItem("Cliente:" + g.getClient() + " Empleado:" + g.getEmployee().getAlias()));
			// Add the list
			list.setFontSize(14).setTextAlignment(TextAlignment.CENTER);
			document.add(list);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GratificationEntity searchGratificationActive(Long id) {
		GratificationEntity g = gratificationRepository.findByIdgratificationAndPaydateIsNull(id, new Date());
		return g;
	}

	public List<GratificationEntity> lastNumGratifications() {
		return gratificationRepository.findByCreationdateBetweenAndPaydateIsNull(DateUtil.addDays(new Date(), -1),
				new Date());
	}

	public void registerNumberGratification(GratificationEntity g, String user, String path) {
		EmployeeEntity employee = employeesrepository.findByUsername(user);
		Calendar c = Calendar.getInstance();
		Date expirationdate, usefromdate;
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		usefromdate = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		expirationdate = c.getTime();
		g.setCreationdate(new Date());
		g.setEmployee(employee);
		g.setUsefromdate(usefromdate);
		g.setExpirationdate(expirationdate);
		generateTicket(gratificationRepository.save(g), path);
	}
}
