package com.testcase.test_case_management_system.Dto.TestCaseResponse;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;

import java.util.List;

public class TestCasesResponseDTO {
    private List<TestCaseDTO> list;
    private boolean status;
    private String message;

    public List<TestCaseDTO> getList() {
        return list;
    }

    public void setList(List<TestCaseDTO> list) {
        this.list = list;
    }

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
