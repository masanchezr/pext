package com.gu.services.register;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.RegisterEntity;
import com.gu.dbaccess.repositories.EmployeesRepository;
import com.gu.dbaccess.repositories.RegisterRepository;
import com.gu.util.date.DateUtil;

public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private EmployeesRepository employeesRepository;

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
	 *            Iterator<EmployeeEntity> ilin = lin.iterator(); InEntity in; Long
	 *            id; while (ilin.hasNext()) { id = ilin.next().getIdemployee(); if
	 *            (id != null) { in = new InEntity(); in.setDate(new Date());
	 *            in.setEmployee(employeesRepository.findById(id));
	 *            inRepository.save(in); inout.add(mapper.map(in, InOut.class)); } }
	 * 
	 *            }
	 * 
	 *            public List<Register> out(List<EmployeeEntity> lout) {
	 *            List<Register> inout = new ArrayList<Register>();
	 *            Iterator<EmployeeEntity> ilin = lout.iterator(); OutEntity in;
	 *            while (ilin.hasNext()) { in = new OutEntity(); in.setOutdate(new
	 *            Date()); in.setEmployee(mapper.map(ilin.next(),
	 *            EmployeeEntity.class)); outRepository.save(in);
	 *            inout.add(mapper.map(in, Register.class)); } return inout; }
	 **/
	public void registerIn(String user, String ipaddress) {
		EmployeeEntity employee = employeesRepository.findByUsername(user);
		if (employee.getEnabled().equals(Boolean.TRUE)) {
			RegisterEntity register = registerRepository
					.findByCreationdateAndEmployee(DateUtil.getDateFormated(new Date()), employee);
			if (register == null) {
				register = new RegisterEntity();
				register.setEmployee(employee);
				register.setCreationdate(new Date());
				register.setIpaddress(ipaddress);
				register.setEmployee(employee);
				register.setCreationdate(new Date());
				register.setIpaddress(ipaddress);
				register.setTimein(new Date());
				register.setActive(Boolean.TRUE);
				registerRepository.save(register);
			}
		}
	}

	public void registerOut(String user, String ipaddress) {
		EmployeeEntity employee = employeesRepository.findByUsername(user);
		if (employee.getEnabled().equals(Boolean.TRUE)) {
			RegisterEntity register = registerRepository
					.findByCreationdateAndEmployee(DateUtil.getDateFormated(new Date()), employee);
			if (register == null) {
				register = new RegisterEntity();
				register.setEmployee(employee);
				register.setCreationdate(new Date());
				register.setIpaddress(ipaddress);
				register.setEmployee(employee);
				register.setCreationdate(new Date());
				register.setIpaddress(ipaddress);
				register.setActive(Boolean.TRUE);
			}
			register.setTimeout(new Date());
			registerRepository.save(register);
		}
	}

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil) {
		Date from;
		Date until;
		EmployeeEntity employee;
		Iterable<EmployeeEntity> employees = employeesRepository.findByEnabledTrue();
		Iterator<EmployeeEntity> iemployees = employees.iterator();
		if (dateuntil == null || dateuntil.isEmpty()) {
			until = new Date();
		} else {
			until = DateUtil.getDate(dateuntil);
		}
		from = DateUtil.getDate(datefrom);
		List<Date> dates = DateUtil.getDates(from, until);
		// Desactivamos los registros que no queremos mostrar
		while (iemployees.hasNext()) {
			employee = iemployees.next();
			disabledRegister(employee, dates);
		}
		return registerRepository.findByCreationdateBetweenAndActiveTrue(from, until);
	}

	private void disabledRegister(EmployeeEntity employee, List<Date> dates) {
		Date date;
		RegisterEntity register;
		Iterator<Date> idates = dates.iterator();
		while (idates.hasNext()) {
			date = idates.next();
			// cuidado cambiar que hay varios registros de antes cambiar la tabla con uk
			register = registerRepository.findByCreationdateAndEmployee(date, employee);
			if (register == null) {
				register = registerRepository.findByCreationdateAndEmployee(DateUtil.addDays(date, -1), employee);
				if (register != null) {
					register.setActive(Boolean.FALSE);
					registerRepository.save(register);
				}
			}
		}
	}
}