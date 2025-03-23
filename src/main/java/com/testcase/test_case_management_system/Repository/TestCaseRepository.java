package com.testcase.test_case_management_system.Repository;

import com.testcase.test_case_management_system.Model.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase ,String> {

    Page<TestCase> findByStatusAndPriority(Optional<String> status, Optional<String> priority , Pageable pageable);
    Page<TestCase> findByStatus(Optional<String> status ,  Pageable pageable);
    Page<TestCase> findByPriority(Optional<String> priority , Pageable pageable);
}
