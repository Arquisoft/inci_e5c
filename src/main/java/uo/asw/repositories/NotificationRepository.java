package uo.asw.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uo.asw.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {

	List<Notification> findByOperador(ObjectId operador);
	
}