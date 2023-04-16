package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.TestEduDto;
import mul.cam.a.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping(value = "idcheck")
	public String idcheck(String id) {
		System.out.println("UserController idcheck() " + new Date());
		
		System.out.println("id: " + id);
		
		boolean isS = service.idcheck(id);
		if(isS == true) {
			return "NO";
		}
		
		return "YES";
	}
	
	@GetMapping(value = "subjectlist")
	public List<TestEduDto> subjectlist(String edu_code){
		System.out.println("UserController subjectlist() " + new Date());
		
		List<TestEduDto> list = service.subjectlist(edu_code);
		
		System.out.println(list.toString());
		
		return list;
		
	}
	
}
