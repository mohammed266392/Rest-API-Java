package com.example.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
//@JsonIgnoreProperties({"field2"}) c'est la même chose que mettre sur le fiels @JsonIgnore
@JsonFilter("SommeBeanFilter")
public class SomeBean {


    private String field1;

//    @JsonIgnore
    private String field2;

    private String field3;
}
