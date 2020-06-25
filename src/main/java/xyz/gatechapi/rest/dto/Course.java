package xyz.gatechapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String code;

    private int credits;

    private int id;

    @JsonIgnore
    @JsonProperty("last_updated")
    private Object last_updated;

    private String name;

    private String prerequisites;

    private String restrictions;

    private Seats seats;

    private Seats waitlist;

    @JsonProperty("lastUpdated")
    public String getLast_updated() {
        if(this.last_updated instanceof LinkedHashMap) {
            LinkedHashMap lastUpdated = (LinkedHashMap) this.last_updated;
            return Timestamp.ofTimeSecondsAndNanos((long) lastUpdated.get("seconds"), (int) lastUpdated.get("nanos")).toString();
        }
        else {
            return this.last_updated.toString();
        }
    }
}
