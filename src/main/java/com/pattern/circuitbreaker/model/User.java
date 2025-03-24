package com.pattern.circuitbreaker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String identifier;
    private String name;
    private String lastName;
    private String email;
    private String university;
}
