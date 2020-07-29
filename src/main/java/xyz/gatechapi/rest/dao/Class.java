package xyz.gatechapi.rest.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.gatechapi.rest.enums.Option;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    private LocalDateTime endDate;
    private String endTime;
    private String location;
    private String schedule;
    private LocalDateTime startDate;
    private String startTime;
    private String instructorEmail;
    private String instructorName;

    public Object get(Option option) {
        switch (option) {
            case endDate:
                return this.endDate;
            case endTime:
                return this.endTime;
            case location:
                return this.location;
            case schedule:
                return this.schedule;
            case startDate:
                return this.startDate;
            case startTime:
                return this.startTime;
            default:
                throw new RuntimeException(String.format("Unsupported option %s", option));
        }
    }
}
