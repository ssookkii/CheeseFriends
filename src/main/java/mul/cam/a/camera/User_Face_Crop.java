package mul.cam.a.camera;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class User_Face_Crop {
    private CascadeClassifier faceDetector;
    private static final String FACE_CASCADE_PATH = "C:\\Users\\User\\Downloads\\opencv\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_alt.xml";
    
    public User_Face_Crop() {
        // 얼굴 검출기 초기화
        faceDetector = new CascadeClassifier(FACE_CASCADE_PATH);
        if (faceDetector.empty()) {
            System.out.println("얼굴 검출기 로딩 실패");
            System.exit(1);
        }
    }
    
    // 얼굴 크롭 메서드
    public boolean cropUserFace(String imagePath, String outputImagePath) {
        // 이미지 로딩
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.out.println("이미지 로딩 실패");
            return false;
        }

        // 그레이 스케일 변환
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // 얼굴 검출
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(gray, faceDetections);

        if (faceDetections.toArray().length != 1) {
            System.out.println("얼굴 검출 실패");
            return false;
        }

        // 얼굴 크롭
        Rect faceRect = faceDetections.toArray()[0];
        Mat cropped = new Mat(image, faceRect);
        Mat resized = new Mat();
        Size size = new Size(300, 300);
        Imgproc.resize(cropped, resized, size);

        // 이미지 저장
        Imgcodecs.imwrite(outputImagePath, resized);

        return true;
    }
}
