package com.amruta.familytree.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Relation
{
    @Column(name = "father_id", table = "relation")
    private Long fatherId;
    @Column(name = "mother_id", table = "relation")
    private Long motherId;
    @Column(name = "spouse_id", table = "relation")
    private Long spouseId;
    @Column(name = "gender", table = "relation")
    private String gender;
}
