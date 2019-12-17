package com.pineapplepayments.paya.connector.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapplepayments.paya.connector.soap.client.SoapClient;
import com.pineapplepayments.paya.connector.soap.model.AuthGatewayCertificationResponse;

@Service
@Transactional(readOnly = true)
public class AuthGatewayService {

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private AuthGatewayHeaderService authGatewayHeaderService;

    public AuthGatewayCertificationResponse getAuthGateway(String dataPacket, Long terminalId) {

        AuthGatewayCertificationResponse authResponse;

        authResponse = soapClient.authGatewayCertification(dataPacket, authGatewayHeaderService.prepareAuthHeader(terminalId));

        return authResponse;
    }

}
