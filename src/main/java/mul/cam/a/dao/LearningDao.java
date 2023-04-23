package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.LearningDto;
import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.LectureParam;

@Mapper
@Repository
public interface LearningDao {

//	// 수업 자료 목록
	List<LearningDto> learningList(LectureParam param);
	
	// 수업 자료 등록
	int writeLearning (LearningDto bbs);
	
	// 수업 자료 상세
	LearningDto getLearning(Integer seq);
}
