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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import mul.cam.a.MediaTypeUtiles;
import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.TaskDto;
import mul.cam.a.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@ResponseBody
	@GetMapping(value="tasklist")
	public Map<String, Object> tasklist (ListParam param) {
		System.out.println("TaskController tasklist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = (pn * 10);
		int end = (pn + 1) * 10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<TaskDto> list = service.taskList(param);
		
		int len = service.getAllTask(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	
		
		return map;
	}
	
	

	@PostMapping(value="writeTask")
	public String writeTask(TaskDto bbs) {
		System.out.println("TaskController writeTask " + new Date());
		
		boolean b = service.writeTask(bbs);
		
		if(b == false) {
			return "NO";
		}
		
		return "YES";
	}
	

	@GetMapping(value="getTask")
	public TaskDto getTask(Integer seq) {
		System.out.println("TaskController getTask " + new Date());
		
		return service.getTask(seq);
	}
	

	@PostMapping(value = "upload")
	public String fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile, 
								HttpServletRequest req, TaskDto dto) {		
		System.out.println("UserController fileUpload " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
//			
//			
//			dto.setFilename(filename);
//			 System.out.println(dto.toString());
//			 service.writeTask(dto);
//		
//			    
//		
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}

		return "success";
	}
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "download")
	public ResponseEntity<InputStreamResource> download(String filename, HttpServletRequest req) throws Exception {
		System.out.println("TaskController download " + new Date());
		
		// 경로
		String path = req.getServletContext().getRealPath("/mailfile");
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
