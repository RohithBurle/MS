package com.bt.ms.im.entitydnp;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClientProfileV1Entity {

	@XmlElement(name = "clientServiceInstanceV1")
	private List<ClientServiceV1Entity> clientServicelist;

	public List<ClientServiceV1Entity> getClientServicelist() {
		return clientServicelist;
	}

	public void setClientServicelist(List<ClientServiceV1Entity> clientServicelist) {
		this.clientServicelist = clientServicelist;
	}
}
