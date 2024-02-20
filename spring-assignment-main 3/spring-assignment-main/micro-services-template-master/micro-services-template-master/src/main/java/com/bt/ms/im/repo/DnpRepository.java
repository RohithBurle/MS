package com.bt.ms.im.repo;


import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DnpRepository {

	@Autowired
	RestTemplate restTemplate;
	public ClientProfileV1ResEntity getClientProfileFromXml(Request dnpRequest) {
		ResponseEntity<ClientProfileV1ResEntity> responseEntity = restTemplate.getForEntity("http://localhost:8000/getData", ClientProfileV1ResEntity.class);
	return responseEntity.getBody();
	} 
}
