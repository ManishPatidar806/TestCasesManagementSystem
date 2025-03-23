package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TestCaseService {

    public List<TestCaseDTO> findAllTestCases(Optional<String> status, Optional<String> priority, int page, int size);

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto);

    public TestCaseDTO findTestCasebyId(String id);

    public TestCaseDTO removeTestCasebyId(String id) throws Exception;

    public TestCaseDTO updateTestCasebyId(String id , TestCaseDTO testCaseDTO);


}
