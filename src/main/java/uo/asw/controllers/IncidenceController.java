package uo.asw.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import uo.asw.entities.Incidence;
import uo.asw.entities.IncidenceStatus;
import uo.asw.services.IncidenceService;
import uo.asw.services.NotificationService;

@Controller
public class IncidenceController {

	@Autowired
	private IncidenceService incidenceService;

	@Autowired
	private NotificationService notificationService;
	
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

	@CrossOrigin(origins = "http://localhost:8091")
	@RequestMapping("/getEmitter")
	public SseEmitter getEmitter() {
		return nuevoEmitter();
	}

	public SseEmitter nuevoEmitter() {
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
		emitter.onTimeout(() -> emitters.remove(emitter));
		emitter.onCompletion(() -> emitters.remove(emitter));
		emitters.add(emitter);
		return emitter;
	}

	@GetMapping(value = "/incidencias")
	public String listarIncidencias(Model model, Principal principal) {
		List<Incidence> incidencias = incidenceService.findAll();
		Collections.reverse(incidencias);
		model.addAttribute("incidencias", incidencias);
		return "list";
	}

	@GetMapping(value = "/incidencias/asignadas")
	public String listarIncidenciasAsignadas(Model model, Principal principal) {
		String usuario = principal.getName();
		model.addAttribute("incidencias", incidenceService.findByOperador(usuario));
		return "list";
	}
	
	@RequestMapping(value = "/incidencias/cambiar-estado/{id}", method = RequestMethod.GET)
	public String cambiarEstadoGet(Model model, @PathVariable String id) {
		model.addAttribute("id", id);
		model.addAttribute("estados", Incidence.estados);
		return "cambiarEstado";
	}
	
	@RequestMapping(value = "/incidencias/cambiar-estado/{id}", method = RequestMethod.POST)
	public String cambiarEstado(Model model, @PathVariable String id,  @RequestParam String status) {
		IncidenceStatus estado = Incidence.estados.stream().filter(x -> x.toString().toUpperCase().equals(status)).findFirst().get();
		Incidence incidence = incidenceService.findOne(new ObjectId(id));
		incidence.setStatus(estado);
		incidenceService.update(incidence);
		return "redirect:/incidencias";
	}


	@RequestMapping(value = "/incidencias/mapa", method = RequestMethod.GET) 
	public String mapaIncidencias(Model model) {
		List<Incidence> validIncidences = new ArrayList<>();
		incidenceService.findAll().stream() .filter(x -> x.getLatitud() != null && x.getLongitud() != null)
		.forEach(x -> validIncidences.add(x));
		model.addAttribute("incidencias", validIncidences);
		return "mapa";
	}
	@RequestMapping(value = "/notificaciones", method = RequestMethod.GET)
	public String listarNotificaciones(Model model, Principal principal) {
	model.addAttribute("notificaciones", notificationService.findByOperador(principal.getName()));
	return "listNotifications";
}
	
}
