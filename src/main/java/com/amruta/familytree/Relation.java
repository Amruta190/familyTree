package com.amruta.familytree;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class Relation {
	private Long fatherId;
	private Long motherId;
	private Long spouseId;
	private String gender;

	public Long getFatherId()
	{
		return fatherId;
	}

	public void setFatherId(Long fatherId)
	{
		this.fatherId = fatherId;
	}

	public Long getMotherId()
	{
		return motherId;
	}

	public void setMotherId(Long motherId)
	{
		this.motherId = motherId;
	}

	public Long getSpouseId()
	{
		return spouseId;
	}

	public void setSpouseId(Long spouseId)
	{
		this.spouseId = spouseId;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
}
