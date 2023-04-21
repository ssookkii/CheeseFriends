package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.SubjectDto;
import mul.cam.a.dto.UserDto;
import mul.cam.a.service.SubjectService;

@RestController
public class SubjectController {
	
	@Autowired
	SubjectService service;
	
	@GetMapping(value="sublist")
	public Map<String, Object> sublist(ListParam param) {
		System.out.println("SubjectController sublist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = (pn * 15);
		int end = (pn + 1) * 15;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<SubjectDto> list = service.getSubList(param);
		
		int len = service.getAllSub(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	// react에 보낼 때
		
		return map;
	}
	@GetMapping(value="getSub")
	public SubjectDto getSub(String subCode) {
		System.out.println("SubjectController getSub()" + new Date());
		
		return service.getSub(subCode);
	}
	@PostMapping(value="subUpdate")
	public String subUpdate(SubjectDto sub) {
		System.out.println("SubjectController subUpdate()" + new Date());
				
		boolean isS = service.subUpdate(sub);
		
		if(isS) {
			return "success";
		}
		return "fail";
	}
	@PostMapping(value="subDelete")
	public String subDelete(String subCode) {
		System.out.println("SubjectController subDelete()" + new Date());
				
		boolean isS = service.subDelete(subCode);
		
		if(isS) {
			return "success";
		}
		return "fail";
	}
	@GetMapping(value="getIdList")
	public List<UserDto> getIdList(String auth) {
		System.out.println("SubjectController getIdList()" + new Date());
		
		List<UserDto> teacher = service.getIdList(auth);
		return teacher;
	}
}