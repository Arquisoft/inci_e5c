package uo.asw.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.entities.Incidence;
import uo.asw.entities.Notification;
import uo.asw.entities.Operario;
import uo.asw.entities.TipoIncidencia;
import uo.asw.repositories.IncidenceRepository;
import uo.asw.repositories.NotificationRepository;
import uo.asw.repositories.OperariosRepository;

@Service
public class NotificationService {

	@Autowired
	private IncidenceRepository incidenceRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private OperariosRepository operariosRepository;
	
	
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}
	
	
	 public void clasify(String strIdIncidencia, TipoIncidencia type, Double valor, String name) {
			
		 ObjectId idIncidence = new ObjectId(strIdIncidencia);
		 Incidence incidence = incidenceRepository.findOne(idIncidence);
		 
		 if(temperatura(type,valor)){
		 Notification n = new Notification(type,valor,name, incidence.getOperario());
		 notificationRepository.save(n);
		
		 }if(indundacion(type, valor)){
		
		 Notification n = new Notification(type,valor,name, incidence.getOperario());
		 notificationRepository.save(n);
		
		 }if(seismo(type, valor)){
		
		 Notification n = new Notification(type,valor,name, incidence.getOperario());
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


		public List<Notification> findByOperador(String operador) {
			
			Operario operario = operariosRepository.findByUsername(operador);
			
			return notificationRepository.findByOperador(operario.get_id());
		}
}
