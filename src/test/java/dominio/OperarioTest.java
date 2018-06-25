package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uo.asw.DashboardApplication;
import uo.asw.entities.Operario;
import uo.asw.repositories.OperariosRepository;

@SpringBootTest(classes = { DashboardApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OperarioTest {
	
	@Autowired 
	private OperariosRepository operariosRepository;
	
	private Operario operario1,operario2;

	@Before
	public void setUp() throws Exception {
		this.operario1 = new Operario("labra@uniovi.es", "ASW:2017-2018");
		this.operario2 = new Operario("aquilino@uniovi.es", "ASW_2017_2018");
	}

	@Test
	public void testObjectId() {
		assertNull(operario1.get_id());
		assertNull(operario2.get_id());
		operario1.set_id(null);
		operario2.set_id(null);
		operariosRepository.save(operario1);
		operariosRepository.save(operario2);
		assertNotNull(operario1.get_id());
		assertNotNull(operario2.get_id());
		operariosRepository.delete(operario1);
		operariosRepository.delete(operario2);
	}
	
	@Test
	public void testUsername() {
		assertEquals(operario1.getUsername(), "labra@uniovi.es");
		assertEquals(operario2.getUsername(), "aquilino@uniovi.es");
		operario1.setUsername("labra@gmail.es");
		operario2.setUsername("aquilino@gmail.es");
		assertEquals(operario1.getUsername(), "labra@gmail.es");
		assertEquals(operario2.getUsername(), "aquilino@gmail.es");
		operario1.setUsername(null);
		operario2.setUsername(null);
		assertNull(operario1.getUsername());
		assertNull(operario2.getUsername());
	}
	
	@Test
	public void testPassword() {
		assertEquals(operario1.getPassword(), "ASW:2017-2018");
		assertEquals(operario2.getPassword(), "ASW_2017_2018");
		operario1.setPassword("ASW:2016-2017");
		operario2.setPassword("ASW_2016_2017");
		assertEquals(operario1.getPassword(), "ASW:2016-2017");
		assertEquals(operario2.getPassword(), "ASW_2016_2017");
		operario1.setPassword(null);
		operario2.setPassword(null);
		assertNull(operario1.getPassword());
		assertNull(operario2.getPassword());
	}
	
	@Test
	public void testToString() {
		assertEquals(operario1.toString(), "Operario [username=labra@uniovi.es, password=ASW:2017-2018]");
		assertEquals(operario2.toString(), "Operario [username=aquilino@uniovi.es, password=ASW_2017_2018]");
	}
	
	@Test
	public void testOperarioService() {
		
	}

}
