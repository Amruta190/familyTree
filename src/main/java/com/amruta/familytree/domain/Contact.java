package com.amruta.familytree.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Long contactId;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member memberId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "sur_name")
    private String surname;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "mail_id")
    private String mailId;
    @Column(name = "primary_phone_number")
    private String primaryPhoneNumber;
    @Column(name = "secondary_phone_number")
    private String secondaryPhoneNyumber;
}
