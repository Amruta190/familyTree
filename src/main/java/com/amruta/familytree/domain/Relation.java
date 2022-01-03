package com.amruta.familytree.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Relation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long relationId;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member memberId;
    private Long fatherId;
    private Long motherId;
    private Long spouseId;
    private Long gender;
}
