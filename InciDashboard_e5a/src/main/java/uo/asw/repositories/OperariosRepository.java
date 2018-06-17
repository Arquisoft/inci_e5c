package uo.asw.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uo.asw.entities.Operario;

@Repository
public interface OperariosRepository extends MongoRepository<Operario, Long> {

	public Operario findByUsername(String username);
	
	public Operario findOne(Long id);

}
