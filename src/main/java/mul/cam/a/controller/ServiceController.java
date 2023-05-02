package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping(value="categorysearch/{category}")
	public String searchService(Model model, @PathVariable String category, @RequestParam(defaultValue = "0") int pageNumber) {
	    ListParam param = new ListParam();
	    
	    // start, end 값 계산
	    int start = 1 + (pageNumber * 12);
	    int end = (pageNumber + 1) * 12;
	    param.setStart(start);
	    param.setEnd(end);

	    // topic 값 설정
	    param.setCategory(category);

	    // 해당 카테고리에 맞는 서비스 리스트와 총 글 수 가져오기
	    List<ServiceDto> list = service.getCategoryServiceList(param);
	    int len = service.getAllCategoryService(param);
	    int pageBbs = (len/12);
	    if((len%12) > 0) {
	        pageBbs += 1;
	    }

	    // 모델에 데이터 추가
	    model.addAttribute("bbslist", list);
	    model.addAttribute("pageBbs", pageBbs);
	    model.addAttribute("pageNumber", pageNumber);
	    model.addAttribute("category", category);

	    // 카테고리에 맞는 제목 설정
	    switch (category) {
	        case "1": {
	            model.addAttribute("boardName", "자주묻는질문");
	            break;
	        }
	        case "2": {
	            model.addAttribute("boardName", "개인정보");
	            break;
	        }
	        case "3": {
	            model.addAttribute("boardName", "강의이용");
	            break;
	        }
	        case "4": {
	            model.addAttribute("boardName", "학습플레이어");
	            break;
	        }
	        case "5": {
	            model.addAttribute("boardName", "모바일/기타");
	            break;
	        }
	    }

	    model.addAttribute("len", len);
	    return "ServiceList";
	}
	
	@ResponseBody
	@PostMapping(value="writeService")
	public String writeService(ServiceDto dto) {
		System.out.println("ServiceController writeService " + new Date());
	
		
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
	
	@ResponseBody
	@PostMapping(value="answerService")
	public String answerService(ServiceDto bbs) {
		System.out.println("ServiceController answerService " + new Date());
		
		boolean b = service.answerUpService(bbs);
		if(b == false) {
			return "NO";
		}
		return "YES";
	}
	
}
