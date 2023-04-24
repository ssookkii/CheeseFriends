package mul.cam.a.dto;

import java.io.Serializable;

public class UserparentsDto implements Serializable {
	
	String studentid;
	String parentsid;
	
	public UserparentsDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserparentsDto(String studentid, String parentsid) {
		super();
		this.studentid = studentid;
		this.parentsid = parentsid;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getParentsid() {
		return parentsid;
	}

	public void setParentsid(String parentsid) {
		this.parentsid = parentsid;
	}

	@Override
	public String toString() {
		return "UserparentsDto [studentid=" + studentid + ", parentsid=" + parentsid + "]";
	}
	
	

}
