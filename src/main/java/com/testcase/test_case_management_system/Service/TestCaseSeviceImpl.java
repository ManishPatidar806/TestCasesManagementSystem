package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Controller.TestCaseController;
import com.testcase.test_case_management_system.Dto.TestCaseDTO;


import com.testcase.test_case_management_system.Dto.TestCaseRequestDTO;
import com.testcase.test_case_management_system.Exception.TestCaseNotFoundException;
import com.testcase.test_case_management_system.Model.TestCase;
import com.testcase.test_case_management_system.Repository.TestCaseRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestCaseSeviceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Override
    public List<TestCaseDTO> findAllTestCases(Optional<String> status, Optional<String> priority, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<TestCase> testCase;
        if (status.isPresent() && priority.isPresent()) {
            testCase = testCaseRepository.findByStatusAndPriority(status, priority, pageable);
        } else if (status.isPresent()) {
            testCase = testCaseRepository.findByStatus(status, pageable);
        } else if (priority.isPresent()) {
            testCase = testCaseRepository.findByPriority(priority, pageable);
        } else {
            testCase = testCaseRepository.findAll(pageable);
        }
        if (testCase.isEmpty()) {
            logger.error("No Test cases found with the given criteria: status = {}, priority = {}", status.orElse("Any"), priority.orElse("Any"));
            throw new TestCaseNotFoundException("No Test cases found with the given criteria.");
        }
        List<TestCaseDTO> response = new ArrayList<>();
        for (TestCase aCase : testCase) {
            response.add(modelMapper.map(aCase, TestCaseDTO.class));
        }
        return response;
    }

    public TestCaseDTO createTestCase(TestCaseRequestDTO testCaseDto) {
            TestCase testCase = modelMapper.map(testCaseDto, TestCase.class);
            testCase.setCreatedAt(LocalDate.now());
            testCase.setUpdatedAt(LocalDate.now());
            TestCase testCase1 = testCaseRepository.save(testCase);
            return modelMapper.map(testCase1, TestCaseDTO.class);
    }

    public TestCaseDTO findTestCasebyId(String id) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);
        if (testCase.isEmpty()) {
            logger.error("TestCase not found for the given id: {}", id);
            throw new TestCaseNotFoundException("TestCase not found for the given id: " + id);
        }
        return modelMapper.map(testCase, TestCaseDTO.class);
    }


    public TestCaseDTO removeTestCasebyId(String id) {
            Optional<TestCase> testCase = testCaseRepository.findById(id);
            if (testCase.isEmpty()) {
                logger.error("Failed to remove TestCase. TestCase not found for the given id: {}", id);
                throw new TestCaseNotFoundException("Failed to remove TestCase. TestCase not found for the given id: " + id);
            }
            testCaseRepository.deleteById(id);
            return modelMapper.map(testCase, TestCaseDTO.class);

    }

    @Override
    public TestCaseDTO updateTestCasebyId(String id, TestCaseRequestDTO testCaseDTO) {
            Optional<TestCase> response = Optional.ofNullable(testCaseRepository.findById(id).orElseThrow(() -> new TestCaseNotFoundException("Update Failed. TestCase is Not found for this id : " + id)));
            TestCase testCase = modelMapper.map(testCaseDTO, TestCase.class);
            testCase.setId(response.get().getId());
            testCase.setUpdatedAt(LocalDate.now());
            testCase.setCreatedAt(response.get().getCreatedAt());
            testCaseRepository.save(testCase);
            return modelMapper.map(testCase, TestCaseDTO.class);
    }

}
