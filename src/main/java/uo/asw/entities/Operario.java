package uo.asw.entities;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operarios")
public class Operario {

	@Id
	private ObjectId _id;
	
	private String username;
	private String password;
	
	public Operario() {
	}
	
	public Operario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public ObjectId get_id() {
		return _id;
	}
	
	public void set_id(ObjectId _id) {
		this._id = _id;
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

	@Override
	public String toString() {
		return "Operario [username=" + username + ", password=" + password + "]";
	}

	

}
