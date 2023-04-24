package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.TestEduDto;
import mul.cam.a.dto.UserDto;
import mul.cam.a.dto.UserparentsDto;

@Mapper
@Repository
public interface UserDao {
	
	String idcheck(String id);
	
	List<TestEduDto> subjectlist(String edu_code);
	
	String eduname(String edu_code);
	String subname(String sub_code);
	
	// 회원가입
	int adduser(UserDto dto);
	
	// 학생 가입
	int addusersubject(TestEduDto dto);
	
	// 학부모 가입
	UserDto idmatching(String studentid);
	int adduserparents(UserparentsDto dto);
	
	// 교사 가입
	List<EducationDto> edusearch(String edu_name);
	int adduseredu(TestEduDto dto);
	
	// 아이디 찾기
	UserDto idsearch(UserDto dto);
	
	// 비밀번호 재설정
	String idchecktwo(UserDto dto);
	int updatepassword(UserDto dto);
	
	// 로그인
	UserDto login(UserDto dto);
	
	// 개인정보 변경
	int changeuser(UserDto dto);

}
