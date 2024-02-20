package com.bt.ms.im.mapper;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bt.ms.im.entity.Response;
import com.bt.ms.im.entitydnp.Characterstics;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;
import com.bt.ms.im.entitydnp.ClientServiceV1Entity;
import com.bt.ms.im.exception.StandardError;
import com.bt.ms.im.exception.handler.standardexception.ForbiddenException;
import com.bt.ms.im.util.RequestValidator;

@Component
public class Mapper {

	private Logger logger = LoggerFactory.getLogger(Mapper.class);

	public Response Uuiddata(ClientProfileV1ResEntity data) throws Exception { 
        logger.info("Entered into mapper");
        if(data.getClientProfileV1() == null) {
        	return null;
        } 
        if ( data.getClientProfileV1().getClientServicelist() == null
                || data.getClientProfileV1().getClientServicelist().isEmpty()) {
            return null;
        } 
        
        Response cntrlRep = new Response();
        Optional<ClientServiceV1Entity> instanceResponse = findUuid(data.getClientProfileV1().getClientServicelist());

        if (instanceResponse.isPresent()) {
            cntrlRep.setUuid(instanceResponse.get().getKey());
        } else {
            throw new ForbiddenException("52", "Forbidden user");
        }
        return cntrlRep;
    } 

    private Optional<ClientServiceV1Entity> findUuid(List<ClientServiceV1Entity> clientServiceInstances) {
        for (ClientServiceV1Entity instance : clientServiceInstances) {
            if (("BTWIFI:DEFAULT".equals(instance.getServiceCode()) || "BTROPENZONE".equals(instance.getServiceCode()))
                    && "ACTIVE".equals(instance.getStatus())) {
                return Optional.of(instance);
            }
        } 
        throw new ForbiddenException("52", "Forbidden user");
    }
    
    
	public Response Cfsiddata(ClientProfileV1ResEntity cfsiddata) throws Exception {
		if (cfsiddata.getClientProfileV1() == null) {
			return null;
		}

		if (cfsiddata.getClientProfileV1().getClientServicelist() == null
				|| cfsiddata.getClientProfileV1().getClientServicelist().size() == 0) {
			return null; 
		}

		Optional<String> cfsidinstanceresponse = findCfsid(cfsiddata.getClientProfileV1().getClientServicelist());

		Response cntrlRepcfsid = new Response();
		cntrlRepcfsid.setCsfid(cfsidinstanceresponse.get());
		return cntrlRepcfsid;

	}

	private Optional<String> findCfsid(List<ClientServiceV1Entity> clientServiceInstances) throws Exception {
		for (ClientServiceV1Entity instance : clientServiceInstances) {
			if ("CONTENTFILTERING:DEFAULT".equals(instance.getServiceCode())) {
				for (Characterstics characteristic : instance.getCharacterstics()) {
					if ("CFSID".equals(characteristic.getName())) {
						return Optional.ofNullable(characteristic.getValue());
					}
				}
			}
		}
		throw new Exception("CFSID not found for the specified criteria");
	}
}
