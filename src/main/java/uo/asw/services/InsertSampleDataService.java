package uo.asw.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.entities.Operario;
import uo.asw.repositories.IncidenceRepository;
import uo.asw.repositories.OperariosRepository;

@Service
public class InsertSampleDataService {

	@Autowired 
	private OperariosRepository operariosRepository;
	
	@Autowired 
	private IncidenceRepository incidenceRepository;
	
	@PostConstruct
	public void init() {
		operariosRepository.deleteAll();
		incidenceRepository.deleteAll();
		Operario operario1 = new Operario("guille@uniovi.es", "Contra"),
				operario2 = new Operario("miguel@uniovi.es", "Contra"),
				operario3 = new Operario("jesus@uniovi.es", "Contra"),
				operario4 = new Operario("alvaro@uniovi.es", "Contra");
		
		operariosRepository.save(operario1);
		operariosRepository.save(operario2);
		operariosRepository.save(operario3);
		operariosRepository.save(operario4);
	}
}
