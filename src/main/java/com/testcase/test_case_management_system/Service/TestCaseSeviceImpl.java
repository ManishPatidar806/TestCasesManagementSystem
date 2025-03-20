package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.CommonException;
import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import com.testcase.test_case_management_system.Exception.ResourceNotFoundException;
import com.testcase.test_case_management_system.Model.TestCase;
import com.testcase.test_case_management_system.Repository.TestCaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestCaseSeviceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<TestCaseDTO> findAllTestCases() {
        List<TestCase> testCase = testCaseRepository.findAll();
        if (testCase.isEmpty()) {
            throw new ResourceNotFoundException("No Test case Found");
        }
        List<TestCaseDTO> response = new ArrayList<>();
        for (TestCase aCase : testCase) {
            response.add(modelMapper.map(aCase, TestCaseDTO.class));
        }
        return response;
    }

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto){
        TestCase testCase = modelMapper.map(testCaseDto, TestCase.class);
        testCase.setCreatedAt(LocalDate.now());
        testCase.setUpdatedAt(LocalDate.now());
        TestCase testCase1 = testCaseRepository.save(testCase);
        return modelMapper.map(testCase1, TestCaseDTO.class);
    }

    public TestCaseDTO findTestCasebyId(String id) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);
        if (testCase.isEmpty()) {
            throw new ResourceNotFoundException("TestCase is Not found for this id : " + id);
        }
        return modelMapper.map(testCase, TestCaseDTO.class);
    }


    public TestCaseDTO removeTestCasebyId(String id) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);
        if (testCase.isEmpty()) {
            throw new ResourceNotFoundException(" Remove Unsuccessfully. TestCase is Not found for this id : " + id);
        }
        testCaseRepository.deleteById(id);
        return modelMapper.map(testCase, TestCaseDTO.class);
    }

    @Override
    public TestCaseDTO updateTestCasebyId(String id, TestCaseDTO testCaseDTO) throws ResourceNotFoundException {
        Optional<TestCase> response = Optional.ofNullable(testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update Failed. TestCase is Not found for this id : " + id)));
        TestCase testCase = modelMapper.map(testCaseDTO, TestCase.class);
        testCase.setId(response.get().getId());
        testCase.setUpdatedAt(LocalDate.now());
        testCaseRepository.save(testCase);
        return testCaseDTO;
    }


}
