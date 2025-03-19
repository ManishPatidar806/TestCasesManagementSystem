package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseService {

    public List<TestCaseDTO> findAllTestCases();

    public void createTestCase(TestCaseDTO testCaseDto);

    public TestCaseDTO findTestCasebyId(String id);

    public boolean removeTestCasebyId(String id) throws Exception;

    public boolean updateTestCasebyId(String id , TestCaseDTO testCaseDTO);


}
