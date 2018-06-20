package uo.asw.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notificaciones")
public class Notification {
	@Id
	private ObjectId _id;
	private TipoIncidencia type;
	private double valor;
	private String name;
	

	public Notification() {
		
	}
	

	public Notification(TipoIncidencia type, double valor, String name) {
		super();
		this.type = type;
		this.valor = valor;
		this.name= name;
	}


	public TipoIncidencia getType() {
		return type;
	}
	public void setType(TipoIncidencia type) {
		this.type = type;
	}
	
	
	
	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Notification [type=" + type + ", valor=" + valor + ", name=" + name + "]";
	}






	
	
	
	

}
