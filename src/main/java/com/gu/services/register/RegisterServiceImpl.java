package com.gu.services.register;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.RegisterEntity;
import com.gu.dbaccess.repositories.EmployeesRepository;
import com.gu.dbaccess.repositories.RegisterRepository;

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
	 *            Iterator<EmployeeEntity> ilin = lin.iterator(); InEntity in;
	 *            Long id; while (ilin.hasNext()) { id =
	 *            ilin.next().getIdemployee(); if (id != null) { in = new
	 *            InEntity(); in.setDate(new Date());
	 *            in.setEmployee(employeesRepository.findOne(id));
	 *            inRepository.save(in); inout.add(mapper.map(in, InOut.class));
	 *            } }
	 * 
	 *            }
	 * 
	 *            public List<Register> out(List<EmployeeEntity> lout) {
	 *            List<Register> inout = new ArrayList<Register>();
	 *            Iterator<EmployeeEntity> ilin = lout.iterator(); OutEntity in;
	 *            while (ilin.hasNext()) { in = new OutEntity();
	 *            in.setOutdate(new Date());
	 *            in.setEmployee(mapper.map(ilin.next(), EmployeeEntity.class));
	 *            outRepository.save(in); inout.add(mapper.map(in,
	 *            Register.class)); } return inout; }
	 **/
	public void register(String user, String ipaddress, Boolean inout) {
		EmployeeEntity employee = employeesRepository.findByUsername(user);
		RegisterEntity register = new RegisterEntity();
		register.setEmployee(employee);
		register.setDate(new Date());
		register.setIpaddress(ipaddress);
		register.setOutin(inout);
		registerRepository.save(register);
	}

	public List<RegisterEntity> findByDate(Date date) {
		return registerRepository.selectByDate(date);
	}
}