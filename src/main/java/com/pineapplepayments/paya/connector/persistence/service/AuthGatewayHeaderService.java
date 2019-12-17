package com.pineapplepayments.paya.connector.persistence.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapplepayments.paya.connector.soap.model.AuthGatewayHeader;
import com.pineapplepayments.paya.connector.soap.model.ObjectFactory;

@Service
@Transactional(readOnly = true)
public class AuthGatewayHeaderService {

    @Value("${soap.request.username}")
    private String userName;

    @Value("${soap.request.password}")
    private String password;

    public AuthGatewayHeader prepareAuthHeader(Long terminalId) {
        ObjectFactory factory = new ObjectFactory();
        AuthGatewayHeader authRequest = factory.createAuthGatewayHeader();
        authRequest.setUserName(userName);
        authRequest.setPassword(password);
        authRequest.setTerminalID(terminalId.intValue());
        return authRequest;
    }

}
