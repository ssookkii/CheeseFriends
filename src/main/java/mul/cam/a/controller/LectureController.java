package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.AdminQuestionDto;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.ListParam;
import mul.cam.a.service.LectureService;

@RestController
public class LectureController {

	@Autowired
	LectureService service;
	
	@ResponseBody
	@GetMapping(value="lecturelist")
	
	public Map<String, Object> lecturelist(ListParam param) {
		System.out.println("LectureController lecturelist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = pn * 10;
		int end = (pn + 1) * 10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<LectureDto> list = service.lectureList(param);
		
		int len = service.getAllLecture(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	
		
		return map;
	}
	
	
	@ResponseBody
	@PostMapping(value="writeLecture")
	public String writeLecture(LectureDto bbs) {
		System.out.println("LectureController writeLecture " + new Date());
		
		boolean b = service.writeLecture(bbs);
		
		if(b == false) {
			return "NO";
		}

		return "YES";
	}
	
	@ResponseBody
	@GetMapping(value="getLecture")
	public LectureDto getLecture(Integer seq) {
		System.out.println("LectureController getLecture " + new Date());
		
		return service.getLecture(seq);
	}

}
