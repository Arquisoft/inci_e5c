package uo.asw.entities;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operarios")
public class Operario {

	private String username;
	private String password;
	
	Set<Incidence> incidencias;

	public Operario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Set<Incidence> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Set<Incidence> incidencias) {
		this.incidencias = incidencias;
	}

	@Override
	public String toString() {
		return "Operario [username=" + username + ", password=" + password + "]";
	}

	

}
