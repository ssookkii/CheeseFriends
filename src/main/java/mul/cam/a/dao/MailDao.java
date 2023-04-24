package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.MailDto;
import mul.cam.a.dto.UserDto;

@Mapper
@Repository
public interface MailDao {
	
	// 메일 받는사람 검색
	List<UserDto> mailreceiverid(String name);
	
	// 메일 받는사람 추가
	UserDto mailreceiveradd(String id);
	
	// 메일 보내기
	int mailsend(MailDto dto);

}
