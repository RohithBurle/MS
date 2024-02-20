package com.bt.ms.im.entitydnp;

import jakarta.xml.bind.annotation.XmlElement;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "getClientProfileV1Res",namespace = "http://capabilities.nat.bt.com/v2/dnp/manageprofile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientProfileV1ResEntity {

    @XmlElement(name = "clientProfileV1")
    private ClientProfileV1Entity clientProfileV1; 

	public ClientProfileV1Entity getClientProfileV1() {
		return clientProfileV1;
	}

	public void setClientProfileV1(ClientProfileV1Entity clientProfileV1) {
		this.clientProfileV1 = clientProfileV1;
	}
}
