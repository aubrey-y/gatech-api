/* Copyright © 2020 Gatech-API - All Rights Reserved. Subject to terms of the PolyForm Noncommercial License. */
package xyz.gatechapi.rest.service;

import com.backendless.Backendless;
import com.backendless.persistence.DataQueryBuilder;
import org.springframework.stereotype.Service;
import xyz.gatechapi.rest.dao.Class;
import xyz.gatechapi.rest.dao.Course;
import xyz.gatechapi.rest.dto.CourseAspect;
import xyz.gatechapi.rest.enums.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    public CourseService() {
    }
    
    public static DataQueryBuilder getBaseCourseDqb() {
        return DataQueryBuilder.create().addRelated("classes").setPageSize(100);
    }
    
    public static DataQueryBuilder getBaseClassDqb() {
        return DataQueryBuilder.create().setPageSize(100);
    }

    public List<Course> findAllCourses() {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().setWhereClause("1=1");
        return this.pageUntilEndCourse(queryBuilder);
    }

    public List<Course> findCoursesByCode(String code) {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().setWhereClause(String.format("code = '%s'", code));
        return this.pageUntilEndCourse(queryBuilder);
    }

    public Course findCourseByCrn(String registrationNumber) {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().setWhereClause(String.format("registrationNumber = '%s'", registrationNumber));
        List<Course> results = Backendless.Data.of(Course.class).find(queryBuilder);
        if(results.isEmpty()) {
            return null;
        }
        else {
            return results.get(0);
        }
    }

    public List<Course> findCourses(CourseAspect courseAspect) {
        List<String> whereClause = new ArrayList<>();
        if(courseAspect.getAttributes() != null && !courseAspect.getAttributes().isEmpty()) {
            for(String attribute: courseAspect.getAttributes()) {
                whereClause.add(String.format("attributes like '%%%s%%'", attribute));
            }
        }
        for(String query: courseAspect.getQueries()) {
            if(query != null) {
                whereClause.add(query);
            }
        }
        DataQueryBuilder queryBuilder = getBaseCourseDqb().setWhereClause(String.join(" and ", whereClause));

        return this.pageUntilEndCourse(queryBuilder);
    }

    public Set<Object> getUniqueAttributes() {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().addProperty("attributes").addGroupBy("attributes");
        Set<Object> uniqueAttributes = new HashSet<>();
        pageUntilEndCourse(queryBuilder).forEach((Course course) -> uniqueAttributes.addAll(Arrays.asList(course.getAttributes().split(","))));
        return uniqueAttributes;
    }

    public Set<Object> getUniqueMajors() {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().addProperty("code").addGroupBy("code");
        Set<Object> uniqueMajors = new HashSet<>();
        pageUntilEndCourse(queryBuilder).forEach((Course course) -> uniqueMajors.add(course.getCode().split(" ")[0]));
        return uniqueMajors;
    }

    public Set<Object> getUniqueGradeBases() {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().addProperty("gradeBasis").addGroupBy("gradeBasis");
        Set<Object> uniqueGradeBases = new HashSet<>();
        pageUntilEndCourse(queryBuilder).forEach((Course course) -> uniqueGradeBases.addAll(Arrays.asList(course.getGradeBasis().split(""))));
        return uniqueGradeBases;
    }

    public Set<Object> getUniqueInstructorNames() {
        DataQueryBuilder queryBuilder = getBaseClassDqb().addProperty("instructorName").addGroupBy("instructorName");
        Set<Object> uniqueInstructorNames = new HashSet<>();
        pageUntilEndClass(queryBuilder).forEach((Class c) -> uniqueInstructorNames.add(c.getInstructorName()));
        return uniqueInstructorNames;
    }

    public Set<Object> getUniqueInstructorEmails() {
        DataQueryBuilder queryBuilder = getBaseClassDqb().addProperty("instructorEmail").addGroupBy("instructorEmail");
        Set<Object> uniqueInstructorEmails = new HashSet<>();
        pageUntilEndClass(queryBuilder).forEach((Class c) -> uniqueInstructorEmails.add(c.getInstructorEmail()));
        return uniqueInstructorEmails;
    }

    public Set<Object> getDefaultUniqueCourse(Option option) {
        DataQueryBuilder queryBuilder = getBaseCourseDqb().addProperty(option.name()).addGroupBy(option.name());
        Set<Object> unique = new HashSet<>();
        pageUntilEndCourse(queryBuilder).forEach((Course course) -> unique.add(course.get(option)));
        return unique;
    }

    public Set<Object> getDefaultUniqueClass(Option option) {
        DataQueryBuilder queryBuilder = getBaseClassDqb().addProperty(option.name()).addGroupBy(option.name());
        Set<Object> unique = new HashSet<>();
        pageUntilEndClass(queryBuilder).forEach((Class c) -> unique.add(c.get(option)));
        return unique;
    }

    private List<Course> pageUntilEndCourse(DataQueryBuilder queryBuilder) {
        List<Course> result = new ArrayList<>();
        List<Course> response = Backendless.Data.of(Course.class).find(queryBuilder);
        while(!response.isEmpty()) {
            result.addAll(response);
            queryBuilder.prepareNextPage();
            response = Backendless.Data.of(Course.class).find(queryBuilder);
        }
        return result;
    }

    private List<Class> pageUntilEndClass(DataQueryBuilder queryBuilder) {
        List<Class> result = new ArrayList<>();
        List<Class> response = Backendless.Data.of(Class.class).find(queryBuilder);
        while(!response.isEmpty()) {
            result.addAll(response);
            queryBuilder.prepareNextPage();
            response = Backendless.Data.of(Class.class).find(queryBuilder);
        }
        return result;
    }
}
