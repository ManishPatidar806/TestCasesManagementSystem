package com.testcase.test_case_management_system.Model;


import com.testcase.test_case_management_system.Enum.Priority;
import com.testcase.test_case_management_system.Enum.Status;


import jakarta.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;

@Document("test_cases")
public class TestCase {

    @Id
    @Field(targetType = FieldType.STRING)
    private String id;

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    @Indexed
    private Status status;

    @NotEmpty
    @Indexed
    private Priority priority;

    private LocalDate createdAt;

    private LocalDate updatedAt;




    /*
     * Getter And Setter
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
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

    public String getTitle() {
        return title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
