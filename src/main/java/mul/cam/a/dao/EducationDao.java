package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.UserDto;

@Mapper
@Repository
public interface EducationDao {
	// 교육기관등록 
	int eduAdd(EducationDto edu);
	
	// 교육기관등록 시 메인계정 생성
	int eduAddAdmain(UserDto admin);
	
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
}