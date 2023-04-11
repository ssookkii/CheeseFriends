package mul.cam.a.controller;

import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.service.CFR_AttendanceService;

public class CFR_AttendanceController {
    private CFR_AttendanceService attendanceService;

    public CFR_AttendanceController(CFR_AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public CFR_Attendance getAttendanceById(int attendanceID) {
        return attendanceService.getAttendanceById(attendanceID);
    }
    
    // Other methods for handling attendance requests
}

