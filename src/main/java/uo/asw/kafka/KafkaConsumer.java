package uo.asw.kafka;


import java.io.IOException;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import uo.asw.controllers.IncidenceController;
import uo.asw.entities.TipoIncidencia;
import uo.asw.services.IncidenceService;
import uo.asw.services.NotificationService;
@ManagedBean
public class KafkaConsumer {
	
	@Autowired
	private IncidenceController controller;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private IncidenceService incidenceService;
	

	@KafkaListener(topics = "gygw6fys-Incidencias")
	public void listen(String data) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Recibido mensaje: " + data);
		String incidenceType = "";
		Double incidenceValue = 0.0;
		String name = "";
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.readValue(data, ObjectNode.class);
		
		String incidenceId = jsonObject.get("_id").asText();
		incidenceType = jsonObject.get("type").asText();
		incidenceValue = jsonObject.get("valor").asDouble();
		name = jsonObject.get("name").asText();
		
		incidenceService.assing(incidenceId);
		
		notificationService.clasify(incidenceId, TipoIncidencia.valueOf(incidenceType), incidenceValue,name);
		
		for (SseEmitter emitter : controller.getEmitters()) {
		    try {
			emitter.send(data, MediaType.APPLICATION_JSON);
		    } catch (IOException e) {
			emitter.complete();
		    }
		}
	}
}