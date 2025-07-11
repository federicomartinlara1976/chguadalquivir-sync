package net.bounceme.chronos.chguadalquivir.port.in;

import net.bounceme.chronos.dto.MessageDTO;

public interface MessagePort {

	/**
	 * El método receptor
	 * 
	 * @param message
	 */
	void receive(MessageDTO<?> message);

}