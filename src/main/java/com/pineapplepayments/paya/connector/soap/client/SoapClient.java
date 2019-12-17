package com.pineapplepayments.paya.connector.soap.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.pineapplepayments.paya.connector.soap.config.AuthHeader;
import com.pineapplepayments.paya.connector.soap.model.AuthGatewayCertification;
import com.pineapplepayments.paya.connector.soap.model.AuthGatewayCertificationResponse;
import com.pineapplepayments.paya.connector.soap.model.AuthGatewayHeader;
import com.pineapplepayments.paya.connector.soap.model.GetCertificationTerminalSettings;
import com.pineapplepayments.paya.connector.soap.model.GetCertificationTerminalSettingsResponse;
import com.pineapplepayments.paya.connector.soap.model.ProcessSingleCertificationCheck;
import com.pineapplepayments.paya.connector.soap.model.ProcessSingleCertificationCheckResponse;

public class SoapClient extends WebServiceGatewaySupport {

    @Value("${soap.request.action.name}")
    private String soapAction;

    @Value("${is.production.env}")
    private Boolean isProdEnv;

    /*
     * 
     */
    public GetCertificationTerminalSettingsResponse getCertificationTerminalSettings(AuthGatewayHeader header) {
        GetCertificationTerminalSettings request = new GetCertificationTerminalSettings();
        GetCertificationTerminalSettingsResponse response = null;
        try {
            response = (GetCertificationTerminalSettingsResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(request, new AuthHeader(header,
                            soapAction + (isProdEnv ? "GetTerminalSettings" : "GetCertificationTerminalSettings")));
        } catch (Exception e) {
            response = null;
            e.printStackTrace();
        }
        return response;
    }

    /*
     * 
     */
    public AuthGatewayCertificationResponse authGatewayCertification(String dataPacket, AuthGatewayHeader header) {

        AuthGatewayCertification request = new AuthGatewayCertification();
        request.setDataPacket(dataPacket);
        AuthGatewayCertificationResponse response = null;
        try {
            response = (AuthGatewayCertificationResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(request, new AuthHeader(header,
                            soapAction + (isProdEnv ? "AuthGateway" : "AuthGatewayCertification")));
        } catch (Exception e) {
            response = null;
            e.printStackTrace();
        }

        return response;
    }

    /*
     * Calling the paya method processSingleCertificationCheck
     */
    public ProcessSingleCertificationCheckResponse processSingleCertificationCheck(String dataPacket, AuthGatewayHeader header) {

        ProcessSingleCertificationCheck request = new ProcessSingleCertificationCheck();
        request.setDataPacket(dataPacket);
        ProcessSingleCertificationCheckResponse response = null;
        try {
            response = (ProcessSingleCertificationCheckResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(request, new AuthHeader(header,
                            soapAction + (isProdEnv ? "ProcessSingleCheck" : "ProcessSingleCertificationCheck")));
        } catch (Exception e) {
            response = null;
            e.printStackTrace();
        }

        return response;
    }

}