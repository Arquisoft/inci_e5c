package uo.asw.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidencias")
public class Incidence {

	public Incidence() {}
	
	public static final List<IncidenceStatus> estados = Arrays.asList(IncidenceStatus.values());

	private String name, description, identificador;

	private Agent agent;

	private Date date;
	private IncidenceStatus status;

	private List<String> tags;

	private Map<String, String> properties;

	public Incidence(String identificador, String name, String description, Agent agent, List<String> tags) {

		this.identificador = identificador;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.date = new Date();
		this.status = IncidenceStatus.OPENED;
		this.agent = agent;
		this.date = new Date();
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getTags() {
		return tags;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public IncidenceStatus getStatus() {
		return status;
	}

	public void setStatus(IncidenceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + identificador + "#user=" + agent.getNombre()  + "#indicenceName=" + name
				+ "#description=" + description + "#tags=<" + tags + ">]";
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Date getDate() {
		return date;
	}

}
