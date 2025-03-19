package com.testcase.test_case_management_system.Dto;

public class CommonResponse {
    private boolean status;
    private String message;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
