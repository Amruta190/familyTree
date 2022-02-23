package com.amruta.familytree.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tree")

@Data
@DynamicUpdate
public class Tree
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tree_id")
    private Long treeId;
    @Column(name = "tree_name")
    private String treeName;

}
