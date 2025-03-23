package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import com.testcase.test_case_management_system.Exception.CommonException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TestCaseService {

    public List<TestCaseDTO> findAllTestCases(Optional<String> status, Optional<String> priority, int page, int size);

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto) throws CommonException;

    public TestCaseDTO findTestCasebyId(String id);

    public TestCaseDTO removeTestCasebyId(String id) throws Exception;

    public TestCaseDTO updateTestCasebyId(String id , TestCaseDTO testCaseDTO) throws CommonException;


}
