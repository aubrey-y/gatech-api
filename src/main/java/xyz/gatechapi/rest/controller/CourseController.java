/* Copyright © 2020 Gatech-API - All Rights Reserved. Subject to terms of the PolyForm Noncommercial License. */
package xyz.gatechapi.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.gatechapi.rest.dao.Course;
import xyz.gatechapi.rest.dto.CourseAspect;
import xyz.gatechapi.rest.enums.Option;
import xyz.gatechapi.rest.service.CourseService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/api/v1")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = this.courseService.findAllCourses();
        if(courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/code")
    public ResponseEntity<List<Course>> getCoursesByCode(String code) {
        List<Course> courses = this.courseService.findCoursesByCode(code);
        if(courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/crn")
    public ResponseEntity<Course> getCoursesByRegistrationNumber(String registrationNumber) {
        Course course = this.courseService.findCourseByCrn(registrationNumber);
        if(course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/options")
    public ResponseEntity<Set<Object>> getOptions(Option option) {
        Set<Object> options = new HashSet<>();
        switch (option) {
            case attributes:
                options.addAll(this.courseService.getUniqueAttributes());
                break;
            case major:
                options.addAll(this.courseService.getUniqueMajors());
                break;
            case gradeBasis:
                options.addAll(this.courseService.getUniqueGradeBases());
                break;
            case instructorName:
                options.addAll(this.courseService.getUniqueInstructorNames());
                break;
            case instructorEmail:
                options.addAll(this.courseService.getUniqueInstructorEmails());
                break;
            case campus:
            case credits:
            case format:
            case name:
            case section:
                options.addAll(this.courseService.getDefaultUniqueCourse(option));
                break;
            case endDate:
            case endTime:
            case location:
            case schedule:
            case startDate:
            case startTime:
                options.addAll(this.courseService.getDefaultUniqueClass(option));
                break;
        }
        options.remove("");
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(@RequestBody CourseAspect courseAspect) {
        List<Course> courses = this.courseService.findCourses(courseAspect);
        if(courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
