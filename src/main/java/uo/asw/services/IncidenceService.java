package uo.asw.services;

import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.entities.Incidence;
import uo.asw.entities.Operario;
import uo.asw.repositories.IncidenceRepository;
import uo.asw.repositories.OperariosRepository;

@Service
public class IncidenceService {
	
	private static final Random r = new Random();

	@Autowired
	private IncidenceRepository incidenceRepository;
	@Autowired
	private OperariosRepository operariosRepository;
	
	public void add(Incidence incidence) {
		incidenceRepository.save(incidence);
	}
	
	public List<Incidence> findAll() {
		return incidenceRepository.findAll();
	}
	
	public Incidence findOne(ObjectId id) {
		return incidenceRepository.findOne(id);
	}
	
	public void update(Incidence incidence) {
		incidenceRepository.save(incidence);
	}

	public void assing(String incidenceId) {
		
		ObjectId id = new ObjectId(incidenceId);
		
		List<Operario> operarios = operariosRepository.findAll();
		Operario operario = operarios.get(r.nextInt(operarios.size()));
		
		Incidence incidence = incidenceRepository.findOne(id);
		if (incidence != null && incidence.getOperario() == null) {
			incidence.setOperario(operario.get_id());
			incidenceRepository.save(incidence);
		}
	}

	public List<Incidence> findByOperador(String usuario) {

		Operario operario = operariosRepository.findByUsername(usuario);
		return incidenceRepository.findByOperario(operario.get_id());
	}
}
