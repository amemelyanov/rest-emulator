package org.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private String name;
    private Integer age;
    private Mother mother;
    private List<String> children;
    private boolean married;
    private String dog;
}