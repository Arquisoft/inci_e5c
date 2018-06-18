package uo.asw.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import uo.asw.entities.Incidence;
import uo.asw.services.IncidenceService;

@Controller
public class IncidenceController {

	@Autowired
	private IncidenceService incidenceService;

	private final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());

	public void sendData(SseEventBuilder event) {
		synchronized (emitters) {
			for (SseEmitter sseEmitter : emitters) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					sseEmitter = new SseEmitter(Long.MAX_VALUE);
				}
			}
		}
	}

	public List<SseEmitter> getEmitters() {
		return emitters;
	}

	@RequestMapping("/getEmitter")
	public SseEmitter getEmitter() {
		return nuevoEmitter();
	}

	public SseEmitter nuevoEmitter() {
		SseEmitter emitter = new SseEmitter();
		emitter.onTimeout(() -> emitters.remove(emitter));
		emitter.onCompletion(() -> emitters.remove(emitter));
		emitters.add(emitter);
		return emitter;
	}

	@GetMapping(value = "/incidencias")
	public String listarIncidencias(Model model, Principal principal) {
		List<Incidence> incidencias;
		incidencias = incidenceService.findAll();
		model.addAttribute("incidencias", incidencias);
		return "list";
	}
	
	@RequestMapping(value = "/incidencias/{id}", method = RequestMethod.GET)
	public String cambiarEstadoGet() {
		return "cambiarEstado";
	}
	
	@RequestMapping(value = "/incidencias/{id}", method = RequestMethod.POST)
	public String cambiarEstado() {
		return "redirect:/incidencias";
	}


	
}
