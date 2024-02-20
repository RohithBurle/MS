package com.bt.ms.im.entitydnp;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Characterstics {
	
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "value")
	private String value;
	

	public Characterstics() {
		super();
	}
	public Characterstics(String name, String value) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
