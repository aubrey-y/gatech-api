package xyz.gatechapi.rest.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.gatechapi.rest.dto.Course;


@Api(
        name = "courseapi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "gatech-api.xyz", ownerName = "gatech-api.xyz"),
        issuers = {
                @ApiIssuer(
                        name = "firebase",
                        issuer = "https://securetoken.google.com/course-gen",
                        jwksUri = "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system.gserviceaccount.com"
                )
        }
)
@Controller
@RequestMapping("/courseapi/v1")
public class CourseController {

    @ApiMethod(name = "courses", path = "courses", httpMethod = ApiMethod.HttpMethod.GET)
    @GetMapping("/courses")
    public ResponseEntity<Course> getCourses() {
        return new ResponseEntity<>(new Course(), HttpStatus.OK);
    }
}
