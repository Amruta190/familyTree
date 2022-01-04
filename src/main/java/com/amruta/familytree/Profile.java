package com.amruta.familytree;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class Profile {
	private Contact contact;
	private Relation relation;
	private String username;
	private String password;

	public Contact getContact()
	{
		return contact;
	}

	public void setContact(Contact contact)
	{
		this.contact = contact;
	}

	public Relation getRelation()
	{
		return relation;
	}

	public void setRelation(Relation relation)
	{
		this.relation = relation;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
