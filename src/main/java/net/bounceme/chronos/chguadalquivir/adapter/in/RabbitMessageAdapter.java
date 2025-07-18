package net.bounceme.chronos.chguadalquivir.adapter.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.application.ServicioFacade;
import net.bounceme.chronos.chguadalquivir.port.in.MessagePort;
import net.bounceme.chronos.dto.chguadalquivir.CHGuadalquivirMessageDTO;
import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;
import net.bounceme.chronos.dto.chguadalquivir.MessageType;
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
@Slf4j
public class RabbitMessageAdapter implements MessagePort {

	private ServicioFacade servicioFacade;

	private ObjectMapper objectMapper;

	public RabbitMessageAdapter(ServicioFacade servicioFacade, ObjectMapper objectMapper) {
		this.servicioFacade = servicioFacade;
		this.objectMapper = objectMapper;
	}

	/**
	 * El método receptor
	 * 
	 * @param message
	 */
	@Override
	@RabbitListener(queues = "CHGEventos")
	public void receive(CHGuadalquivirMessageDTO<?> message) {
		final String className = message.getClassName();
		final MessageType messageType = message.getMessageType();
	    final Object data = message.getData();

	    switch (messageType) {
	        case REGISTRO_DIARIO -> 
	            servicioFacade.write(objectMapper.convertValue(data, RegistroDiarioDTO.class));
	        case EMBALSE -> 
	            servicioFacade.write(objectMapper.convertValue(data, EmbalseDTO.class));
	        case ZONA -> 
	            servicioFacade.write(objectMapper.convertValue(data, ZonaDTO.class));
	        case PUNTO_CONTROL -> 
	            servicioFacade.write(objectMapper.convertValue(data, PuntoControlDTO.class));
	        default -> log.warn("Tipo desconocido {}", className);
	    }
	}
}
