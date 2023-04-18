package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.ListParam;
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
		admin.setName(edu.getEduName());
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
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	@GetMapping(value="edulist")
	public Map<String, Object> edulist(ListParam param) {
		System.out.println("EducationController edulist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = (pn * 15);
		int end = (pn + 1) * 15;
		
		param.setStart(start);
		param.setEnd(end);
		
		System.out.println(param.getEnd());
		List<EducationDto> list = service.getEduList(param);
		
		int len = service.getAllEdu(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	// react에 보낼 때
		
		return map;
	}
	@GetMapping(value="getEdu")
	public EducationDto getEdu(String eduCode) {
		System.out.println("EducationController getEdu()" + new Date());
		return service.getEdu(eduCode);
	}
	@PostMapping(value="eduUpdate")
	public String eduUpdate(EducationDto edu) {
		System.out.println("EducationController update()" + new Date());

		boolean isS = service.eduUpdate(edu);
		if(isS) {
			return "success";
		}
		return "fail";
	}
}
