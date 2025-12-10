package com.brito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brito.model.Course;
import com.brito.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private CourseRepository courseRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
        return courseRepository.save(course);
    }

}