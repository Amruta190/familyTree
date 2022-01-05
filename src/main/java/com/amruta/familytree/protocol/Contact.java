package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "firstName",
        "lastName",
        "emailId",
        "primaryPhoneNumber",
        "secondaryPhoneNumber"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Contact
{
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("primaryPhoneNumber")
    private String primaryPhoneNumber;
    @JsonProperty("secondaryPhoneNumber")
    private String secondaryPhoneNumber;
}
