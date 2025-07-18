package net.bounceme.chronos.chguadalquivir.port.in;

import net.bounceme.chronos.dto.chguadalquivir.CHGuadalquivirMessageDTO;

public interface MessagePort {

	/**
	 * El método receptor
	 * 
	 * @param message
	 */
	void receive(CHGuadalquivirMessageDTO<?> message);

}