package mul.cam.a.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import mul.cam.a.service.CFR_AttendanceService;

@Controller
public class CFR_AttendanceController {
    private CFR_AttendanceService attendanceService;

   /* public CFR_AttendanceController(CFR_AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @RequestMapping("/getAttendance")
    public String getAttendanceById(@RequestParam("attendanceID") int attendanceID, Model model) {
        CFR_Attendance attendance = attendanceService.getAttendanceById(attendanceID);
        model.addAttribute("attendance", attendance);
        return "attendanceDetails"; // 해당하는 View 이름을 반환합니다.
    }*/
    
    
    @PostMapping("/api/compareFaces")
    public ResponseEntity<Double> compareFaces() {
        String command = "java -cp .;opencv-470.jar mul.cam.a.camera.Compare";
        double similarity = 5.0;
        System.out.println("출석체크 시스템 실행");
        System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());

        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.directory(new File("C:/springboot2/CheeseFriends/STS/cheesefriends_back/target/classes"));
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            System.out.println(command);
            System.out.println(process);
            System.out.println(line);
            if (line != null && line.matches("\\d+(\\.\\d+)?")) {
                similarity = Double.parseDouble(line.trim());
                return ResponseEntity.ok(similarity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch");
        }
        return ResponseEntity.ok(similarity);
    }



}


