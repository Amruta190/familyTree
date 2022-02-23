package com.amruta.familytree.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "member")
@SecondaryTables(
        { @SecondaryTable(name = "contact", pkJoinColumns = @PrimaryKeyJoinColumn(name = "member_id")),
                @SecondaryTable(name = "relation", pkJoinColumns = @PrimaryKeyJoinColumn(name = "member_id")) })
@Data
@DynamicUpdate
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name= "tree_id")
    private Long treeId;
    @Embedded
    private Contact contact;
    @Embedded
    private Relation relation;

}
