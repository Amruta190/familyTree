package com.amruta.familytree.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
public class Contact
{
    @Column(name = "first_name", table = "contact")
    private String firstName;
    @Column(name = "last_name", table = "contact")
    private String lastName;
    @Column(name = "sur_name", table = "contact")
    private String surname;
    @Column(name = "dob", table = "contact")
    private Date dob;
    @Column(name = "mail_id", table = "contact")
    private String mailId;
    @Column(name = "primary_phone_number", table = "contact")
    private String primaryPhoneNumber;
    @Column(name = "secondary_phone_number", table = "contact")
    private String secondaryPhoneNumber;

}
