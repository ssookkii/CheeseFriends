
package mul.cam.a.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import mul.cam.a.MediaTypeUtiles;
import mul.cam.a.dto.LearningDto;
import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.TaskDto;
import mul.cam.a.service.LearningService;

@RestController
public class LearningController {

	@Autowired
	LearningService service;
	
	@ResponseBody
	@GetMapping(value="learninglist")
	
	public Map<String, Object> learninglist (ListParam param) {
		System.out.println("LearningController learninglist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = (pn * 10);
		int end = (pn + 1) * 10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<LearningDto> list = service.learningList(param);
		
		int len = service.getAllLearning(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	
		
		return map;
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
	
	// download
		@Autowired
		ServletContext servletContext;
		
		@RequestMapping(value = "/fileDownload")
		public ResponseEntity<InputStreamResource> download(String filename, 
					HttpServletRequest req, LearningDto bbs, TaskDto tto) throws Exception {
			System.out.println("HelloController download " + new Date());
			
			// 경로
			String path = req.getServletContext().getRealPath("/upload");
			// String path = "c:\temp";
			
			MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, filename);
			System.out.println("filename:" + filename);
			System.out.println("mediaType:" + mediaType);
			
			File file = new File(path + File.separator + filename);		// newfilename
			
			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
			
			// db 다운로드 카운트
			
			return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()) // 원본파일명
						.contentType(mediaType)
						.contentLength(file.length())
						.body(isr);					
		}
		
}

