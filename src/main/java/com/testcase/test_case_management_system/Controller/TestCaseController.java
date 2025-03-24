package com.testcase.test_case_management_system.Controller;

import com.testcase.test_case_management_system.Dto.*;

import com.testcase.test_case_management_system.Dto.TestCaseResponse.TestCaseResponseDTO;
import com.testcase.test_case_management_system.Dto.TestCaseResponse.TestCasesResponseDTO;
import com.testcase.test_case_management_system.Repository.TestCaseRepository;
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

    private final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private TestCaseService testCaseService;

    @Operation(summary = "Get all test cases", description = "Fetches all the test cases")
    @GetMapping("/testcases")
    public ResponseEntity<TestCasesResponseDTO> getAllTestCases(
            @RequestParam @Valid Optional<String> status,
            @RequestParam @Valid Optional<String> priority,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        TestCasesResponseDTO responseDTO = new TestCasesResponseDTO();
        List<TestCaseDTO> testCases = testCaseService.findAllTestCases(status, priority, page, size);
        responseDTO.setList(testCases);
        logger.info("Fetched {} test cases successfully with status: {} and priority: {}", testCases.size(),
                status.orElse("Any"), priority.orElse("Any"));
        responseDTO.setMessage("Test cases retrieved successfully.");
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    /*
     * Create test Case
     */
    @Operation(summary = "Create new test case", description = "Create new test case")
    @PostMapping("/testcases")
    public ResponseEntity<TestCaseResponseDTO> createTestCases(@RequestBody @Valid TestCaseRequestDTO testCaseDTO) {
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        TestCaseDTO responseDto = testCaseService.createTestCase(testCaseDTO);

        logger.info("New test case created with ID: {}", responseDto.getId());
        responseDTO.setMessage("New test case created successfully with ID: " + responseDto.getId());
        responseDTO.setStatus(true);
        responseDTO.setTestCaseDTO(responseDto);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

    }

    /*
     * Find single test case
     */
    @Operation(summary = "Get  test case", description = "Fetch test case by its id")
    @GetMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> getTestCase(@PathVariable String id) {
        TestCaseDTO testCaseDto = testCaseService.findTestCasebyId(id);
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        responseDTO.setMessage("Test case retrieved successfully.");
        logger.info("Test case with ID: {} retrieved successfully", id);
        responseDTO.setTestCaseDTO(testCaseDto);
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    /*
     * Delete testCase By Id
     */
    @Operation(summary = "Delete test case", description = "Delete test case by its id")
    @DeleteMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> deleteTestCase(@PathVariable String id) throws Exception {
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        TestCaseDTO testCaseDTO = testCaseService.removeTestCasebyId(id);
        responseDTO.setTestCaseDTO(testCaseDTO);
        responseDTO.setMessage("Test case removed successfully.");
        logger.info("Test case with ID: {} removed successfully", id);
        responseDTO.setStatus(true);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

    }

    /*
     * Update test case
     */

    @Operation(summary = "Update Test Case", description = "Update test case by its id")
    @PutMapping("/testcases/{id}")
    public ResponseEntity<TestCaseResponseDTO> updateTestCase(@PathVariable String id,
                                                            @RequestBody @Valid TestCaseRequestDTO testCaseDTO) {
        TestCaseDTO response = testCaseService.updateTestCasebyId(id, testCaseDTO);
        TestCaseResponseDTO responseDTO = new TestCaseResponseDTO();
        responseDTO.setMessage("Test case updated successfully with ID: " + id);
        logger.info("Test case with ID: {} updated successfully", id);
        responseDTO.setStatus(true);
        responseDTO.setTestCaseDTO(response);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

    }

}
