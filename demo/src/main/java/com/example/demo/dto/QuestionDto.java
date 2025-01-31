package com.example.demo.dto;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Answer is required")
    @Range(min = 1, max = 4, message = "Answer must be between 1 and 4")
    private Integer answer;

    @NotNull(message = "All four Options are required")
    @Size(min = 4, max = 4, message = "Options must be 4")
    private List<String> options;

    @NotBlank(message = "Explanation is required")
    private String explanation;
}
