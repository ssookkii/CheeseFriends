package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.TaskDao;
import mul.cam.a.dto.LectureParam;
import mul.cam.a.dto.TaskDto;

@Service
@Transactional
public class TaskService {

	@Autowired
	TaskDao dao;
	
	public List<TaskDto> taskList(LectureParam param) {
		return dao.taskList(param);
	}
	
	public boolean writeTask(TaskDto bbs) {
		int n = dao.writeTask(bbs);
		return n>0? true:false;
	}
}
