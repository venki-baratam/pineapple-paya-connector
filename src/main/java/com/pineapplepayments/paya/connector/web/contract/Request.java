
package com.pineapplepayments.paya.connector.web.contract;

import javax.validation.constraints.NotNull;

public class Request {

    @NotNull
    protected Long terminalId;

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

}
