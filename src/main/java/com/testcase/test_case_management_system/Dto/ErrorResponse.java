package com.testcase.test_case_management_system.Dto;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private Map<String ,String> message;
    private boolean status ;


    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
