package com.testcase.test_case_management_system.Controller;

import com.testcase.test_case_management_system.Dto.CommonResponse;
import com.testcase.test_case_management_system.Dto.TestCaseDTO;
import com.testcase.test_case_management_system.Dto.TestCaseResponseDTO;
import com.testcase.test_case_management_system.Dto.TestCasesResponseDTO;

import com.testcase.test_case_management_system.Service.TestCaseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestCaseController {
//    private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);
    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/testcases")
    public ResponseEntity<TestCasesResponseDTO> getAllTestCases(){
       List<TestCaseDTO> testCases = testCaseService.findAllTestCases();
    TestCasesResponseDTO responseDTO = new TestCasesResponseDTO();
    responseDTO.setList(testCases);
    responseDTO.setMessage("List Fetch Successfully");
    responseDTO.setStatus(true);
      return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /*
    * Create test Case
    * */
    @PostMapping("/testcases")
    public ResponseEntity<CommonResponse> createTestCases(@RequestBody @Valid TestCaseDTO testCaseDTO ){
        TestCaseDTO testCase = testCaseService.createTestCase(testCaseDTO);
        CommonResponse responseDTO = new CommonResponse();
        responseDTO.setMessage("Data Created Successfully");
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }
    /*
    * Find single test case
    * */

    @GetMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> getTestCase(@PathVariable String id){
        TestCaseDTO testCaseDto = testCaseService.findTestCasebyId(id);
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        responseDTO.setMessage("Data Fetch Successfully");
        responseDTO.setTestCaseDTO(testCaseDto);
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

/*
* Delete testCase By Id
* */
@DeleteMapping("/testcases/{id}")
public ResponseEntity<CommonResponse> deleteTestCase(@PathVariable String id){
    boolean success  = testCaseService.removeTestCasebyId(id);
    CommonResponse responseDTO = new CommonResponse();
    responseDTO.setMessage("Data Remove Successfully");
    responseDTO.setStatus(true);
    return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
}

    /*
     * Update test case
     * */

    @PutMapping("/testcases/{id}")
    public ResponseEntity<CommonResponse> updateTestCase(@PathVariable String id ,@RequestBody TestCaseDTO testCaseDTO){
        boolean response = testCaseService.updateTestCasebyId(id,testCaseDTO);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage("Update TestCase Successfully");
        commonResponse.setStatus(true);
        return new ResponseEntity<>(commonResponse, HttpStatus.ACCEPTED);
    }


}
