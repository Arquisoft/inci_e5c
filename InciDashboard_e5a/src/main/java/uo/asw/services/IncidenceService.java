package uo.asw.services;

import java.util.List;

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
}
