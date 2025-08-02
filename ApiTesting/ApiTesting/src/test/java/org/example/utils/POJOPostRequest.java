package org.example.utils;

import lombok.Data;

@Data
public class POJOPostRequest {

    String id;
    String name;
    String job;
    String[] location;
}
