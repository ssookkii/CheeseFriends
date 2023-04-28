package mul.cam.a.dto;

import java.io.Serializable;

public class MysubjectDto implements Serializable{

	String eduname;
	String subname;
	String educatorname;
	String startdate;
	String enddate;
	String state;
	
	public MysubjectDto() {
		
	}

	public MysubjectDto(String eduname, String subname, String educatorname, String startdate, String enddate,
			String state) {
		super();
		this.eduname = eduname;
		this.subname = subname;
		this.educatorname = educatorname;
		this.startdate = startdate;
		this.enddate = enddate;
		this.state = state;
	}

	public String getEduname() {
		return eduname;
	}

	public void setEduname(String eduname) {
		this.eduname = eduname;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getEducatorname() {
		return educatorname;
	}

	public void setEducatorname(String educatorname) {
		this.educatorname = educatorname;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "MysubjectDto [eduname=" + eduname + ", subname=" + subname + ", educatorname=" + educatorname
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", state=" + state + "]";
	}
	
	
}
