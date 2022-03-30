package com.example.MappingSolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MappingSolutions.dto.Employe;
import com.example.MappingSolutions.dto.EmployeDTO;
import com.example.MappingSolutions.dto.EmployeDTOResponse;
import com.example.MappingSolutions.dto.EmployeGetDTO;
import com.google.gson.Gson;

@RestController
public class EmployeController {

	@Autowired
	private RestTemplate restTemplate;
	String url = "http://localhost:9090/emp-service/create";
	String getUrl = "http://localhost:9090/emp-service/";
	
	

	@PostMapping("/create")
	public EmployeDTOResponse createEmploye(@RequestBody EmployeDTO employeDTO) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("test", "test12");

		EmployeDTOResponse fromJson = null;

		HttpEntity<EmployeDTO> entity = new HttpEntity<EmployeDTO>(employeDTO, headers);

		try {

			System.out.println("for test");
			// EmployeDTOResponse postForObject = restTemplate.postForObject(url, entity,
			// EmployeDTOResponse.class);

			ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, entity, String.class);
			Gson gson = new Gson();

			fromJson = gson.fromJson(postForEntity.getBody(), EmployeDTOResponse.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		
		
		return fromJson;

	}

	@GetMapping("/{id}")
	public Employe getEmpInfo(@PathVariable int id) {

		// HttpEntity<String> entity = new HttpEntity<>(headers);

		// ResponseEntity<EmployeDTOResponse> emp = restTemplate.exchange(getUrl+id,
		// HttpMethod.GET, entity, EmployeDTOResponse.class);

		 EmployeGetDTO employe = restTemplate.getForObject(getUrl+id, EmployeGetDTO.class);

		return employe.getEmploye();

	}

}
