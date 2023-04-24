package mul.cam.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import mul.cam.a.dto.LearningDto;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.TaskDto;
=======
import mul.cam.a.dto.EducationDto;
>>>>>>> 7795725b679af6cfcc77f14e6895edaa0c62783d
import mul.cam.a.dto.TestEduDto;
import mul.cam.a.dto.UserDto;
import mul.cam.a.dto.UserparentsDto;
import mul.cam.a.service.UserService;
import mul.cam.a.util.naver2;

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
	
	@GetMapping(value = "eduname")
	public String eduname(String edu_code){
		System.out.println("UserController eduname() " + new Date());
		
		String eduname = service.eduname(edu_code);
		
		System.out.println("eduname : " + eduname);
		
		return eduname;
	}
	
	@GetMapping(value = "subname")
	public String subname(String sub_code){
		System.out.println("UserController subname() " + new Date());
		System.out.println("subcode : " + sub_code );
		
		String subname = service.subname(sub_code);
		
		System.out.println("subname : " + subname);
		
		return subname;
	}
	
	@PostMapping("phoneAuth")
	public String phoneAuth(String phone, HttpSession session) {
		System.out.println("UserController phoneAuth() " + new Date());
//	    try { // 이미 가입된 전화번호가 있으면
//	        if(UserService.memberTelCount(tel) > 0) 
//	            return true; 
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
		System.out.println("phone : " + phone);
		
	    String code = service.sendRandomMessage(phone);
	    System.out.println("code : " + code);
	    
	    session.setAttribute("rand", code);
	    String rand = (String)session.getAttribute("rand");
	    System.out.println("rand : " + rand);
	    
	    return rand;
	}

	@PostMapping("phoneAuthOk")
	public Boolean phoneAuthOk(String phone_public, HttpSession session) {
		System.out.println("UserController phoneAuthOk() " + new Date());
		
	    String rand = (String)session.getAttribute("rand");
	    String code = phone_public;

	    System.out.println(rand + " 비교: " + code);

	    if (rand.equals(code)) {
	        session.removeAttribute("rand");
	        return false;
	    } 

	    return true;
	}
	
	
	@PostMapping(value = "fileUpload")
	public String fileUpload(@RequestParam("uploadFile")MultipartFile uploadFile, 
								HttpServletRequest req, LectureDto dto, LearningDto bbs, TaskDto tto) {		
		System.out.println("UserController fileUpload " + new Date());
		
		String uploadpath = req.getServletContext().getRealPath("/upload");
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadpath + "/" + filename;		
		System.out.println(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			return "fail";
		}

		return "success";
	}	
	
	@PostMapping(value = "adduser")
	public String addmember(UserDto dto) {
		System.out.println("UserController addmember() " + new Date());
		
		boolean isS = service.adduser(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
	@PostMapping(value = "addusersubject")
	public String addusersubject(TestEduDto dto) {
		System.out.println("UserController addusersubject() " + new Date());
		
		boolean isS = service.addusersubject(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
	// 학부모 가입
	@GetMapping(value = "idmatching")
	public UserDto idmatching(String studentid){
		System.out.println("UserController idmatching() " + new Date());
		
		UserDto dto = service.idmatching(studentid);
		
		if(dto != null) {
			System.out.println(dto.getId());
			System.out.println(dto.getName());
		}
		return dto;
	}
	
	@PostMapping(value = "adduserparents")
	public String adduserparents(UserparentsDto dto) {
		System.out.println("UserController addmember() " + new Date());
		
		boolean isS = service.adduserparents(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
	// 교사 가입
	@GetMapping(value = "edusearch")
	public List<EducationDto> edusearch(String edu_name) {
		System.out.println("UserController edusearch() " + new Date());
		
		List<EducationDto> list = service.edusearch(edu_name);
		
		if(list != null) {
			System.out.println(list.toString());
		}
		return list;
	}
	
	@PostMapping(value = "adduseredu")
	public String adduseredu(TestEduDto dto) {
		System.out.println("UserController adduseredu() " + new Date());
		
		boolean isS = service.adduseredu(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
	@PostMapping(value = "idsearch")
	public UserDto idsearch(UserDto dto) {
		System.out.println("UserController idsearch() " + new Date());
		
		UserDto getdto = service.idsearch(dto);
		System.out.println("id: " + getdto.getId());
		
		return getdto;
	}
	
	@PostMapping(value = "idchecktwo")
	public String idchecktwo(UserDto dto) {
		System.out.println("UserController idcheck() " + new Date());
		
		boolean isS = service.idchecktwo(dto);
		if(isS == true) {
			return "NO";
		}
		
		return "YES";
	}
	
	
	@PostMapping(value = "updatepassword")
	public String updatepassword(UserDto dto) {
		System.out.println("UserController updatepassword() " + new Date());
		
		boolean isS = service.updatepassword(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
	@PostMapping(value = "login")
	public UserDto login(UserDto dto) {
		System.out.println("UserController login() " + new Date());
		
		return service.login(dto);
	}
	
	@PostMapping(value = "changeuser")
	public String changeuser(UserDto dto) {
		System.out.println("UserController changeuser() " + new Date());
		
		boolean isS = service.changeuser(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}

}
