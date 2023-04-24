package mul.cam.a.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.MailDao;
import mul.cam.a.dto.MailDto;
import mul.cam.a.dto.UserDto;

@Service
@Transactional
public class MailService {

	@Autowired
	MailDao dao;
	
	// 메일 받는사람 검색
	public List<UserDto> mailreceiverid(String name){
		System.out.println("MailService mailreceiverid() " + new Date());
		
		return dao.mailreceiverid(name);
	}
	
	// 메일 받는사람 추가
	public UserDto mailreceiveradd(String id){
		System.out.println("MailService mailreceiveradd() " + new Date());
		
		return dao.mailreceiveradd(id);
	}
	
	// 메일 보내기
	public boolean mailsend(MailDto dto) {
		int count = dao.mailsend(dto);
		return count>0?true:false;
	}

}

