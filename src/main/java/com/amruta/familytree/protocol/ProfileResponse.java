package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProfileResponse
{
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("relation")
    private RelationResponse relation;
}
