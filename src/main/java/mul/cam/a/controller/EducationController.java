package mul.cam.a.controller;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.service.EducationService;
import mul.cam.a.util.RandomCode;

@RestController
public class EducationController {
	
	@Autowired
	EducationService service;
	
	@PostMapping(value="eduAdd")
	public String eduAdd(EducationDto edu) {
		System.out.println("EducationController eduAdd" + new Date());
		
		// 등록된 교육기관인지 확인
		boolean eduDuplicate = service.eduDuplicateCheck(edu);
		if(eduDuplicate) {
			return "duplicate";
		}		
		
		// 교육기관 코드생성
		String eduCode = new RandomCode().eduCode();
		boolean eduCodeChk = service.eduCodeCheck(eduCode);
		
		// 중복코드있으면 다시생성
		while(eduCodeChk) {
			eduCode = new RandomCode().eduCode();
			eduCodeChk = service.eduCodeCheck(eduCode);
		}
		
		// 학원 메인계정 아이디 생성
		CFR_User admin = new CFR_User();
		admin.setId("@" + eduCode);
		admin.setPassword(eduCode + "1234");
		admin.setName(edu.getEduAddress());
		admin.setAddress(edu.getEduAddress());
		admin.setPhone(edu.getEduPhone());
		admin.setAuth("admin");
		
		// 학원등록
		edu.setEduCode(eduCode);
		boolean isS = service.eduAdd(edu);
		if(isS) {
			// 학원등록 성공 시 메인계정아이디 등록
			boolean b = service.eduAddAdmain(admin);
			
			if(b) {
				return "sucess";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
}
