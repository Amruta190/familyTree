package com.amruta.familytree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relation {
	private Long father_id;
	private Long mother_id;
	private Long spouse_id;
	private String gender;
}
