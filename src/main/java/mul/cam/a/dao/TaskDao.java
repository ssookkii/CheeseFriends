package mul.cam.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.TaskDto;

@Mapper
@Repository
public interface TaskDao {
	
	// 과제 제출 목록
	List<TaskDto> taskList(ListParam param);
	
	// 과제 등록
	int writeTask (TaskDto bbs);
}
