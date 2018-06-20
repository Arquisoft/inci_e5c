package uo.asw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.entities.Notification;
import uo.asw.entities.TipoIncidencia;
import uo.asw.repositories.NotificationRepository;

@Service
public class NotificationService {

	
	@Autowired
	private NotificationRepository notificationRepository;
	
	
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}
	
	
	 public void clasify(TipoIncidencia type, Double valor, String name) {
			
		 if(temperatura(type,valor)){
		 Notification n = new Notification(type,valor,name);
		 notificationRepository.save(n);
		
		 }if(indundacion(type, valor)){
		
		 Notification n = new Notification(type,valor,name);
		 notificationRepository.save(n);
		
		 }if(seismo(type, valor)){
		
		 Notification n = new Notification(type,valor,name);
		 notificationRepository.save(n);
		
		 }
		
		 }

		private boolean temperatura(TipoIncidencia type, Double valor) {

			if (type.equals(TipoIncidencia.SENSOR_TEMPERATURA) && (valor != null && valor > 50.0)) {
				return true;
			}

			return false;

		}
		
		private boolean indundacion(TipoIncidencia type, Double valor) {

			if (type.equals(TipoIncidencia.SENSOR_INUNDACION) && (valor != null && valor == 1.0)) {
				return true;
			}

			return false;

		}
		
		private boolean seismo(TipoIncidencia type, Double valor) {

			if (type.equals(TipoIncidencia.SENSOR_SEISMO) && (valor != null && valor > 100.0)) {
				return true;
			}

			return false;

		}
}
