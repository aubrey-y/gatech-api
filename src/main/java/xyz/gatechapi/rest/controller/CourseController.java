package xyz.gatechapi.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.gatechapi.rest.dto.Course;
import xyz.gatechapi.rest.service.CourseService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/v1")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> courses = this.courseService.findAllCourses();
        if(courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(String code) {
        List<Course> courses = this.courseService.findCoursesByCode(code);
        if(courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
