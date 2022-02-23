package com.amruta.familytree.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
@DynamicUpdate
public class Contact
{
    @Column(name = "first_name", table = "contact")
    private String firstName;
    @Column(name = "last_name", table = "contact")
    private String lastName;
    @Column(name = "email_id", table = "contact")
    private String emailId;
    @Column(name = "gender", table = "contact")
    private String gender;
    @Column(name = "primary_phone_number", table = "contact")
    private String primaryPhoneNumber;
    @Column(name = "secondary_phone_number", table = "contact")
    private String secondaryPhoneNumber;

}
