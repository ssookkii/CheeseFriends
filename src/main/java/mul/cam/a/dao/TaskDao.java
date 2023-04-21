package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.LectureParam;
import mul.cam.a.dto.TaskDto;

@Mapper
@Repository
public interface TaskDao {
	
	// 과제 제출 목록
	List<TaskDto> taskList(LectureParam param);
	
	// 과제 등록
	int writeTask (TaskDto bbs);
}
