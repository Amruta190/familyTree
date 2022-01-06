package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "contact",
        "relation",
        "username",
        "password"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProfileRequest
{
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("relation")
    private RelationRequest relation;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
