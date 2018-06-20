package uo.asw.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uo.asw.entities.Notification;

public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {

	
	
}