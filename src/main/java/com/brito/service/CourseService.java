package com.brito.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.brito.dto.CourseRecordDTO;
import com.brito.dto.mapper.CourseMapper;
import com.brito.enums.Category;
import com.brito.exception.RecordNotFoundException;
import com.brito.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseRecordDTO> list() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseRecordDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseRecordDTO create(@Valid @NotNull CourseRecordDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseRecordDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseRecordDTO course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.name());
                    courseFound.setCategory(Category.FRONTEND);
                    return courseMapper.toDTO(courseRepository.save(courseFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
