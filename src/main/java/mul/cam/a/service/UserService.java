package mul.cam.a.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.UserDao;
import mul.cam.a.dto.TestEduDto;

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

}
