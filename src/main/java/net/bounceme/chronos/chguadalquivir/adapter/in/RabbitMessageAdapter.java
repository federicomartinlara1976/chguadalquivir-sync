package net.bounceme.chronos.chguadalquivir.adapter.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RabbitMessageAdapter implements MessagePort {

	// Definir constantes para los nombres de clase (opcional pero recomendado)
	private static final String REGISTRO_DIARIO = "RegistroDiarioDTO";
	private static final String EMBALSE = "EmbalseDTO";
	private static final String ZONA = "ZonaDTO";
	private static final String PUNTO_CONTROL = "PuntoControlDTO";

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
	public void receive(MessageDTO<?> message) {
		final String className = message.getClassName();
	    final Object data = message.getData();

	    switch (className) {
	        case String s when s.contains(REGISTRO_DIARIO) -> 
	            servicioFacade.write(objectMapper.convertValue(data, RegistroDiarioDTO.class));
	        case String s when s.contains(EMBALSE) -> 
	            servicioFacade.write(objectMapper.convertValue(data, EmbalseDTO.class));
	        case String s when s.contains(ZONA) -> 
	            servicioFacade.write(objectMapper.convertValue(data, ZonaDTO.class));
	        case String s when s.contains(PUNTO_CONTROL) -> 
	            servicioFacade.write(objectMapper.convertValue(data, PuntoControlDTO.class));
	        default -> log.warn("Tipo desconocido {}", className);
	    }
	}
}
