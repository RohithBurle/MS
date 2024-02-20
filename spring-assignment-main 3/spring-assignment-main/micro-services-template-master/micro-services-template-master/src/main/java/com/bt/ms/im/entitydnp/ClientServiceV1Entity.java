package com.bt.ms.im.entitydnp;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ClientServiceV1Entity {

	@XmlElement(name ="serviceCode")
	private String serviceCode;
	@XmlElement(name ="key")
	private String key;
	@XmlElement(name ="status")
	private String status;
	@XmlElement(name ="characteristic")
	private List<Characterstics> characterstics;
	
//	public List<Characterstics> getCharacterstics() {
//		return characterstics;
//	}

	public void setCharacterstics(List<Characterstics> characterstics) {
		this.characterstics = characterstics;
	}

	public ClientServiceV1Entity(String serviceCode, String key, String status) {
		super();
		this.serviceCode = serviceCode;
		this.key = key;
		this.status = status;
	}
	
	public ClientServiceV1Entity() {
		super();
	}

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Characterstics> getCharacterstics() {
		return characterstics;
	}


}
