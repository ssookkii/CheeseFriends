package mul.cam.a.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.opencv.core.Core;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mul.cam.a.camera.User_Face_Crop;
import mul.cam.a.dto.AttendanceRequest;
import mul.cam.a.dto.AttendanceSubject;
import mul.cam.a.dto.CFR_Attendance;
import mul.cam.a.service.CFR_AttendanceService;

@Controller
public class CFR_AttendanceController {
    private CFR_AttendanceService attendanceService;
    
    public CFR_AttendanceController(CFR_AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
   
 //출석체크 실행 시스템   
    @PostMapping("/api/compareFaces")
    public ResponseEntity<Double> compareFaces() {
        String command = "java -cp .;opencv-470.jar mul.cam.a.camera.Compare";
        double similarity = 0.0;
        System.out.println("출석체크 시스템 실행");
        System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());

        try {
        	
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            builder.directory(new File("./target/classes"));
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new FileReader("./target/classes/similarity.txt"));
            String line = reader.readLine();
            System.out.println(command);
            System.out.println(process);
            System.out.println(line);
            if (line != null && line.matches("\\d+(\\.\\d+)?")) {
                similarity = Double.parseDouble(line.trim());
            }
        } catch (Exception e) {
           e.printStackTrace();
            System.out.println("catch");
       }
        return ResponseEntity.ok(similarity);
    }


 // userID별로 사진 크롭해서 저장
    @PostMapping("/api/imgcrop/{userId}")
    public ResponseEntity<Object> compareFaces(@PathVariable String userId) {
    	
    	
    	System.out.println(userId);
        try {
            // 이미지를 자르는 기능
            User_Face_Crop faceCrop = new User_Face_Crop();
            String imagePath = "./src/MemberFace/" + userId + ".jpg";
            System.out.println(imagePath);
            String croppedImagePath = "./src/AttendanceFace/"+"test"+userId+".jpg";
            boolean cropSuccess = faceCrop.cropUserFace(imagePath, croppedImagePath);

            // 자르기가 성공했는지에 따라 결과 반환
            if (cropSuccess) {
            	System.out.println(userId + " 생성 성공");
                return ResponseEntity.ok().build();
            } else {
            	System.out.println(userId + " 생성 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to crop image");
        }
    }
    
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    @PostMapping(value = "/attendance")
    public ResponseEntity<?> postAttendance(@RequestBody AttendanceRequest request, Model model) {
        String userId = request.getUserId();
        String eduCode = request.getEduCode();
        String subCode = request.getSubCode();
        Date now = new Date();
        
        String month = String.format("%02d", now.getMonth() + 1);
        String date = String.format("%02d", now.getDate());
        String AttendanceID = userId + subCode + month + date;

        List<AttendanceSubject> subjectList = attendanceService.getSubjectByUserIdAndEduCode(userId, eduCode, subCode);

        // Attendance ID 중복 체크
        boolean checkattendanceIds = attendanceService.checkAttendanceId(userId, subCode, eduCode, AttendanceID);


        for (AttendanceSubject subject : subjectList) {
            if(checkattendanceIds==false) {
            	System.out.println(userId + "이미 출석했습니다");
            }
            else {
                if (subject.getSubCode().equals(subCode)) {
                    CFR_Attendance attendance = new CFR_Attendance();
                    attendance.setAttendanceID(AttendanceID);
                    attendance.setStudentID(userId);
                    attendance.setSub_code(subCode);
                    attendance.setEdu_code(eduCode);

                    // 현재 시간이 출석 시작 시간보다 빠르면 출석으로 처리, 아니면 지각으로 처리
                    if (now.getTime() < subject.getStartDate().getTime()) {                	
                        attendance.setStatus("출석");
                    } else {
                        attendance.setStatus("지각");
                    }

                 System.out.println(userId + " 출석했습니다.");
                    attendanceService.checkAttendance(attendance);
                    break;
                }
            }

        }


        return ResponseEntity.ok().build();
    }


    
    
 /* 출석체크!
    @PostMapping(value = "/attendance")
    public String checkAttendance(Model model, @RequestBody AttendanceRequest request) {
        String userId = request.getUserId();
        String eduCode = request.getEduCode();
        String subCode = request.getSubCode();
        System.out.println(userId);
        System.out.println(eduCode);
        System.out.println(subCode);
/*
        List<AttendanceSubject> subjectList = attendanceService.getSubjectByUserIdAndEduCode(userId, eduCode);

        LocalDateTime now = LocalDateTime.now();
        for (AttendanceSubject subject : subjectList) {
            if (subject.getSubCode().equals(subCode)) {
                CFR_Attendance attendance = new CFR_Attendance();
                attendance.setStudentID(userId);
                attendance.setSub_code(subCode);
                attendance.setEdu_code(eduCode);
                if (now.toLocalDate().isBefore(subject.getStartDate().toLocalDate())) {
                    attendance.setStatus("출석");
                } else {
                    attendance.setStatus("지각");
                }
                attendanceService.checkAttendance(attendance);
                break;
                
            }
        }

        return "redirect:/attendance";
    } */

}


