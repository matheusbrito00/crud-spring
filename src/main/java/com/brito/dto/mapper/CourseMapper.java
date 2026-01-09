package com.brito.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.brito.dto.CourseRecordDTO;
import com.brito.dto.LessonDTO;
import com.brito.enums.Category;
import com.brito.model.Course;

@Component
public class CourseMapper {

    public CourseRecordDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons().stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getVideoUrl()))
                .toList();

        return new CourseRecordDTO(course.getId(), course.getName(), course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(CourseRecordDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());

        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "FRONT-END" -> Category.FRONTEND;
            case "BACK-END" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Unknown category value: " + value);
        };
    }
}
