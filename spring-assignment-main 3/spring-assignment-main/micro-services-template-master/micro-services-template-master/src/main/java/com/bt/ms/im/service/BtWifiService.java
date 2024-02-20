package com.bt.ms.im.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entity.Response;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;
import com.bt.ms.im.mapper.Mapper;
import com.bt.ms.im.repo.DnpRepository;


@Service
public class BtWifiService {

	private Logger logger = LoggerFactory.getLogger(BtWifiService.class);
	
	@Autowired
	private DnpRepository dnpRepository;

	@Autowired
	private Mapper mapper;
	
	public Response getUuid(Request getwifirequest) throws Exception { 
		logger.info("entered service layer uuid");
		ClientProfileV1ResEntity data = dnpRepository.getClientProfileFromXml(getwifirequest);
		 Response cntrlRep = mapper.Uuiddata(data);
		return cntrlRep;
	} 
	public Response getcfsid(Request cfsidrequest) throws Exception {

		logger.info("entered service of cfsid");
		ClientProfileV1ResEntity cfsiddata = dnpRepository.getClientProfileFromXml(cfsidrequest);
	    Response cntrlRepcfsid = mapper.Cfsiddata(cfsiddata);		
		return cntrlRepcfsid;
	}

}
 
