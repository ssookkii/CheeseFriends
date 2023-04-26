package mul.cam.a.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.UserDao;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.TestEduDto;
import mul.cam.a.dto.UserDto;
import mul.cam.a.dto.UserparentsDto;
import mul.cam.a.util.naver2;


@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public boolean idcheck(String id) {
		String userid = dao.idcheck(id);
		
		if(userid != null && !userid.equals("")) {
			return true;
		}
		
		return false;
	}
	
	public List<TestEduDto> subjectlist(String edu_code){
		System.out.println("UserService subjectlist() " + new Date());
		
		return dao.subjectlist(edu_code);
	}
	
	public String eduname(String edu_code) {
		System.out.println("UserService eduname() " + new Date());
		
		return dao.eduname(edu_code);
	}
	
	public String subname(String sub_code) {
		System.out.println("UserService subname() " + new Date());
		
		return dao.subname(sub_code);
	}
	
	public String sendRandomMessage(String phone) {
		naver2 message = new naver2();
	    Random rand = new Random();
	    String random = "";
	    for (int i = 0; i < 6; i++) {
	        String ran = Integer.toString(rand.nextInt(10));
	        random += ran;
	    }
	    System.out.println("회원가입 문자 인증 => " + random);

	    message.sendSMS(phone, random);

	    return random;
	}
	
	// 학생 가입
	public boolean adduser(UserDto dto) {
		int count = dao.adduser(dto);
		return count>0?true:false;
	}
	
	public boolean addusersubject(TestEduDto dto) {
		int count = dao.addusersubject(dto);
		return count>0?true:false;
	}
	
	// 학부모 가입
	public UserDto idmatching(String studentid){
		System.out.println("UserService idmatching() " + new Date());
		
		return dao.idmatching(studentid);
	}
	
	public boolean adduserparents(UserparentsDto dto) {
		int count = dao.adduserparents(dto);
		return count>0?true:false;
	}
	
	// 교사 가입
	public List<EducationDto> edusearch(String edu_name) {
		System.out.println("UserService edusearch() " + new Date());
		
		return dao.edusearch(edu_name);
	}
	
	public boolean adduseredu(TestEduDto dto) {
		int count = dao.adduseredu(dto);
		return count>0?true:false;
	}
	
	// 아이디 찾기
	public UserDto idsearch(UserDto dto) {
		System.out.println("UserService idsearch() " + new Date());

		UserDto getdto = dao.idsearch(dto);
	
		return getdto;
	}
	
	// 아이디 찾기2
	public boolean idchecktwo(UserDto dto) {
		String userid = dao.idchecktwo(dto);
		
		if(userid != null && !userid.equals("")) {
			return true;
		}
		
		return false;
	}
	
	// 비밀번호 ㅂ녀경
	public boolean updatepassword(UserDto dto) {
		int count = dao.updatepassword(dto);
		return count>0?true:false;
	}

	// 로그인
	public UserDto login(UserDto dto) {
		return dao.login(dto);
	}
	
	//개인정보 변경
	public boolean changeuser(UserDto dto) {
		int count = dao.changeuser(dto);
		return count>0?true:false;
	}
	
}
