
package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.LearningDto;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.LectureParam;
import mul.cam.a.service.LearningService;

@RestController
public class LearningController {

	@Autowired
	LearningService service;
	
	@ResponseBody
	@GetMapping(value="learninglist")
	public List<LearningDto> learningList(LectureParam param) {
		System.out.println("LearningController learningList " + new Date());
		
		List<LearningDto> learninglist = service.learningList(param);
		
		return learninglist;
	}
	
	@ResponseBody
	@PostMapping(value="writeLearning")
	public String writeLearning(LearningDto bbs) {
		System.out.println("LearningController writeLearning " + new Date());
		
		boolean b = service.writeLearning(bbs);
		
		if(b == false) {
			return "NO";
		}

		return "YES";
	}

	@ResponseBody
	@GetMapping(value = "getLearning")
	public LearningDto getLearning(Integer seq) {
		System.out.println("LearningController getLearning " + new Date());
		
		return service.getLearning(seq);
	}
}

