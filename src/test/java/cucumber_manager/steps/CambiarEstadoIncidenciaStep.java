package cucumber_manager.steps;

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
import uo.asw.entities.IncidenceStatus;
import uo.asw.entities.TipoIncidencia;
import uo.asw.services.IncidenceService;

@ContextConfiguration(classes = DashboardApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class CambiarEstadoIncidenciaStep {
	
	@Autowired 
	private IncidenceService incidenceService;

	private String nombre, descripcion;
	private List<String> tags;
	private double valor,lon,lat;
	private TipoIncidencia tipo;
	private Incidence incidencia;
	private IncidenceStatus newStatus;
	
	@Dado("^una incidencia con nombre \'([^\"]*)\'$")
	public void una_incidencia_con_nombre(String nombreIncidencia) throws Throwable {
		this.nombre = nombreIncidencia;
		System.out.println("El nombre de la  incidencia es: " + nombreIncidencia);
	}

	@Y("^su descripcion \'([^\"]*)\'$")
	public void su_descripcion(String descripcionIncidencia) throws Throwable {
		this.descripcion = descripcionIncidencia;
		System.out.println("La descripcion de la incidencia es: " + descripcionIncidencia);
	}

	@Y("^sus etiquetas \'([^\"]*)\'$")
	public void sus_etiquetas(ArrayList<String> etiquetas) throws Throwable {
		this.tags = etiquetas;
		System.out.println("Éstas son las etiquetas de la incidencia ");
		for (String string : etiquetas) {
			System.out.println(string);
		}

	}

	@Y("^su tipo \'([^\"]*)\'$")
	public void su_tipo(String tipo) {
		this.tipo = Incidence.parseTipo(tipo);
		System.out.println("El tipo de la incidencia  es " + tipo);
	}

	@Y("^su valor (.+)$")
	public void su_valor(double valor) {
		this.valor = valor;
		System.out.println("El valor de la incidencia  es " + valor);
	}

	@Y("^su latitud (.+)$")
	public void su_latitud(double lat) {
		this.lat = lat;
		System.out.println("La latitud de la incidencia  es " + lat);
	}

	@Y("^su longitud (.+)$")
	public void su_longitud(double longitud) {
		this.lon = longitud;
		System.out.println("El valor de la incidencia  es " + longitud);
	}
	
	@Cuando("^intentamos cambiar el estado de la incidencia por \'([^\"]*)\'$")
	public void cambiar_estado_incidencia_por(String nuevoEstado) {
		this.incidencia = new Incidence();
		incidencia.setName(nombre);
		incidencia.setDescription(descripcion);
		incidencia.setTags(tags);
		incidencia.setType(tipo);
		incidencia.setValor(valor);
		incidencia.setLatitud(lat);
		incidencia.setLongitud(lon);
		incidenceService.add(incidencia);
		assertTrue(incidencia.getStatus().equals(IncidenceStatus.OPENED));
		System.out.println("Cambiamos su estado de 'OPENED' a '"  + nuevoEstado + "'");
		this.newStatus = Incidence.parseEstado(nuevoEstado);
		incidencia.setStatus(newStatus);
		incidenceService.update(incidencia);
	}
	
	@Entonces("^el estado se cambia correctamente$")
	public void el_estado_se_cambia_correctamente() {
		Incidence i = incidenceService.findOne(incidencia.get_id());
		assertTrue(i.getStatus().equals(newStatus));
	}
}
