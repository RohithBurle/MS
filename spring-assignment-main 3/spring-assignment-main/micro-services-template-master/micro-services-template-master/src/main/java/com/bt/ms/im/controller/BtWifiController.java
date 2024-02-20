package com.bt.ms.im.controller;

import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entity.Response;
import com.bt.ms.im.service.BtWifiService;
import com.bt.ms.im.util.RequestValidator;
import com.bt.ms.im.exception.handler.standardexception.BadRequestException;

import java.util.ArrayList;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bt-consumer/v2/wifi-eligibility")
public class BtWifiController {

    @Autowired
    private RequestValidator requestValidator;
    @Autowired
    private BtWifiService wifiService;

    @GetMapping("/")
    public ResponseEntity<Response> getWifiResponse(
            @RequestHeader(value = "APIGW_Tracking_Header",required = false) String apigwTrackingHeader,
            @RequestHeader(value = "X-Profile-Guide", required = false) String X_Profile_Guide,
            @RequestHeader(value = "X-Consumer-DigitalId-Ref", required = false) String X_Consumer_Digital_Id_Ref) throws Exception {

        Request getwifirequest = new Request();
        getwifirequest.setAPIGW_Tracking_Header(apigwTrackingHeader);
        getwifirequest.setX_Profile_Guide(X_Profile_Guide);
        getwifirequest.setX_Consumer_Digital_Id_Ref(X_Consumer_Digital_Id_Ref);

        requestValidator.validateGetWifiRequest(getwifirequest);

        Response cntrlRep = new Response();

        if (StringUtils.isNotBlank(X_Profile_Guide) && StringUtils.isNotBlank(X_Consumer_Digital_Id_Ref)) {
            cntrlRep.setUuid(wifiService.getUuid(getwifirequest).getUuid());
            cntrlRep.setCsfid(wifiService.getcfsid(getwifirequest).getCsfid());
        } else if (StringUtils.isNotBlank(X_Profile_Guide)) {
            cntrlRep = wifiService.getUuid(getwifirequest);
        } else if (StringUtils.isNotBlank(X_Consumer_Digital_Id_Ref)) {
            cntrlRep = wifiService.getcfsid(getwifirequest);
        }

        return ResponseEntity.ok(cntrlRep); 
    } 
} 
