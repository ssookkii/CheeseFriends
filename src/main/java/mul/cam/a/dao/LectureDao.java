package mul.cam.a.dao;

import java.util.List;
import mul.cam.a.dto.LectureDto;

public interface LectureDao {

	// 강의 목록
	List<LectureDto> lectureList(Integer seq);
}
