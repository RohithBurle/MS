package com.bt.ms.im.mapper;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.bt.ms.im.entity.Response;
import com.bt.ms.im.entitydnp.Characterstics;
import com.bt.ms.im.entitydnp.ClientProfileV1Entity;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;
import com.bt.ms.im.entitydnp.ClientServiceV1Entity;
import com.bt.ms.im.exception.handler.standardexception.ForbiddenException;

public class MapperTest {

	@InjectMocks
	private Mapper mapper;

	@BeforeEach 
	void setUp() {
		MockitoAnnotations.openMocks(this);
	} 
	
	ClientProfileV1ResEntity data = new ClientProfileV1ResEntity();
	ClientProfileV1Entity v1Entity = new ClientProfileV1Entity();
	ClientServiceV1Entity clientService = new ClientServiceV1Entity();
	List<ClientServiceV1Entity> clientServiceList = new ArrayList<>();
	
	@Test
	void testUuiddata_Success() throws Exception {
		
		clientService.setServiceCode("BTWIFI:DEFAULT");
		clientService.setStatus("ACTIVE");
		clientService.setKey("uuid123");
		
		clientServiceList.add(clientService);
		data.setClientProfileV1(v1Entity);
		data.getClientProfileV1().setClientServicelist(clientServiceList);

		Response response = mapper.Uuiddata(data);

		assertNotNull(response);
		assertEquals("uuid123", response.getUuid());
	}

	@Test
	void testUuiddata_EmptyData() throws Exception {
		
		Response response = mapper.Uuiddata(data);
		assertNull(response);
	}

	@Test
	void testUuiddata_NoMatchingService() {
		clientService.setServiceCode("OtherService");
		clientService.setStatus("ACTIVE");
		clientServiceList.add(clientService);
		data.setClientProfileV1(v1Entity);
		data.getClientProfileV1().setClientServicelist(clientServiceList);

		assertThrows(ForbiddenException.class, () -> mapper.Uuiddata(data));
	}

	@Test
	void testCfsiddata_Success() throws Exception {
		clientService.setServiceCode("CONTENTFILTERING:DEFAULT");
		clientService.setStatus("ACTIVE");
		List<Characterstics> characteristics = new ArrayList<>();
		Characterstics charData = new Characterstics("CFSID", "cfsid123");
		characteristics.add(charData);		
		clientService.setCharacterstics(characteristics);
		List<ClientServiceV1Entity> clientServiceList = new ArrayList<>();
		clientServiceList.add(clientService);
		data.setClientProfileV1(v1Entity);
		data.getClientProfileV1().setClientServicelist(clientServiceList);

		Response response = mapper.Cfsiddata(data);

		assertNotNull(response);
		assertEquals("cfsid123", response.getCsfid());
	}
 
	@Test
	void testCfsiddata_EmptyData() throws Exception {
		ClientProfileV1ResEntity data = new ClientProfileV1ResEntity();
		Response response = mapper.Cfsiddata(data);
		assertNull(response);
	}
 
	@Test
	void testCfsiddata_NoMatchingService() {
		clientService.setServiceCode("OtherService");
		clientService.setStatus("ACTIVE");
		clientServiceList.add(clientService);
		data.setClientProfileV1(v1Entity);
		data.getClientProfileV1().setClientServicelist(clientServiceList);

		assertThrows(Exception.class, () -> mapper.Cfsiddata(data));
	}
}
