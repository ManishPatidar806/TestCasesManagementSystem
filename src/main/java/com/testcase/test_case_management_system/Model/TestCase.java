package com.testcase.test_case_management_system.Model;

import com.testcase.test_case_management_system.CustomAnnotation.ValueOfEnum;
import com.testcase.test_case_management_system.Enum.Priority;
import com.testcase.test_case_management_system.Enum.Status;


import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document("test_cases")
public class TestCase {

    @Id
    @Field(targetType = FieldType.STRING)
    private String id;

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    @ValueOfEnum(enumClass = Status.class, message = "Allowed values: Pending,InProgress,Passed,Failed")
    private Status status;

    @NotEmpty
    @ValueOfEnum(enumClass = Priority.class , message = "Allowed values: Low,Medium,High")
    private Priority priority;

    private Date  createdAt;

    private Date updatedAt;




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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
