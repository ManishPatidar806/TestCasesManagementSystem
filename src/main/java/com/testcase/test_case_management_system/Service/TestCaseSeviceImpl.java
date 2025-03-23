package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import com.testcase.test_case_management_system.Exception.CommonException;
import com.testcase.test_case_management_system.Exception.ResourceNotFoundException;
import com.testcase.test_case_management_system.Model.TestCase;
import com.testcase.test_case_management_system.Repository.TestCaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            throw new ResourceNotFoundException("No Test case Found");
        }
        List<TestCaseDTO> response = new ArrayList<>();
        for (TestCase aCase : testCase) {
            response.add(modelMapper.map(aCase, TestCaseDTO.class));
        }
        return response;
    }

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto) throws CommonException {
        try {
            TestCase testCase = modelMapper.map(testCaseDto, TestCase.class);
            testCase.setCreatedAt(LocalDate.now());
            testCase.setUpdatedAt(LocalDate.now());
            TestCase testCase1 = testCaseRepository.save(testCase);
            return modelMapper.map(testCase1, TestCaseDTO.class);
        }catch (Exception exception){
            throw new CommonException("TestCase Created Failed");
        }
        }

    public TestCaseDTO findTestCasebyId(String id) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);
        if (testCase.isEmpty()) {
            throw new ResourceNotFoundException("TestCase is Not found for this id : " + id);
        }
        return modelMapper.map(testCase, TestCaseDTO.class);
    }


    public TestCaseDTO removeTestCasebyId(String id) throws CommonException {
        try{
            Optional<TestCase> testCase = testCaseRepository.findById(id);
            if (testCase.isEmpty()) {
                throw new ResourceNotFoundException(" Remove Unsuccessfully. TestCase is Not found for this id : " + id);
            }
            testCaseRepository.deleteById(id);
            return modelMapper.map(testCase, TestCaseDTO.class);
        }catch(Exception exception){
            throw new CommonException("Remove TestCase Failed");
        }

    }

    @Override
    public TestCaseDTO updateTestCasebyId(String id, TestCaseDTO testCaseDTO) throws ResourceNotFoundException, CommonException {
        try {
            Optional<TestCase> response = Optional.ofNullable(testCaseRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Update Failed. TestCase is Not found for this id : " + id)));
            TestCase testCase = modelMapper.map(testCaseDTO, TestCase.class);
            testCase.setId(response.get().getId());
            testCase.setUpdatedAt(LocalDate.now());
            testCaseRepository.save(testCase);
            return testCaseDTO;
        }catch (Exception exception){
            throw new CommonException("Update Data Failed");
        }
    }

}
