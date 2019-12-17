package com.pineapplepayments.paya.connector.soap.config;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import com.pineapplepayments.paya.connector.soap.model.AuthGatewayHeader;

public class AuthHeader implements WebServiceMessageCallback {
    private AuthGatewayHeader header;
    private String soapAction;

    public AuthHeader(AuthGatewayHeader header, String soapAction) {
        this.header = header;
        this.soapAction = soapAction;
    }

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {

        SoapMessage soapMessage = (SoapMessage) message;
        SoapHeader soapHeader = soapMessage.getSoapHeader();
        soapMessage.setSoapAction(soapAction);

        try {
            JAXBContext context = JAXBContext.newInstance(AuthGatewayHeader.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(header, soapHeader.getResult());

        } catch (JAXBException e) {
            throw new IOException("error while marshalling authentication.");
        }
    }
}