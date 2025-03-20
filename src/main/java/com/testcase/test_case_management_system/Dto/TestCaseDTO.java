package com.testcase.test_case_management_system.Dto;

import com.testcase.test_case_management_system.CustomAnnotation.ValueOfEnum;
import com.testcase.test_case_management_system.Enum.Priority;
import com.testcase.test_case_management_system.Enum.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class TestCaseDTO {
    private String id;
    @NotEmpty(message = "Titile is required")
    private String title;

    private String description;

    @NotNull(message = "Status Can't be Null")
    @ValueOfEnum(enumClass = Status.class, message = "Allowed values: Pending,InProgress,Passed,Failed")
    private String status;

    @NotNull(message = "Priority Can't be Null")
    @ValueOfEnum(enumClass = Priority.class , message = "Allowed values: Low,Medium,High")
    private String priority;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
