package mul.cam.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.TaskDto;
import mul.cam.a.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@ResponseBody
	@GetMapping(value="tasklist")
	public List<TaskDto> taskList(ListParam param) {
		System.out.println("TaskController taskList " + new Date());
		
		List<TaskDto> tasklist = service.taskList(param);
		
		return tasklist;
	}
	
	@ResponseBody
	@PostMapping(value="writeTask")
	public String writeTask(TaskDto bbs) {
		System.out.println("TaskController writeTask " + new Date());
		
		boolean b = service.writeTask(bbs);
		
		if(b == false) {
			return "NO";
		}
		
		return "YES";
	}
}
