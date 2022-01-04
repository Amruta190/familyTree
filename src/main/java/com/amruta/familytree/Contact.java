package com.amruta.familytree;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class Contact {
	private String firstName;
	private String lastName;
	private String emailId;
	private String primaryPhoneNumber;
	private String secondaryPhoneNumber;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPrimaryPhoneNumber()
	{
		return primaryPhoneNumber;
	}

	public void setPrimaryPhoneNumber(String primaryPhoneNumber)
	{
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	public String getSecondaryPhoneNumber()
	{
		return secondaryPhoneNumber;
	}

	public void setSecondaryPhoneNumber(String secondaryPhoneNumber)
	{
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
}
