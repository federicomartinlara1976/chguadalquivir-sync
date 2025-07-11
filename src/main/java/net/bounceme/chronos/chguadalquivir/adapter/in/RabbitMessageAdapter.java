package net.bounceme.chronos.chguadalquivir.adapter.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bounceme.chronos.chguadalquivir.application.ServicioFacade;
import net.bounceme.chronos.chguadalquivir.port.in.MessagePort;
import net.bounceme.chronos.dto.MessageDTO;
import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;
import net.bounceme.chronos.dto.chguadalquivir.PuntoControlDTO;
import net.bounceme.chronos.dto.chguadalquivir.RegistroDiarioDTO;
import net.bounceme.chronos.dto.chguadalquivir.ZonaDTO;

/**
 * Receptor de la cola de mensajes. Se usa para evitar que la aplicación se
 * quede bloqueada mientras efectúa el envío de una notificación de aviso
 * 
 * @author frederik
 *
 */
@Service
public class RabbitMessageAdapter implements MessagePort {

	@Autowired
	private ServicioFacade servicioFacade;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * El método receptor
	 * 
	 * @param message
	 */
	@Override
	@RabbitListener(queues = "CHGEventos")
	public void receive(MessageDTO<?> message) {
		if (message.getClassName().contains("RegistroDiarioDTO")) {
			servicioFacade.write(objectMapper.convertValue(message.getData(), RegistroDiarioDTO.class));
		}

		if (message.getClassName().contains("EmbalseDTO")) {
			servicioFacade.write(objectMapper.convertValue(message.getData(), EmbalseDTO.class));
		}

		if (message.getClassName().contains("ZonaDTO")) {
			servicioFacade.write(objectMapper.convertValue(message.getData(), ZonaDTO.class));
		}
		
		if (message.getClassName().contains("PuntoControlDTO")) {
			servicioFacade.write(objectMapper.convertValue(message.getData(), PuntoControlDTO.class));
		}
	}
}
