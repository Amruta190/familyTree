package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Contact
{
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("primaryPhoneNumber")
    private String primaryPhoneNumber;
    @JsonProperty("secondaryPhoneNumber")
    private String secondaryPhoneNumber;
}
