package cucumber_manager.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import uo.asw.DashboardApplication;
import uo.asw.entities.Incidence;
import uo.asw.entities.Operario;
import uo.asw.entities.TipoIncidencia;
import uo.asw.repositories.IncidenceRepository;
import uo.asw.repositories.OperariosRepository;
import uo.asw.services.IncidenceService;

@ContextConfiguration(classes = DashboardApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class ListarIncidenciasAsignadasStep {

	 @Autowired
	    OperariosRepository operariosRepository;
	    
	    @Autowired
	    IncidenceRepository incidenceRepository;
	    
	    @Autowired
	    IncidenceService incidenceService;
	    
	    String user, password;
	    String nombre1,nombre2, descripcion1,descripcion2;
	    List<String> etiquetas1, etiquetas2;
	    double valor1,valor2,lat1,lat2,lon1,lon2;
	    TipoIncidencia tipo1,tipo2;
	    Incidence i1, i2;
	    List<Incidence> incidencias;

	    @Dado("^el usuario del operario que va a listar las incidencias \'([^\"]*)\'$")
	    public void email(String user) 
	    {
	    	this.user = user;
	    	System.out.println("El usuario del operario es " + user);
	    }

	    @Y("^su password \'([^\"]*)\'$")
	    public void password(String password) 
	    {
	    	this.password = password;
	    }
	    

	    @Dado("^una incidencia recogida en el sistema con nombre \'([^\"]*)\'$")
	    public void una_incidencia_recogida_en_el_sistema_con_nombre1(String nombreIncidencia) throws Throwable 
	    {
	    	this.nombre1 = nombreIncidencia;
	    	System.out.println("El nombre de la 1 incidencia es: " + nombreIncidencia);
	    }

	    @Y("^descripcion \'([^\"]*)\'$")
	    public void descripcion_de_la_incidencia1(String descripcionIncidencia) throws Throwable 
	    {
	    	this.descripcion1 = descripcionIncidencia;
	    	System.out.println("La descripcion de la incidencia es: " + descripcionIncidencia);
	    }

	    @Y("^etiquetas \'([^\"]*)\'$")
	    public void etiquetas_de_la_incidencia1(ArrayList<String> etiquetas) throws Throwable 
	    {
	    	this.etiquetas1 = etiquetas;
	    	System.out.println("Éstas son las etiquetas de la incidencia 1");
	    	for (String string : etiquetas) 
	    	{
				System.out.println(string);
			}
	    	
	    }
	    
	    @Y("^tipo \'([^\"]*)\'$")
	    public void tipo_de_la_incidencia1(String tipo) {
	    	this.tipo1 = Incidence.parseTipo(tipo);
	    	System.out.println("El tipo de la incidencia 1 es " + tipo);
	    }
	    
	    @Y("^valor (.+)$")
	    public void valor_de_la_incidencia1(double valor) {
	    	this.valor1 = valor;
	    	System.out.println("El valor de la incidencia 1 es " + valor);
	    }
	    
	    @Y("^latitud (.+)$")
	    public void latitud_de_la_incidencia1(double lat) {
	    	this.lat1 = lat;
	    	System.out.println("La latitud de la incidencia 1 es " + lat);
	    }
	    
	    @Y("^longitud (.+)$")
	    public void longitud_de_la_incidencia1(double longitud) {
	    	this.lon1 = longitud;
	    	System.out.println("El valor de la incidencia 1 es " + longitud);
	    }
	    
	    @Dado("^otra incidencia recogida en el sistema con nombre \'([^\"]*)\'$")
	    public void otra_incidencia_recogida_en_el_sistema_con_nombre2(String nombreIncidencia) throws Throwable 
	    {
	    	this.nombre2 = nombreIncidencia;
	    	System.out.println("El nombre de la 2 incidencia es: " + nombreIncidencia);
	    }

	    @Y("^otra descripcion \'([^\"]*)\'$")
	    public void descripcion_de_la_incidencia2(String descripcionIncidencia) throws Throwable 
	    {
	    	this.descripcion2 = descripcionIncidencia;
	    	System.out.println("La descripcion de la incidencia es: " + descripcionIncidencia);
	    }

	    @Y("^otras etiquetas \'([^\"]*)\'$")
	    public void etiquetas_de_la_incidencia2(ArrayList<String> etiquetas) throws Throwable 
	    {
	    	this.etiquetas2 = etiquetas;
	    	System.out.println("Éstas son las etiquetas de la incidencia 2");
	    	for (String string : etiquetas) 
	    	{
				System.out.println(string);
	    	}
	    }
	    
	    @Y("^otro tipo \'([^\"]*)\'$")
	    public void tipo_de_la_incidencia2(String tipo) {
	    	this.tipo2 = Incidence.parseTipo(tipo);
	    	System.out.println("El tipo de la incidencia 2 es " + tipo);
	    }
	    
	    @Y("^otro valor (.+)$")
	    public void valor_de_la_incidencia2(double valor) {
	    	this.valor2 = valor;
	    	System.out.println("El valor de la incidencia 2 es " + valor);
	    }
	    
	    @Y("^otra latitud (.+)$")
	    public void latitud_de_la_incidencia2(double lat) {
	    	this.lat2 = lat;
	    	System.out.println("La latitud de la incidencia 2 es " + lat);
	    }
	    
	    @Y("^otra longitud (.+)$")
	    public void longitud_de_la_incidencia2(double longitud) {
	    	this.lon2 = longitud;
	    	System.out.println("El valor de la incidencia 2 es " + longitud);
	    }

	    @Cuando("^introducimos las incidencias en el sistema$")
	    public void introducimos_las_incidencias_en_el_sistema() 
	    {
			Incidence i1 = new Incidence(), i2 = new Incidence();
			i1.setName(nombre1); 
			i2.setName(nombre2);
			i1.setDescription(descripcion1);
			i2.setDescription(descripcion2);
			i1.setTags(etiquetas1);
			i2.setTags(etiquetas2);
			i1.setType(tipo1);
			i2.setType(tipo2);
			i1.setValor(valor1);
			i2.setValor(valor2);
			i1.setLatitud(lat1);
			i2.setLatitud(lat2);
			i1.setLongitud(lon1);
			i2.setLongitud(lon2);
			this.i1 = i1;
			this.i2 = i2;
			incidenceRepository.save(i1);
			incidenceRepository.save(i2);
	    }
	    
	    @Y("^iniciamos sesión$") 
	    public void iniciamos_sesion() {
	    	Operario operario = operariosRepository.findByUsername(user);
	    	assertTrue(operario != null);
	    	assertTrue(operario.getUsername().equals(user));
	    }
	    
	    @Y("^tratamos de listar todas las incidencias$")
	    public void tratamos_de_listar_todas_las_incidencias() {
	    	this.incidencias = incidenceService.findByOperador(user);
	    }
	    
	    @Entonces("^se listan las incidencias correctamente $")
	    public void se_listan_las_incidencias_correctamente() 
	    {
	    	assertTrue(incidencias.contains(i1));
	    	assertFalse(incidencias.contains(i2));
	    	incidenceRepository.delete(i1);
	    	incidenceRepository.delete(i2);
	    	System.out.println("Se listan las incidencias correctamente");
	    }
	
}
