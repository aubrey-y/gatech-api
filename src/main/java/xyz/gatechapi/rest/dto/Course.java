package xyz.gatechapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String code;

    private int credits;

    private int id;

    private LocalDateTime lastUpdated;

    private String name;

    private String prerequisites;

    private String restrictions;

    private Seats seats;

    private Seats waitlistSeats;
}
