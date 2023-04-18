package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.LectureDto;

@Mapper
@Repository
public interface LectureDao {

	// 강의 목록
	List<LectureDto> lectureList(Integer seq);
}
