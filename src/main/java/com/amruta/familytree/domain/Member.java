package com.amruta.familytree.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "is_admin")
    private Boolean isAdminId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
