package mul.cam.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import mul.cam.a.MediaTypeUtiles;
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
	
	// upload
//
//	@RequestMapping(value = "upload", method = RequestMethod.POST)
//		public String fileUpload(LectureDto bbs, 
//								@RequestParam("uploadFile")
//								MultipartFile uploadFile, 
//								HttpServletRequest req) {
//			System.out.println("LectureController fileUpload " + new Date());
//			System.out.println(bbs.toString());
//			
//			// 경로
//		    String path = "src/main/webapp/upload";
//		    // String path = "c:\temp";
//		    
//		    String filename = uploadFile.getOriginalFilename();				
//		    if (filename == null || filename.isEmpty()) {
//		        return "file upload fail";
//		    }
//		    String filepath = path + "/" + filename;
//		    
//		    System.out.println(filepath);
//		    
//		    File file = new File(filepath);
//		    
//		    try {
//		        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//		        bos.write(uploadFile.getBytes());			
//		        bos.close();
//		        
//		    } catch (Exception e) {			
//		        return "file upload fail";
//		    } 
//		    
//		    return "file upload success";
//		}
		
//		// download
//		@Autowired
//		ServletContext servletContext;
//		
//		@RequestMapping(value = "download")
//		public ResponseEntity<InputStreamResource> download(String filename, HttpServletRequest req) throws Exception {
//			System.out.println("LectureController download " + new Date());
//			
//			// 경로
//			String path = req.getServletContext().getRealPath("/upload");
//			// String path = "c:\temp";
//			
//			MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, filename);
//			System.out.println("filename:" + filename);
//			System.out.println("mediaType:" + mediaType);
//			
//			File file = new File(path + File.separator + filename);		// newfilename
//			
//			InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
//			
//			// db 다운로드 카운트
//			
//			return ResponseEntity.ok()
//						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()) // 원본파일명
//						.contentType(mediaType)
//						.contentLength(file.length())
//						.body(isr);					
//		}
//		

}
