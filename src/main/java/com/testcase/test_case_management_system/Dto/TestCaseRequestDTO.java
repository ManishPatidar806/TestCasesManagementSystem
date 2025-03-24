package com.testcase.test_case_management_system.Dto;

import com.testcase.test_case_management_system.CustomAnnotation.ValueOfEnum;
import com.testcase.test_case_management_system.Enum.Priority;
import com.testcase.test_case_management_system.Enum.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TestCaseRequestDTO {

    @NotEmpty(message = "Titile is required")
    private String title;

    private String description;

    @NotNull(message = "Status cannot be null")
    @ValueOfEnum(enumClass = Status.class, message = "Permissible values: Pending, InProgress, Passed, Failed")
    private String status;

    @NotNull(message = "Priority cannot be null")
    @ValueOfEnum(enumClass = Priority.class, message = "Permissible values: Low, Medium, High")
    private String priority;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
