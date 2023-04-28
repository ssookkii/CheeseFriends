package mul.cam.a.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.ServiceDto;
import mul.cam.a.service.ServiceService;

@RestController
public class ServiceController {

	@Autowired
	ServiceService service;
	
	@ResponseBody
	@GetMapping(value="serviceList")
	public Map<String, Object> serviceList(ListParam param) {
		int pn = param.getPageNumber();
		int start = (pn * 10);
		int end = (pn + 1) *10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<ServiceDto> list = service.serviceList(param);
		
		int len = service.getAllService(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);
		
		return map;
	}
	
	@ResponseBody
	@PostMapping(value="writeService")
	public String writeService(ServiceDto dto) {
		
		boolean b = service.writeService(dto);
		
		if(b == false) {
			return "NO";
		}
		
		return "YES";
	}
	
	@ResponseBody
	@GetMapping(value="getService")
	public ServiceDto getService(Integer seq) {
		return service.getService(seq);
	}
}
