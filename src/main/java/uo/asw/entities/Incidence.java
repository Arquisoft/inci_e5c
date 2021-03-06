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

	public static final List<IncidenceStatus> estados = Arrays.asList(IncidenceStatus.values());
	public static final List<TipoIncidencia> tipos = Arrays.asList(TipoIncidencia.values());
	
	@Id
	private ObjectId _id;

	private String name, description,agent;

	private Date date = new Date();
	private IncidenceStatus status;

	private List<String> tags;

	private Map<String, String> properties;


	private TipoIncidencia type;

	private Double valor;
	private Double latitud, longitud;
	
	private ObjectId operario;

	public Incidence(String name, String description, String agent, List<String> tags, TipoIncidencia type,
			Double valor) {
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.status = IncidenceStatus.OPENED;
		this.agent = agent;
		this.tags = tags;
		this.type = type;
		this.valor = valor;

	}
	
	public Incidence() {}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public static List<IncidenceStatus> getEstados() {
		return estados;
	}

	public static List<TipoIncidencia> getTipos() {
		return tipos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public String toJSON() {
		return "{ \"_id\" : \"" + _id + "\", " 
				+ " \"name\" : \"" + name + "\", " 
				+ " \"description\" : \""+ description + "\", " 
				+ " \"date\" : \"" + date + "\", " 
				+ " \"status\" : \"" + status + "\", "
				+ " \"agent\" : \"" + agent + "\", " 
				+ " \"tags\" : [" + tagsList() + "], "
				+ " \"type\" : \"" + type + "\", " 
				+ " \"valor\" : " + valor + ", "
				+ " \"latitud\" : " + latitud + ", "
				+ " \"longitud\" : " + longitud + "}";
	}

	private String tagsList() {
		String list = "";
		for (int i = 0; i < tags.size(); i++)
			list += "\"" + tags.get(i) + "\"" + ((i == tags.size() - 1) ? "" : ", ");
		return list;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + _id + "#user=" + agent + "#indicenceName=" + name + "#description="
				+ description + "#tags=<" + tags + "#indicetype=" + type + "#indiceValor=" + valor + ">]";
	}

	public Date getDate() {
		return date;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public TipoIncidencia getType() {
		return type;
	}

	public void setType(TipoIncidencia type) {
		this.type = type;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	public ObjectId getOperario() {
		return operario;
	}
	
	public void setOperario(ObjectId operario) {
		this.operario = operario;
	}
	
	public static TipoIncidencia parseTipo(String tipo) {
		return tipos.stream().filter(x -> x.toString().equals(tipo)).findFirst().get();
	}
	
	public static IncidenceStatus parseEstado(String estado) {
		return estados.stream().filter(x -> x.toString().equals(estado)).findFirst().get();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Incidence)) return false;
		Incidence i = (Incidence) o;
		return i.get_id().equals(_id);
	}

}
