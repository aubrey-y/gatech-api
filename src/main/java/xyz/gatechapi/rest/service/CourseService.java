package xyz.gatechapi.rest.service;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.stereotype.Service;
import xyz.gatechapi.rest.dto.Course;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private Firestore firestore;

    public CourseService(Firestore firestore) {
        this.firestore = firestore;
    }

    public Map<String, Object> findAllCourses() {
        try {
            return this.firestore.collection("CLASSES_ALL")
                    .get()
                    .get()
                    .getDocuments()
                    .stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList())
                    .get(0);
        }
        catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public List<Course> findCoursesByCode(String code) {
        try {
            return this.firestore.collection("CLASSES_MASTER")
                    .whereEqualTo("code", code)
                    .get()
                    .get()
                    .getDocuments()
                    .stream()
                    .map(document -> document.toObject(Course.class))
                    .collect(Collectors.toList());
        }
        catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }
}
