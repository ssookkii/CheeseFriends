package mul.cam.a.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.UserDao;
import mul.cam.a.dto.TestEduDto;
import mul.cam.a.dto.UserDto;
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
	
	public boolean adduser(UserDto dto) {
		int count = dao.adduser(dto);
		return count>0?true:false;
	}
	
	public boolean addusersubject(TestEduDto dto) {
		int count = dao.addusersubject(dto);
		return count>0?true:false;
	}
}
