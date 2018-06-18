package uo.asw.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uo.asw.entities.Operario;

@Repository
public interface OperariosRepository extends MongoRepository<Operario, ObjectId> {

	public Operario findByUsername(String username);

}
