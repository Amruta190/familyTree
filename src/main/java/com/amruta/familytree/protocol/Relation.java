package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "fatherId",
        "motherId",
        "spouseId",
        "gender",
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Relation
{
    @JsonProperty("fatherId")
    private String fatherId;
    @JsonProperty("motherId")
    private String motherId;
    @JsonProperty("spouseId")
    private String spouseId;
    @JsonProperty("gender")
    private String gender;
}
