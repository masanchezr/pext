package com.gu.services.register;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.EmployeeScheduleEntity;
import com.gu.dbaccess.entities.FicticionalRegisterEntity;
import com.gu.dbaccess.entities.ScheduleEntity;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.dbaccess.repositories.FicticionalRegisterRepository;
import com.gu.dbaccess.repositories.ScheduleRepository;
import com.gu.util.constants.Constants;
import com.gu.util.date.DateUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class FicticionalRegisterServiceImpl implements FicticionalRegisterService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private FicticionalRegisterRepository registerRepository;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(FicticionalRegisterServiceImpl.class);

	@Override
	public List<FicticionalRegisterEntity> findByDates(String datefrom, String dateuntil) {
		Date dfrom = DateUtil.getDate(datefrom);
		Date duntil = DateUtil.getDate(dateuntil);
		List<ScheduleEntity> schedule = scheduleRepository.findByDatescheduleBetween(dfrom, duntil);
		if (schedule != null && !schedule.isEmpty()) {
			Iterator<ScheduleEntity> ischedule = schedule.iterator();
			ScheduleEntity se;
			EmployeeEntity employee;
			Calendar e = Calendar.getInstance();
			Calendar d = Calendar.getInstance();
			while (ischedule.hasNext()) {
				se = ischedule.next();
				Iterator<EmployeeScheduleEntity> iemployees = se.getEmployees().iterator();
				while (iemployees.hasNext()) {
					employee = iemployees.next().getEmployee();
					saveRegister(se, employee, e, d);
				}
			}
		}
		return registerRepository.findByCreationdateBetweenOrderByCreationdate(dfrom, duntil);
	}

	private void saveRegister(ScheduleEntity se, EmployeeEntity employee, Calendar e, Calendar d) {
		if (!employee.getIdemployee().equals(Constants.NOBODY)) {
			FicticionalRegisterEntity fre = registerRepository.findByCreationdateAndEmployee(se.getDateschedule(),
					employee);
			if (fre == null) {
				Random rand;
				try {
					rand = SecureRandom.getInstanceStrong();
					fre = new FicticionalRegisterEntity();
					TimeEntity time = se.getTime();
					fre.setCreationdate(se.getDateschedule());
					fre.setEmployee(employee);
					e.setTime(time.getEntry());
					e.set(Calendar.MINUTE, rand.nextInt(60));
					e.set(Calendar.SECOND, rand.nextInt(60));
					d.setTime(time.getDeparture());
					d.set(Calendar.MINUTE, rand.nextInt(60));
					d.set(Calendar.SECOND, rand.nextInt(60));
					fre.setTimein(e.getTime());
					fre.setTimeout(d.getTime());
					registerRepository.save(fre);
				} catch (NoSuchAlgorithmException e1) {
					log.error("No se ha podido generar el random.");
				}
			}
		}
	}

	@Override
	public void generatePdf(List<FicticionalRegisterEntity> register, File file) {
		try (PdfWriter writer = new PdfWriter(file)) {
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			float[] columns = { 30f, 30f, 30f, 30f, 30f };
			Table table = new Table(columns);
			Iterator<FicticionalRegisterEntity> iregister = register.iterator();
			FicticionalRegisterEntity r;
			Paragraph para = new Paragraph(
					"Estos datos solo podrán cederse a terceros con la finalidad de dar cumplimiento a las obligaciones de carácter legal o contractual relacionadas con el desarrollo de la actividad laboral.");
			document.add(new Paragraph("GOLDEN USERA S.L. Registro de empleados").setItalic());
			table.addCell(new Cell().add("DNI"));
			table.addCell(new Cell().add("Nombre"));
			table.addCell(new Cell().add("Fecha"));
			table.addCell(new Cell().add("Hora entrada"));
			table.addCell(new Cell().add("Hora salida"));
			while (iregister.hasNext()) {
				r = iregister.next();
				table.addCell(new Cell().add(r.getEmployee().getDni()));
				table.addCell(new Cell().add(r.getEmployee().getName()));
				table.addCell(new Cell().add(r.getCreationdate().toString()));
				table.addCell(new Cell().add(r.getTimein().toString()));
				table.addCell(new Cell().add(String.valueOf((r.getTimeout()))));
			}
			document.add(table);
			document.add(para);
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el ticket.");
		}
	}
}