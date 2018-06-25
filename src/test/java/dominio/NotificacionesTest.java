package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uo.asw.DashboardApplication;
import uo.asw.entities.Incidence;
import uo.asw.entities.Notification;
import uo.asw.entities.Operario;
import uo.asw.entities.TipoIncidencia;
import uo.asw.repositories.IncidenceRepository;
import uo.asw.repositories.NotificationRepository;
import uo.asw.services.NotificationService;

@SpringBootTest(classes = { DashboardApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class NotificacionesTest {
	
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private IncidenceRepository incidenceRepository;
	@Autowired
	private NotificationService notificationService;
	
	
	private Notification notification1, notification2;
	private Operario operario1,operario2;
	private Incidence incidence1;


	@Before
	public void setUp()
	{
		operario1 = new Operario("labra@uniovi.es", "ASW:2017-2018");
		operario2 = new Operario("aquilino@uniovi.es", "ASW_2017_2018");
		notification1 = new Notification( TipoIncidencia.SENSOR_TEMPERATURA, 79.3,"Notifacacion1", operario1.get_id());
		notification2 = new Notification( TipoIncidencia.SENSOR_TEMPERATURA, 90.3,"Notificacion2", operario2.get_id());
		incidence1 = new Incidence("Incidencia1", "Descripcion2", "alvaro@uniovi.es", Arrays.asList("incidencia2", "tags2"), TipoIncidencia.SENSOR_TEMPERATURA, 100.4);
		System.out.println(incidence1);
	}
	
	@Test
	public void testObjectId() {
		assertNull(notification1.get_id());
		assertNull(notification2.get_id());
		notification1.set_id(null);
		notification2.set_id(null);
		notificationRepository.save(notification1);
		notificationRepository.save(notification2);
		incidenceRepository.save(incidence1);
		assertNotNull(notification1.get_id());
		assertNotNull(notification2.get_id());
		notificationRepository.delete(notification1);
		notificationRepository.delete(notification2);
	}
	
	@Test
	public void testName() {
		assertEquals("Notifacacion1", notification1.getName());
		assertEquals("Notificacion2", notification2.getName());
		notification1.setName("Noti1");
		notification2.setName("Noti2");
		assertEquals("Noti1", notification1.getName());
		assertEquals("Noti2", notification2.getName());
		notification1.setName(null);
		notification2.setName(null);
		assertNull(notification1.getName());
		assertNull(notification2.getName());
	}
	
	@Test
	public void testTipo() {
		assertEquals(TipoIncidencia.SENSOR_TEMPERATURA, notification1.getType());
		assertEquals(TipoIncidencia.SENSOR_TEMPERATURA,notification2.getType());
		notification1.setType(TipoIncidencia.SENSOR_SEISMO);
		assertEquals(TipoIncidencia.SENSOR_SEISMO, notification1.getType());
		notification2.setType(null);
		assertNull(notification2.getType());
	}
	
	@Test
	public void testValor() {
		assertEquals(79.3, notification1.getValor(),0.01);
		assertEquals(90.3, notification2.getValor(),0.01);
		notification1.setValor(634.4);
		assertEquals(634.4, notification1.getValor(), 0.01);
	}
	
	@Test
	public void testOperario() {
		assertEquals(operario1.get_id(), notification1.getOperador());
		assertEquals(operario2.get_id(), notification2.getOperador());
	}
	
	
	
	@Test
	public void testNotificacionAsignada() {
	
		Incidence inci = incidenceRepository.findByName("Incidencia1");
		String strIdIncidencia = inci.get_id().toString();
		TipoIncidencia type = inci.getType();
		Double valor = inci.getValor();
		String name = inci.getName();
		int var1 = notificationRepository.findAll().size();	
		notificationService.clasify(strIdIncidencia, type, valor, name);
		int var2 = notificationRepository.findAll().size();	
		assertEquals(var1 + 1, var2);

	
	}
		

}
