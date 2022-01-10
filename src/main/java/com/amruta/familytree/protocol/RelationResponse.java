package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RelationResponse
{
    @JsonProperty("father")
    private Person father;
    @JsonProperty("mother")
    private Person mother;
    @JsonProperty("spouse")
    private Person spouse;
    @JsonProperty("children")
    private List<Person> children;
}
