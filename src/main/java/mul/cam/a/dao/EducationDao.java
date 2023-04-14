package mul.cam.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;

@Mapper
@Repository
public interface EducationDao {
	// 교육기관등록 
	int eduAdd(EducationDto edu);
	
	// 교육기관등록 시 메인계정 생성
	int eduAddAdmain(CFR_User admin);
	
	// 교육기관코드 중복체크
	int eduCodeCheck(String eduCode);
	
	// 등록된 교육기관 중복체크 
	int eduDuplicateCheck(EducationDto edu);
}
