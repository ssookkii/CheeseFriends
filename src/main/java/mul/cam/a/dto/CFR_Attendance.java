package mul.cam.a.dto;
import java.sql.Timestamp;

public class CFR_Attendance {
    private int attendanceID;
    private String studentID;
    private String subCode;
    private Timestamp attendanceTime;
    private String status;
    private String eduCode;
    
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
		return subCode;
	}
	public void setSub_code(String subCode) {
		this.subCode = subCode;
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
		return eduCode;
	}
	public void setEdu_code(String eduCode) {
		this.eduCode = eduCode;
	}


}
