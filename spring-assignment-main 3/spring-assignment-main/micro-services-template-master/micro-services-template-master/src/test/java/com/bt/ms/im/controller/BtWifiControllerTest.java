package com.bt.ms.im.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entity.Response;
import com.bt.ms.im.service.BtWifiService;
import com.bt.ms.im.util.RequestValidator;

public class BtWifiControllerTest {

	@Mock
	RequestValidator requestvalidator;

	@Mock
	BtWifiService wifiService;

	@InjectMocks
	BtWifiController btWifiController;
  
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	String apigwtrackingheader = "96bb97fa-b941-46bb-8c4e-86c616c28a13";
	String uuid = "0ab297cc-b2b6-1033-9f36-9a1c1857c201";
	String cfsid = "0ab7cc-b2b6-1033-9f36-9a1c1857c271";

	@Test
	public void getWifiResponseTest_WithUuidandCfsid() throws Exception {
		 

		
		Request testrequest = new Request();
		testrequest.setAPIGW_Tracking_Header(apigwtrackingheader);
		testrequest.setX_Profile_Guide(cfsid);
		testrequest.setX_Consumer_Digital_Id_Ref(uuid);
				
		Response testresponse = new Response();
		testresponse.setUuid(uuid);
		testresponse.setCsfid(cfsid);
		
		doNothing().when(requestvalidator).validateGetWifiRequest(testrequest);
		
		//mocked
		when(wifiService.getUuid(any())).thenReturn(testresponse);
		when(wifiService.getcfsid(any())).thenReturn(testresponse);
		
		ResponseEntity<Response> wifiResponseTest = btWifiController.getWifiResponse(apigwtrackingheader, uuid, cfsid);
		
		assertEquals(testresponse.getCsfid(),wifiResponseTest.getBody().getCsfid());
		assertEquals(testresponse.getUuid(), wifiResponseTest.getBody().getUuid());
	}

  	@Test
  	public void getWifiTest_WithUuid() throws Exception {
  		
  		Request uuidReq = new Request();
  		uuidReq.setAPIGW_Tracking_Header(apigwtrackingheader);
  		uuidReq.setX_Profile_Guide(uuid);
  		
  		Response uuidRes = new Response();
  		uuidRes.setUuid(uuid);
  		
  		doNothing().when(requestvalidator).validateGetWifiRequest(uuidReq);
  		
  		when(wifiService.getUuid(any())).thenReturn(uuidRes);
  		
  		ResponseEntity<Response> uuidResTest = btWifiController.getWifiResponse(apigwtrackingheader, uuid, null);
  		
  		assertEquals(uuidRes, uuidResTest.getBody());
  	}


	@Test
	public void getWifiTest_WithCfsid() throws Exception {
		
		Request uuidReq = new Request();
		uuidReq.setAPIGW_Tracking_Header(apigwtrackingheader);
		uuidReq.setX_Consumer_Digital_Id_Ref(cfsid);
		
		Response cfsidRes = new Response();
		cfsidRes.setUuid(cfsid);
		
		doNothing().when(requestvalidator).validateGetWifiRequest(uuidReq);
		
		when(wifiService.getcfsid(any())).thenReturn(cfsidRes);
		
		ResponseEntity<Response> cfsidResTest = btWifiController.getWifiResponse(apigwtrackingheader, null, cfsid);
		
		assertEquals(cfsidRes, cfsidResTest.getBody());
	}
	
	   @Test
	    public void getWifiResponseTest_WithoutBoth() throws Exception {
	        Request testrequest = new Request();
	        testrequest.setAPIGW_Tracking_Header(apigwtrackingheader);

	        Response testresponse = new Response();
	        testresponse.setUuid(uuid);
	        testresponse.setCsfid(cfsid);

	        doNothing().when(requestvalidator).validateGetWifiRequest(testrequest);

	        // Mocked
	        when(wifiService.getUuid(any())).thenReturn(testresponse);
	        when(wifiService.getcfsid(any())).thenReturn(testresponse);

	        ResponseEntity<Response> wifiResponseTest = btWifiController.getWifiResponse(apigwtrackingheader, null, null);

	        assertEquals(testresponse.getCsfid(), wifiResponseTest.getBody().getCsfid());
	        assertEquals(testresponse.getUuid(), wifiResponseTest.getBody().getUuid());
	    }
}


