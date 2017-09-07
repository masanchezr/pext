package com.gu.services.register;

import java.util.List;

import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

public class SCardConexion {

	private Card card = null;
	private CardChannel channel = null;

	/**
	 * Devuelve una lista de objetos tipo CardTerminal, donde viene toda la
	 * información de las terminales PC/SC conectadas
	 * 
	 * @return
	 * @throws CardException
	 */
	private synchronized List<CardTerminal> terminals() throws CardException {
		TerminalFactory factory = TerminalFactory.getDefault();
		return factory.terminals().list();
	}

	/**
	 * Metodo conncet abre la conección utilizando un canal básico con el PC/SC
	 * 
	 * @param t
	 *            número de lector PC/SC conectado al puerto USB
	 * @param protocol
	 *            protocolo a utilizar durante la conección utilice ("T=0",
	 *            "T=1", o "T=CL"), o "*" para conectar al protocolo disponible
	 *            durante la conexion
	 * @return Un objeto de tipo ATR que contiene el mensaje de contacto Anwer
	 *         to reset
	 * @see <a href="http://es.wikipedia.org/wiki/Tarjeta_inteligente">WikiES:
	 *      Tarjeta inteligente</a>
	 * @see <a href="http://en.wikipedia.org/wiki/Answer_to_reset">WikiEng:
	 *      Answer to reset</a>
	 * @throws CardException
	 */
	public synchronized ATR connect(int t, String protocol) throws CardException {
		CardTerminal terminal = this.terminals().get(t);
		card = terminal.connect(protocol);
		card.beginExclusive();
		channel = card.getBasicChannel();
		return card.getATR();
	}

	/**
	 * Metodo transmit, transmite un comando hacia el canal abierto de la
	 * conexión PC/SC
	 * 
	 * @param apdu
	 *            recibe un objeto de tipo CommandAPDU
	 * @return Un objeto de tipo ResponseAPDU que contiene la data de respuesta
	 *         Response Data Lr y SW1-SW2
	 * @see <a href=
	 *      "http://es.wikipedia.org/wiki/Application_Protocol_Data_Unit">WikiES:
	 *      Application Protocol Data Unit</a>
	 * @throws CardException
	 */
	public synchronized ResponseAPDU transmit(CommandAPDU apdu) throws CardException {
		return channel.transmit(apdu);
	}

	/**
	 * Metodo disconnect, desconeta la conexion PC/SC terminando el canal
	 * exclusivo
	 * 
	 * @throws CardException
	 */
	public synchronized void disconnect() throws CardException {
		card.endExclusive();
		card.disconnect(true);
	}

}