/* Copyright © 2020 Gatech-API - All Rights Reserved. Subject to terms of the PolyForm Noncommercial License. */
package xyz.gatechapi.rest.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.gatechapi.rest.enums.Option;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String code;
    private String name;
    private String section;
    private String registrationNumber;
    private String attributes;
    private int credits;
    private String gradeBasis;
    private String campus;
    private String format;
    private List<Class> classes;
    private LocalDateTime created;

    public Object get(Option option) {
        switch (option) {
            case campus:
                return this.campus;
            case credits:
                return this.credits;
            case format:
                return this.format;
            case name:
                return this.name;
            case section:
                return this.section;
            default:
                throw new RuntimeException(String.format("Unsupported option %s", option));
        }
    }
}
