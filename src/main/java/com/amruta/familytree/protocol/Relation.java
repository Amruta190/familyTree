package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "fatherId",
        "motherId",
        "spouseId",
        "gender",
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

    public Relation()
    {
    }

    public String getFatherId()
    {
        return fatherId;
    }

    public void setFatherId(String fatherId)
    {
        this.fatherId = fatherId;
    }

    public String getMotherId()
    {
        return motherId;
    }

    public void setMotherId(String motherId)
    {
        this.motherId = motherId;
    }

    public String getSpouseId()
    {
        return spouseId;
    }

    public void setSpouseId(String spouseId)
    {
        this.spouseId = spouseId;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
