package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseService {

    public List<TestCaseDTO> findAllTestCases();

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto);

    public TestCaseDTO findTestCasebyId(String id);

    public boolean removeTestCasebyId(String id);

    public boolean updateTestCasebyId(String id , TestCaseDTO testCaseDTO);


}
