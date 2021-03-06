package com.pineapplepayments.paya.connector;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapplepayments.paya.connector.contants.TransactionTypes;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionInformation;
import com.pineapplepayments.paya.connector.web.controller.TransactionController;

public class TransactionControllerTest {

    @Mock
    public TransactionController transactionController;

    @Mock
    public TransactionInformation transactionInformation;

    private MockMvc mockMvc;

    @org.junit.Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    private static String convertObjectToJson(Object object) throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objMapper.writeValueAsString(object);
    }

    @Test
    public void validateACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("A");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Validate);
        transactionInformation.setRoutingNumber(Long.valueOf(490000018));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/validate").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void processACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("A");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Authorization);
        transactionInformation.setRoutingNumber(Long.valueOf(490000018));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/authorization").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void processCreditsTranactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("A");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Credit);
        transactionInformation.setRoutingNumber(Long.valueOf(490000034));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/credit").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void voidACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("V");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Void);
        transactionInformation.setRoutingNumber(Long.valueOf(490000021));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/void").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void reverseACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("F");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Reverse);
        transactionInformation.setRoutingNumber(Long.valueOf(490000047));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/reverse").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void recurringACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("R");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Recurring);
        transactionInformation.setRoutingNumber(Long.valueOf(490000047));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/recurring").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

    @Test
    public void overrideACHTransactionTest() throws Exception {
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setTransactionGuid(UUID.randomUUID().toString());
        transactionInformation.setRequestId(UUID.randomUUID().toString());
        transactionInformation.setTransactionTime(Timestamp.valueOf(LocalDateTime.now()));
        transactionInformation.setIdentifier("O");
        transactionInformation.setTerminalId(Long.valueOf(1210));
        transactionInformation.setTransactionType(TransactionTypes.Override);
        transactionInformation.setRoutingNumber(Long.valueOf(490000018));
        transactionInformation.setAccountNumber(Long.valueOf(23242324));
        transactionInformation.setCheckNumber(Long.valueOf(34343434));
        transactionInformation.setControlChar("S");
        transactionInformation.setVerificationOnly(false);
        transactionInformation.setMicrData("T490000018T 24413815O 4456");
        transactionInformation.setAccountType("Checking");
        transactionInformation.setFirstName("Eric");
        transactionInformation.setLastName("Eric");
        transactionInformation.setAddress1("1001 Test Ave");
        transactionInformation.setAddress2("#200");
        transactionInformation.setCity("Melbourne");
        transactionInformation.setZipCode(31305);
        transactionInformation.setState("FL");
        transactionInformation.setPhoneNumber("3103022834");
        transactionInformation.setDlNumber("D12346544");
        transactionInformation.setDobYear(1998);
        transactionInformation.setCourtseyCardId("abcabcabc");
        transactionInformation.setCompanyName("abcabc");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/transaction/override").contentType("application/json")
                        .content(convertObjectToJson(transactionInformation)))
                .andDo(MockMvcResultHandlers.print()).andDo(MockMvcResultHandlers.log()).andExpect(status().isOk());

    }

}
