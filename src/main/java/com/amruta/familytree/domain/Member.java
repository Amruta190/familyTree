package com.amruta.familytree.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member")
@SecondaryTables(
        { @SecondaryTable(name = "contact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "member_id")),
                @SecondaryTable(name = "relation", pkJoinColumns = @PrimaryKeyJoinColumn(name = "member_id")) })
@Data
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
    @Embedded
    private Contact contact;
    @Embedded
    private Relation relation;

}
