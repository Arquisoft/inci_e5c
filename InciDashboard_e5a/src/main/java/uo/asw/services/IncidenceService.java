package uo.asw.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.entities.Incidence;
import uo.asw.repositories.IncidenceRepository;

@Service
public class IncidenceService {

	@Autowired
	private IncidenceRepository incidenceRepository;
	
	public List<Incidence> findAll() {
		return incidenceRepository.findAll();
	}
	
	public Incidence findOne(ObjectId id) {
		return incidenceRepository.findOne(id);
	}
	
	public void update(Incidence incidence) {
		incidenceRepository.save(incidence);
	}
}
