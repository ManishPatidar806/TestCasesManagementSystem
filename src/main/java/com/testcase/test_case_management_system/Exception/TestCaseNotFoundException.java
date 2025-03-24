package com.testcase.test_case_management_system.Exception;

public class TestCaseNotFoundException extends RuntimeException{
    public TestCaseNotFoundException (String message){
        super(message);
    }
}
