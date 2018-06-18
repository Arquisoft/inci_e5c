package uo.asw.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidencias")
public class Incidence {

	public Incidence() {}
	
	public static final List<IncidenceStatus> estados = Arrays.asList(IncidenceStatus.values());

	@Id
	private ObjectId _id;
	private String name, description;
	private Agent agent;
	private Date date;
	private IncidenceStatus status;
	private List<String> tags;
	private Map<String, String> properties;

	public Incidence(String name, String description, Agent agent, List<String> tags) {

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
		return "Incidence [id=" + _id + "#user=" + agent.getNombre()  + "#indicenceName=" + name
				+ "#description=" + description + "#tags=<" + tags + ">]";
	}

	public Date getDate() {
		return date;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

}
