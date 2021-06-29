package com.sboot.golden.services.register;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.entities.MetadataEntity;
import com.sboot.golden.dbaccess.entities.RegisterEntity;
import com.sboot.golden.dbaccess.repositories.UsersRepository;
import com.sboot.golden.dbaccess.repositories.MetadataRepository;
import com.sboot.golden.dbaccess.repositories.RegisterRepository;
import com.sboot.golden.util.date.DateUtil;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private UsersRepository employeesRepository;

	@Autowired
	private MetadataRepository metadataRepository;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

	/**
	 * @Autowired private Mapper mapper;
	 * 
	 * 
	 *            public void in() {
	 * 
	 * 
	 *            SCardConexion cardConexion = new SCardConexion();
	 *            cardConexion.connect(0, "T=1"); CommandAPDU cpadu= new
	 *            CommandAPDU(); ResponseAPDU rapdu=cardConexion.(capdu);
	 *            List<InOut> inout = new ArrayList<InOut>();
	 *            Iterator<UserEntity> ilin = lin.iterator(); InEntity in; Long
	 *            id; while (ilin.hasNext()) { id = ilin.next().getIdemployee(); if
	 *            (id != null) { in = new InEntity(); in.setDate(new
	 *            DateUtil().getNow());
	 *            in.setEmployee(employeesRepository.findById(id));
	 *            inRepository.save(in); inout.add(mapper.map(in, InOut.class)); } }
	 * 
	 *            }
	 * 
	 *            public List<Register> out(List<UserEntity> lout) {
	 *            List<Register> inout = new ArrayList<Register>();
	 *            Iterator<UserEntity> ilin = lout.iterator(); OutEntity in;
	 *            while (ilin.hasNext()) { in = new OutEntity(); in.setOutdate(new
	 *            Date()); in.setEmployee(mapper.map(ilin.next(),
	 *            UserEntity.class)); outRepository.save(in);
	 *            inout.add(mapper.map(in, Register.class)); } return inout; }
	 **/
	public void registerIn(String user, String ipaddress) {
		UserEntity employee = employeesRepository.findByUsername(user);
		Optional<MetadataEntity> ip = metadataRepository.findById("ipgoldenusera");
		if (employee.getEnabled().equals(Boolean.TRUE) && ip.isPresent() && ip.get().getValue().equals(ipaddress)) {
			RegisterEntity register = registerRepository
					.findByCreationdateAndEmployee(DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow()), employee);
			if (register == null) {
				register = setRegister(employee, ipaddress);
				register.setTimein(new DateUtil().getNow());
				registerRepository.save(register);
			}
		}
	}

	public void registerOut(String user, String ipaddress) {
		UserEntity employee = employeesRepository.findByUsername(user);
		Optional<MetadataEntity> ip = metadataRepository.findById("ipgoldenusera");
		if (employee.getEnabled().equals(Boolean.TRUE) && ip.isPresent() && ip.get().getValue().equals(ipaddress)) {
			RegisterEntity register = registerRepository
					.findByCreationdateAndEmployee(DateUtil.getDateFormatddMMyyyy(new DateUtil().getNow()), employee);
			if (register == null) {
				register = setRegister(employee, ipaddress);
			}
			register.setTimeout(new DateUtil().getNow());
			registerRepository.save(register);
		}
	}

	private RegisterEntity setRegister(UserEntity employee, String ipaddress) {
		RegisterEntity register = new RegisterEntity();
		register.setEmployee(employee);
		register.setCreationdate(new DateUtil().getNow());
		register.setEmployee(employee);
		register.setCreationdate(new DateUtil().getNow());
		register.setIpaddress(ipaddress);
		register.setActive(Boolean.TRUE);
		return register;
	}

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil) {
		Date from;
		Date until;
		/**
		 * UserEntity employee; Iterable<UserEntity> employees =
		 * employeesRepository.findByEnabledTrue(); // Iterator<UserEntity>
		 * iemployees = employees.iterator();
		 **/
		if (dateuntil == null || dateuntil.isEmpty()) {
			until = new DateUtil().getNow();
		} else {
			until = DateUtil.getDate(dateuntil);
		}
		from = DateUtil.getDate(datefrom);
		/**
		 * List<Date> dates = DateUtil.getDates(from, until); Desactivamos los registros
		 * que no queremos mostrar while (iemployees.hasNext()) { employee =
		 * iemployees.next(); disabledRegister(employee, dates); }
		 **/
		return registerRepository.findByCreationdateBetweenAndActiveTrue(from, until);
	}

	@Override
	public void generatePdf(List<RegisterEntity> register, File file) {
		try (PdfWriter writer = new PdfWriter(file)) {
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			float[] columns = { 30f, 30f, 30f, 30f, 30f };
			Table table = new Table(columns);
			Iterator<RegisterEntity> iregister = register.iterator();
			RegisterEntity r;
			Paragraph para = new Paragraph(
					"Estos datos solo podrán cederse a terceros con la finalidad de dar cumplimiento a las obligaciones de carácter legal o contractual relacionadas con el desarrollo de la actividad laboral.");
			Paragraph dni = new Paragraph("DNI");
			Paragraph name = new Paragraph("Nombre");
			Paragraph date = new Paragraph("Fecha");
			Paragraph in = new Paragraph("Hora entrada");
			Paragraph out = new Paragraph("Hora salida");
			Paragraph dniemployee;
			Paragraph nameemployee;
			Paragraph creationdate;
			Paragraph timein;
			Paragraph timeout;
			document.add(new Paragraph("GOLDEN USERA S.L. Registro de empleados").setItalic());
			table.addCell(new Cell().add(dni));
			table.addCell(new Cell().add(name));
			table.addCell(new Cell().add(date));
			table.addCell(new Cell().add(in));
			table.addCell(new Cell().add(out));
			while (iregister.hasNext()) {
				r = iregister.next();
				dniemployee = new Paragraph(r.getEmployee().getDni());
				nameemployee = new Paragraph(r.getEmployee().getName());
				creationdate = new Paragraph(r.getCreationdate().toString());
				timein = new Paragraph(r.getTimein().toString());
				timeout = new Paragraph(String.valueOf((r.getTimeout())));
				table.addCell(new Cell().add(dniemployee));
				table.addCell(new Cell().add(nameemployee));
				table.addCell(new Cell().add(creationdate));
				table.addCell(new Cell().add(timein));
				table.addCell(new Cell().add(timeout));
			}
			document.add(table);
			document.add(para);
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el PDF de registro empleados.");
		}
	}

	/**
	 * private void disabledRegister(UserEntity employee, List<Date> dates) {
	 * Date date; RegisterEntity register; Iterator<Date> idates = dates.iterator();
	 * while (idates.hasNext()) { date = idates.next(); // cuidado cambiar que hay
	 * varios registros de antes cambiar la tabla con uk register =
	 * registerRepository.findByCreationdateAndEmployee(date, employee); if
	 * (register == null) { register =
	 * registerRepository.findByCreationdateAndEmployee(DateUtil.addDays(date, -1),
	 * employee); if (register != null) { register.setActive(Boolean.FALSE);
	 * registerRepository.save(register); } } } }
	 **/
}