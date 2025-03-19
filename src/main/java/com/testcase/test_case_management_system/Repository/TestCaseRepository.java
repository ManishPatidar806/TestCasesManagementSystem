package com.testcase.test_case_management_system.Repository;

import com.testcase.test_case_management_system.Model.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase ,String> {
}
