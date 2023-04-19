package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.LectureDto;
import mul.cam.a.service.LectureService;

@Controller
public class LectureController {

	@Autowired
	LectureService service;
	
	@ResponseBody
	@GetMapping(value = "lecturelist")
	
	public List<LectureDto> lectureList(Integer seq) {
		 System.out.println("LectureController lectureList " + new Date());
		List<LectureDto> list = service.lectureList(seq);
		// System.out.println(list);
		return list;

	}
}
