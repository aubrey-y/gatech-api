package xyz.gatechapi.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.gatechapi.rest.dto.Course;


@Controller
@RequestMapping("/courseapi/v1")
public class CourseController {

    @GetMapping("/courses")
    public ResponseEntity<Course> getCourses() {
        return new ResponseEntity<>(new Course(), HttpStatus.OK);
    }
}
