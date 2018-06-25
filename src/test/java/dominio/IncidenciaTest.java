package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uo.asw.DashboardApplication;
import uo.asw.entities.Incidence;
import uo.asw.entities.IncidenceStatus;
import uo.asw.entities.TipoIncidencia;
import uo.asw.repositories.IncidenceRepository;

@SpringBootTest(classes = { DashboardApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class IncidenciaTest 
{	
	@Autowired
	private IncidenceRepository incidencesRepository;
	
	private Incidence incidence1, incidence2;
	@Before
	public void setUp()
	{
		incidence1 = new Incidence("Incidencia1", "Descripcion1", "guille@uniovi.es", Arrays.asList("incidencia1", "tags1"), TipoIncidencia.SENSOR_INUNDACION, 5.3);
		incidence2 = new Incidence("Incidencia2", "Descripcion2", "alvaro@uniovi.es", Arrays.asList("incidencia2", "tags2"), TipoIncidencia.SENSOR_TEMPERATURA, 10.4);
		incidence1.setLatitud(40.4);
		incidence1.setLongitud(-3.6);
		incidence2.setLatitud(40.9);
		incidence2.setLongitud(14.4);
	}
	
	@Test
	public void testObjectId() {
		assertNull(incidence1.get_id());
		assertNull(incidence2.get_id());
		incidence1.set_id(null);
		incidence2.set_id(null);
		incidencesRepository.save(incidence1);
		incidencesRepository.save(incidence2);
		assertNotNull(incidence1.get_id());
		assertNotNull(incidence2.get_id());
		incidencesRepository.delete(incidence1);
		incidencesRepository.delete(incidence2);
	}
	
	@Test
	public void testName() {
		assertEquals("Incidencia1", incidence1.getName());
		assertEquals("Incidencia2", incidence2.getName());
		incidence1.setName("Inci1");
		incidence2.setName("Inci2");
		assertEquals("Inci1", incidence1.getName());
		assertEquals("Inci2", incidence2.getName());
		incidence1.setName(null);
		incidence2.setName(null);
		assertNull(incidence1.getName());
		assertNull(incidence2.getName());
	}
	
	@Test
	public void testDescription() {
		assertEquals("Descripcion1", incidence1.getDescription());
		assertEquals("Descripcion2", incidence2.getDescription());
		incidence1.setDescription("Descri1");
		incidence2.setDescription("Descri2");
		assertEquals("Descri1", incidence1.getDescription());
		assertEquals("Descri2", incidence2.getDescription());
		incidence1.setDescription(null);
		incidence2.setDescription(null);
		assertNull(incidence1.getDescription());
		assertNull(incidence2.getDescription());
	}
	
	@Test
	public void testTags() {
		assertTrue(incidence1.getTags().contains("incidencia1"));
		assertTrue(incidence1.getTags().contains("tags1"));
		assertTrue(incidence2.getTags().contains("incidencia2"));
		assertTrue(incidence2.getTags().contains("tags2"));
		assertFalse(incidence1.getTags().contains("prueba"));
		incidence1.setTags(Arrays.asList("prueba"));
		assertTrue(incidence1.getTags().contains("prueba"));
		assertFalse(incidence1.getTags().contains("incidencia1"));
		assertFalse(incidence1.getTags().contains("tags1"));
		incidence2.setTags(null);
		assertNull(incidence2.getTags());
	}
	
	@Test
	public void testAgente() {
		assertEquals("guille@uniovi.es", incidence1.getAgent());
		assertEquals("alvaro@uniovi.es", incidence2.getAgent());
		incidence1.setAgent("miguel@uniovi.es");
		incidence2.setAgent("jesus@uniovi.es");
		assertEquals("miguel@uniovi.es", incidence1.getAgent());
		assertEquals("jesus@uniovi.es", incidence2.getAgent());
		incidence1.setAgent(null);
		incidence2.setAgent(null);
		assertNull(incidence1.getAgent());
		assertNull(incidence2.getAgent());
	}
	
	@Test
	public void testTipo() {
		assertEquals(TipoIncidencia.SENSOR_INUNDACION, incidence1.getType());
		assertEquals(TipoIncidencia.SENSOR_TEMPERATURA,incidence2.getType());
		incidence1.setType(TipoIncidencia.SENSOR_SEISMO);
		assertEquals(TipoIncidencia.SENSOR_SEISMO, incidence1.getType());
		incidence2.setType(null);
		assertNull(incidence2.getType());
	}
	
	@Test
	public void testEstado() {
		assertEquals(IncidenceStatus.OPENED, incidence1.getStatus());
		assertEquals(IncidenceStatus.OPENED,incidence2.getStatus());
		incidence1.setStatus(IncidenceStatus.IN_PROCESS);
		assertEquals(IncidenceStatus.IN_PROCESS, incidence1.getStatus());
		incidence2.setStatus(null);
		assertNull(incidence2.getStatus());
	}
	
	@Test
	public void testValor() {
		assertEquals(5.3, incidence1.getValor(),0.01);
		assertEquals(10.4, incidence2.getValor(),0.01);
		incidence1.setValor(6.4);
		assertEquals(6.4, incidence1.getValor(), 0.01);
	}
	
	@Test
	public void testLatitud() {
		assertEquals(40.4, incidence1.getLatitud(),0.01);
		assertEquals(40.9, incidence2.getLatitud(),0.01);
		incidence1.setLatitud(6.4);
		assertEquals(6.4, incidence1.getLatitud(), 0.01);
	}
	
	@Test
	public void testLongitud() {
		assertEquals(-3.6, incidence1.getLongitud(),0.01);
		assertEquals(14.4, incidence2.getLongitud(),0.01);
		incidence1.setLongitud(6.4);
		assertEquals(6.4, incidence1.getLongitud(), 0.01);
	}
}
