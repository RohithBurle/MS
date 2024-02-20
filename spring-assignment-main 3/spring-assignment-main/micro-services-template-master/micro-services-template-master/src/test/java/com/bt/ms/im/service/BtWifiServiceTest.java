package com.bt.ms.im.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entity.Response;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;
import com.bt.ms.im.mapper.Mapper;
import com.bt.ms.im.repo.DnpRepository;


public class BtWifiServiceTest {

	@Mock
	private DnpRepository dnpRepository;

	@Mock
	private Mapper mapper;

	@InjectMocks
	BtWifiService btWifiService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testgetUuid() throws Exception {
		
		Request uuidSampleReq = new Request();
		ClientProfileV1ResEntity mockData = new ClientProfileV1ResEntity();
		when(dnpRepository.getClientProfileFromXml(uuidSampleReq)).thenReturn(mockData);
		
		Response mockRes = new Response(); 
		when(mapper.Uuiddata(mockData)).thenReturn(mockRes);  // mocking
		
		Response result = btWifiService.getUuid(uuidSampleReq);  // service layer call
		
		assertEquals(mockRes,result);
	}
	
	@Test
	public void testcfsid() throws Exception {
		
		Request cfsidSampleReq = new Request();
		
		ClientProfileV1ResEntity mockcfsiddata = new ClientProfileV1ResEntity();
		when(dnpRepository.getClientProfileFromXml(cfsidSampleReq)).thenReturn(mockcfsiddata);
		
		Response mockCfsidRes = new Response();
		when(mapper.Cfsiddata(mockcfsiddata)).thenReturn(mockCfsidRes);
		
		Response result = btWifiService.getcfsid(cfsidSampleReq);
		
		assertEquals(mockCfsidRes, result);
		
	}
}
