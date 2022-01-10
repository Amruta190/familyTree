package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProfileResponse
{
    @JsonProperty("memberId")
    private Long memberId;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("relation")
    private RelationResponse relation;
}
