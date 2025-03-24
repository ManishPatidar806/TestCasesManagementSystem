package com.testcase.test_case_management_system.Dto.TestCaseResponse;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;

public class TestCaseResponseDTO {
    private TestCaseDTO testCaseDTO;
    private boolean status;
    private String message;

    public TestCaseDTO getTestCaseDTO() {
        return testCaseDTO;
    }

    public void setTestCaseDTO(TestCaseDTO testCaseDTO) {
        this.testCaseDTO = testCaseDTO;
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
