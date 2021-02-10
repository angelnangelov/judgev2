package com.example.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class ExerciseAddBindingModel {
    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseAddBindingModel() {
    }

    @Length(min = 2,message = "Very short description")
    public String getName() {
        return name;
    }

    public ExerciseAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "THe date cannot be in the future")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public ExerciseAddBindingModel setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public ExerciseAddBindingModel setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
