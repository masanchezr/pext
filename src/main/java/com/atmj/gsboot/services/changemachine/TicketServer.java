package com.atmj.gsboot.services.changemachine;

public class TicketServer extends Thread {

	private ChangeMachineService changeMachineService;

	public TicketServer(ChangeMachineService changeMachineService) {
		this.changeMachineService = changeMachineService;
	}

	@Override
	public void run() {
		changeMachineService.loadDataTicketServer();
	}
}
