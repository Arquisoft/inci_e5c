package uo.asw.kafka;

import java.io.IOException;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import uo.asw.controllers.IncidenceController;

@ManagedBean
public class KafkaConsumer {
	
	@Autowired
	private IncidenceController controller;

	@KafkaListener(topics = "gygw6fys-Incidencias")
	public void listen(String data) {
		System.out.println("Recibido mensaje: " + data);
		for (SseEmitter emitter : controller.getEmitters()) {
		    try {
			emitter.send(data, MediaType.APPLICATION_JSON);
		    } catch (IOException e) {
			emitter.complete();
		    }
		}
	}
}