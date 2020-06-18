package xyz.gatechapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String code;

    private int credits;

    private int id;

    private Timestamp last_updated;

    private String name;

    private String prerequisites;

    private String restrictions;

    private Seats seats;

    private Seats waitlist;

    @JsonProperty("lastUpdated")
    public String getLast_updated() {
        return this.last_updated.toString();
    }
}
