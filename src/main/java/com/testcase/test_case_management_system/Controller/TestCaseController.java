package com.testcase.test_case_management_system.Controller;

import com.testcase.test_case_management_system.Dto.*;
import com.testcase.test_case_management_system.Service.TestCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "TestCase Controller", description = "All Operation related to test cases")
@RestController
@RequestMapping("/api")
public class TestCaseController {

    private  Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseService testCaseService;

    @Operation(summary = "Get all test cases", description = "Fetches all the test cases")
    @GetMapping("/testcases")
    public ResponseEntity<TestCasesResponseDTO> getAllTestCases(
            @RequestParam Optional<String> status,
            @RequestParam Optional<String> priority,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws CommonException {
        TestCasesResponseDTO responseDTO = new TestCasesResponseDTO();
            List<TestCaseDTO> testCases = testCaseService.findAllTestCases(status, priority, page, size);
            responseDTO.setList(testCases);
            logger.info("TestCases Fetch Successfully");
            responseDTO.setMessage("TestCases Fetch Successfully");
            responseDTO.setStatus(true);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /*
     * Create test Case
     * */
    @Operation(summary = "Create new test case", description = "Create new test case")
    @PostMapping("/testcases")
    public ResponseEntity<TestCaseResponseDTO> createTestCases(@RequestBody @Valid TestCaseDTO testCaseDTO) throws CommonException {
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        try {
            TestCaseDTO responseDto = testCaseService.createTestCase(testCaseDTO);
            responseDTO.setMessage("TestCase Created Successfully");
            logger.info("TestCases Created Successfully");
            responseDTO.setStatus(true);
            responseDTO.setTestCaseDTO(responseDto);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new CommonException("TestCase Created Failed");
        }

    }

    /*
     * Find single test case
     * */
    @Operation(summary = "Get  test case", description = "Fetch test case by its id")
    @GetMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> getTestCase(@PathVariable String id) {
        TestCaseDTO testCaseDto = testCaseService.findTestCasebyId(id);
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        responseDTO.setMessage("TestCase Fetch Successfully");
        logger.info("TestCase Fetch Successfully");
        responseDTO.setTestCaseDTO(testCaseDto);
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    /*
     * Delete testCase By Id
     * */
    @Operation(summary = "Delete test case", description = "Delete test case by its id")
    @DeleteMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> deleteTestCase(@PathVariable String id) throws CommonException {
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        try {
            TestCaseDTO testCaseDTO = testCaseService.removeTestCasebyId(id);
            responseDTO.setTestCaseDTO(testCaseDTO);
            responseDTO.setMessage("TestCase Remove Successfully");
            logger.info("TestCases Remove Successfully");
            responseDTO.setStatus(true);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new CommonException("Remove TestCase Failed");
        }

    }

    /*
     * Update test case
     * */

    @Operation(summary = "Update Test Case", description = "Update test case by its id")
    @PutMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> updateTestCase(@PathVariable String id, @RequestBody @Valid TestCaseDTO testCaseDTO) throws CommonException {
        try {
            TestCaseDTO response = testCaseService.updateTestCasebyId(id, testCaseDTO);
            TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
            responseDTO.setMessage("TestCase Updated Successfully");
            logger.info("TestCase Updated Successfully");
            responseDTO.setStatus(true);
            responseDTO.setTestCaseDTO(response);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new CommonException("Update Data Failed");
        }
    }


}
