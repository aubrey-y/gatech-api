package xyz.gatechapi.rest.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAspect {

    private List<String> attributes;
    private String campusQuery;
    private String codeQuery;
    private String creditsQuery;
    private String formatQuery;
    private String gradeBasisQuery;
    private String nameQuery;
    private String registrationNumberQuery;
    private String sectionQuery;
    private String startTimeQuery;
    private String endTimeQuery;
    private String startDateQuery;
    private String endDateQuery;
    private String locationQuery;
    private String scheduleQuery;
    private String instructorNameQuery;
    private String instructorEmailQuery;

    public List<String> getQueries() {
        return Lists.newArrayList(this.campusQuery, this.codeQuery, this.creditsQuery, this.formatQuery,
                this.gradeBasisQuery, this.nameQuery, this.registrationNumberQuery, this.sectionQuery,
                this.startTimeQuery, this.endTimeQuery,this.startDateQuery, this.endDateQuery, this.locationQuery,
                this.scheduleQuery, this.instructorNameQuery, this.instructorEmailQuery);
    }
}
