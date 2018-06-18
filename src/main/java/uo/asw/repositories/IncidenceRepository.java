package uo.asw.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uo.asw.entities.Incidence;


public interface IncidenceRepository extends MongoRepository<Incidence, ObjectId> {

}
