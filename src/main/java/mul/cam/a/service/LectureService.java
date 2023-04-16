package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.LectureDto;

public interface LectureService {

	List<LectureDto> lectureList(Integer seq);
}
