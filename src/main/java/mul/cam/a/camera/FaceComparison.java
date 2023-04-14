package mul.cam.a.camera;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FaceComparison {

    public static double compareFaces(String imgPath1, String imgPath2) {
        // 비교할 이미지 불러오기 
        Mat img1 = Imgcodecs.imread(imgPath1);
        Mat img2 = Imgcodecs.imread(imgPath2);

        // 색상 관련
        Mat grayImg1 = new Mat();
        Mat grayImg2 = new Mat();
        Imgproc.cvtColor(img1, grayImg1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(img2, grayImg2, Imgproc.COLOR_BGR2GRAY);

        // 이미지 사이즈를 같은 크기로 자르기
      //Imgproc.resize(grayImg1, grayImg1, new Size(200, 200));
      //Imgproc.resize(grayImg2, grayImg2, new Size(200, 200));

        // 이미지 사이의 차이 계산하기 
        Mat diff = new Mat();
        Core.absdiff(grayImg1, grayImg2, diff);

       
        Mat sqrDiff = new Mat();
        Core.pow(diff, 2, sqrDiff);
        Scalar mse = Core.mean(sqrDiff);

        // 유사도 계산하기
        double similarity = 1 - (mse.val[0] / 255);

        return similarity;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String imgPath1 = "path/to/image1.jpg";
        String imgPath2 = "path/to/image2.jpg";

        double similarity = compareFaces(imgPath1, imgPath2);
        System.out.println("유사도: " + similarity);
    }
}
