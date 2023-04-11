package mul.cam.a.service;

import mul.cam.a.dao.CFR_AttendanceDao;
import mul.cam.a.dto.CFR_Attendance;

public class CFR_AttendanceService {
    private CFR_AttendanceDao attendanceDao;

    public CFR_AttendanceService(CFR_AttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    public CFR_Attendance getAttendanceById(int attendanceID) {
        return attendanceDao.getAttendanceById(attendanceID);
    }
    
    // Other methods for attendance management
}

