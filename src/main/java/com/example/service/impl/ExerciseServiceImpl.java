package com.example.service.impl;

import com.example.models.entities.Exercise;
import com.example.models.service.ExerciseServiceModel;
import com.example.repository.ExerciseRepository;
import com.example.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public void addEx(ExerciseServiceModel exerciseServiceModel) {
        Exercise map = modelMapper.map(exerciseServiceModel, Exercise.class);
        exerciseRepository.save(map);

    }
}
