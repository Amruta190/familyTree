package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RelationRequest
{
    @JsonProperty("fatherId")
    private Long fatherId;
    @JsonProperty("motherId")
    private Long motherId;
    @JsonProperty("spouseId")
    private Long spouseId;
}
