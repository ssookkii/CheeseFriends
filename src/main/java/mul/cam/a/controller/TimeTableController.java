package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.TimeTableDto;
import mul.cam.a.service.TimeTableService;

@RestController
public class TimeTableController {
	
	@Autowired
	TimeTableService service;
	
	@GetMapping(value="subTimeList")
	public List<TimeTableDto> subTimeList(String teacher) {
		System.out.println("TimeTableController subTimeList()" + new Date());
		System.out.println(teacher);
		List<TimeTableDto> dto = service.subTimeList(teacher);
		System.out.println(dto);
		return dto;
	}
	@PostMapping(value="timeTableAdd")
	public String timeTableAdd(TimeTableDto dto) {
		System.out.println("TimeTableController TimeTableDto()" + new Date());
		
		// 강의시간 중복입력 확인
		boolean duplicate = service.timeDuplicateCheck(dto);
		if(duplicate) {
			return "duplicate";
		}
		
		boolean isS = service.timeTableAdd(dto);
		if(isS) {
			return "success";
		}else {
			return "fail";
		}
	}

}
