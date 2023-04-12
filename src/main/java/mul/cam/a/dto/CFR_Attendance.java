package mul.cam.a.dto;
import java.sql.Timestamp;

public class CFR_Attendance {
    private int attendanceID;
    private String studentID;
    private String sub_code;
    private Timestamp attendanceTime;
    private String status;
    private String edu_code;
	public int getAttendanceID() {
		return attendanceID;
	}
	public void setAttendanceID(int attendanceID) {
		this.attendanceID = attendanceID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public Timestamp getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(Timestamp attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEdu_code() {
		return edu_code;
	}
	public void setEdu_code(String edu_code) {
		this.edu_code = edu_code;
	}


}
