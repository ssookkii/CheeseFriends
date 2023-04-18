package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.SubjectDto;
import mul.cam.a.dto.UserDto;

@Mapper
@Repository
public interface SubjectDao {
	
	// 과목리스트
	List<SubjectDto> getSubList(ListParam param);
	
	// 과목 총수
	int getAllSub(ListParam param);
	
	// 과목 데이터
	SubjectDto getSub(String subCode);
	
	// 과목수정
	int subUpdate(SubjectDto sub);
	
	// 과목삭제
	int subDelete(String subCode);
	
	// 교사아이디리스트
	List<UserDto> getIdList(String auth);
	
}