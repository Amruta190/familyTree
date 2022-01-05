package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "contact",
        "relation",
        "username",
        "password"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Profile
{
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("relation")
    private Relation relation;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public Profile()
    {
    }

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
