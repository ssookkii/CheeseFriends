package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.LectureDto;
import mul.cam.a.dto.LectureParam;

@Mapper
@Repository
public interface LectureDao {

	// 강의 목록
	List<LectureDto> lectureList(LectureParam param);
	
	// 수업 자료 등록
	int writeLecture(LectureDto bbs);
	
	// 수업 자료 목록
	List<LectureDto> learningList(LectureParam param);
}
