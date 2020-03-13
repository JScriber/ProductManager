package fr.imie.productmanager.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestError {
	
	private String message;
	
	public RestError() {
		
	}
	
	public RestError(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
