package com.brito.dto.mapper;

import org.springframework.stereotype.Component;

import com.brito.dto.CourseRecordDTO;
import com.brito.enums.Category;
import com.brito.model.Course;

@Component
public class CourseMapper {

    public CourseRecordDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseRecordDTO(course.getId(), course.getName(), "FRONTEND");
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
        course.setCategory(Category.FRONTEND);
        return course;
    }
}
