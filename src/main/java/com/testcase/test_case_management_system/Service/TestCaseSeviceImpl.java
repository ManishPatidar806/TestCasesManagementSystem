package com.testcase.test_case_management_system.Service;

import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import com.testcase.test_case_management_system.Model.TestCase;
import com.testcase.test_case_management_system.Repository.TestCaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<TestCase> testCase= testCaseRepository.findAll();
        List<TestCaseDTO> response = new ArrayList<>();
        for (TestCase aCase : testCase) {
            response.add(modelMapper.map(aCase, TestCaseDTO.class));
        }
        return response;
    }

    public TestCaseDTO createTestCase(TestCaseDTO testCaseDto){
        TestCase testCase = modelMapper.map(testCaseDto ,TestCase.class);
        testCase.setCreatedAt(new Date());
        testCase.setUpdatedAt(new Date());
        TestCase testCase1 = testCaseRepository.save(testCase);
        return modelMapper.map(testCase1 ,TestCaseDTO.class);
    }

    public TestCaseDTO findTestCasebyId(String Id){
        Optional<TestCase> testCase = testCaseRepository.findById(Id);
//      if (testCase.isPresent()){
        return modelMapper.map(testCase ,TestCaseDTO.class);
    }


    public boolean removeTestCasebyId(String id){
        try {
            testCaseRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateTestCasebyId(String id, TestCaseDTO testCaseDTO) {
        Optional<TestCase> reponse = testCaseRepository.findById(id);
        TestCase testCase = modelMapper.map(testCaseDTO , TestCase.class);
        testCase.setId(reponse.get().getId());
        testCase.setUpdatedAt(new Date());
        testCaseRepository.save(testCase);
        return true;
    }


}
