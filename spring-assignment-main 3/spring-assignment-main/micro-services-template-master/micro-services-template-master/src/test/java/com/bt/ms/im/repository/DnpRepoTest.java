package com.bt.ms.im.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bt.ms.im.entity.Request;
import com.bt.ms.im.entitydnp.ClientProfileV1ResEntity;
import com.bt.ms.im.repo.DnpRepository;

public class DnpRepoTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DnpRepository dnpRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetClientProfileFromXml() {
        ClientProfileV1ResEntity expectedResponse = new ClientProfileV1ResEntity(/* Initialize with expected data */);
        ResponseEntity<ClientProfileV1ResEntity> mockResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.getForEntity("http://localhost:8000/getData", ClientProfileV1ResEntity.class))
            .thenReturn(mockResponseEntity);

        Request mockReq = new Request();
        ClientProfileV1ResEntity actualResponse = dnpRepository.getClientProfileFromXml(mockReq);

        
    }
}
