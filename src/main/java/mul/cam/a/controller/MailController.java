package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.MailDto;
import mul.cam.a.dto.UserDto;
import mul.cam.a.service.MailService;

@RestController
public class MailController {

	@Autowired
	MailService service;
	
	// 메일 받는사람 검색
	@GetMapping(value = "mailreceiverid")
	public List<UserDto> mailreceiverid(String name){
		System.out.println("MailController mailreceiverid() " + new Date());
		
		List<UserDto> list = service.mailreceiverid(name);
		
		System.out.println(list.toString());
		
		return list;
	}
	
	// 메일 받는사람 추가
	@GetMapping(value = "mailreceiveradd")
	public UserDto mailreceiveradd(String id){
		System.out.println("MailController mailreceiveradd() " + new Date());
		
		UserDto dto = service.mailreceiveradd(id);
		
		System.out.println(dto.toString());
		
		return dto;
	}
	
	// 메일 보내기
	@PostMapping(value = "mailsend")
	public String mailsend(MailDto dto) {
		System.out.println("MailController mailsend() " + new Date());
		
		boolean isS = service.mailsend(dto);
		if(isS == true) {
			return "YES";
		}
			
		return "NO";
	}
	
}



