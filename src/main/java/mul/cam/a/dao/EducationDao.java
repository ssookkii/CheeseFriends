package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.AttendanceEdu;
import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.MailDto;
import mul.cam.a.dto.MailParam;
import mul.cam.a.dto.TeacherUserDto;
import mul.cam.a.dto.UserDto;

@Mapper
@Repository
public interface EducationDao {
	// 교육기관등록 
	int eduAdd(EducationDto edu);
	
	// 교육기관등록 시 메인계정 생성
	int eduAddAdmain(UserDto admin);
	
	// useredu에 아이디 등록
	int userEduAdd(EducationDto edu);
	
	// 교육기관코드 중복체크
	int eduCodeCheck(String eduCode);
	
	// 등록된 교육기관 중복체크 
	int eduDuplicateCheck(EducationDto edu);
	
	// 교육기관리스트
	List<EducationDto> getEduList(ListParam param);
	
	// 교육기관 총 수
	int getAllEdu(ListParam param);
	
	// 교육기관 데이터
	EducationDto getEdu(String eduCode);
	
	// 교육기관 수정
	int eduUpdate(EducationDto edu);
	
	// 교육기관 계정 수정
	int eduUpdateAdmin(EducationDto edu);
	
	// 교육기관 삭제
	int eduDelete(EducationDto edu);
	
	// 메일용 교육기관 리스트
	List<EducationDto> getEduMailList(ListParam param);
	
	// 메일용 아이디 리스트
	List<TeacherUserDto> getIdMailList(String id);
	
	// 메일용 교육기관 아이디 리스트
	List<TeacherUserDto> getEduIdMailList(String eduCode);
	
	// 보낸메일 시간별로 묶은 리스트
	List<MailDto> sendMaillist(MailParam param);
	
	// 보낸메일 총 수
	int getsendAllMail(MailParam param);
	
	// 보낸메일 디테일
	MailDto getSendMailDetail(String wdate);
}



