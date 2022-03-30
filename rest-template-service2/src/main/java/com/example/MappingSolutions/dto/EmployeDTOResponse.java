package com.example.MappingSolutions.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class EmployeDTOResponse {

	private Integer empId;
	private String companyName;
	private String address;
	private String companyCeoName;
 
	
 
}
