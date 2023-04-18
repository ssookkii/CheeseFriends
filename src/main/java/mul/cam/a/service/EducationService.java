package mul.cam.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mul.cam.a.dao.EducationDao;
import mul.cam.a.dto.CFR_User;
import mul.cam.a.dto.EducationDto;
import mul.cam.a.dto.ListParam;

@Service
@Transactional
public class EducationService {
	
	@Autowired
	EducationDao dao;
	
	public boolean eduAdd(EducationDto edu) {
		int n = dao.eduAdd(edu);
		return n>0?true:false;
	}
	public boolean eduAddAdmain(CFR_User admin) {
		int n = dao.eduAddAdmain(admin);
		return n>0?true:false;
	}
	public boolean eduCodeCheck(String eduCode) {
		int n = dao.eduCodeCheck(eduCode);
		return n>0?true:false;
	}
	public boolean eduDuplicateCheck(EducationDto edu) {
		int n = dao.eduDuplicateCheck(edu);
		return n>0?true:false;
	}
	public List<EducationDto> getEduList(ListParam param) {
		return dao.getEduList(param);
	}
	public int getAllEdu(ListParam param) {
		return dao.getAllEdu(param);
	}
	public EducationDto getEdu(String eduCode) {
		return dao.getEdu(eduCode);
	}
	public boolean eduUpdate(EducationDto edu) {
		int n = dao.eduUpdate(edu);
		return n>0?true:false;
	}
}
