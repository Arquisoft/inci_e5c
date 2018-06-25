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
	private ObjectId operador;

	public Notification() {
		
	}
	

	public Notification(TipoIncidencia type, double valor, String name, ObjectId operador) {
		super();
		this.type = type;
		this.valor = valor;
		this.name= name;
		this.operador = operador;
	}

	

	public ObjectId get_id() {
		return _id;
	}


	public void set_id(ObjectId _id) {
		this._id = _id;
	}


	public TipoIncidencia getType() {
		return type;
	}
	public void setType(TipoIncidencia type) {
		this.type = type;
	}
	
	
	
	
	
	public void setOperador(ObjectId operador) {
		this.operador = operador;
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
	
	public ObjectId getOperador() {
		return operador;
	}


	@Override
	public String toString() {
		return "Notification [type=" + type + ", valor=" + valor + ", name=" + name + "]";
	}






	
	
	
	

}
