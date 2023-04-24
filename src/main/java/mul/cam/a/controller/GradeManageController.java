package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.GradeDto;
import mul.cam.a.dto.SubjectDto;
import mul.cam.a.service.GradeManageService;

@RestController
public class GradeManageController {
	
	@Autowired
	GradeManageService service;
	
	@GetMapping(value="subTeacherList")
	public List<SubjectDto> subTeacherList(String teacher) {
		System.out.println("GradeManageController subTeacherList()" + new Date());
		System.out.println(teacher);
		return service.subTeacherlist(teacher);
	}
	
	@GetMapping(value="subStudentList")
	public List<GradeDto> subStudentList(SubjectDto dto) {
		System.out.println("GradeManageController subStudentList()" + new Date());
		System.out.println(dto);
		List<GradeDto> student = service.subStudentList(dto);
		System.out.println(student);
		return student;
				
	}
}
