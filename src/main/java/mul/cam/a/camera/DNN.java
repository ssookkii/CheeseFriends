package mul.cam.a.camera;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.ml.TrainData;

public class DNN {

    private static final int IMG_WIDTH = 300;
    private static final int IMG_HEIGHT = 300;

    private static final String SVM_MODEL_FILE = "svm_model.xml";
    private static final String TRAIN_LABEL_FILE = "train_label.csv";

    public void train(String trainDataPath) throws IOException {
        // 학습 데이터셋과 레이블 로딩
        List<Mat> trainImages = new ArrayList<>();
        List<Integer> trainLabels = new ArrayList<>();

        File trainDataDir = new File(trainDataPath);
        if (!trainDataDir.isDirectory()) {
            System.err.println("Invalid train data directory");
            return;
        }

        File[] trainImageFiles = trainDataDir.listFiles();
        for (File imageFile : trainImageFiles) {
            String fileName = imageFile.getName();
            String[] fileNameParts = fileName.split("\\.");
            String userId = fileNameParts[0];

            Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());
            Mat resizedImage = new Mat();
            Imgproc.resize(image, resizedImage, new org.opencv.core.Size(IMG_WIDTH, IMG_HEIGHT));

            trainImages.add(resizedImage);
            trainLabels.add(Integer.parseInt(userId));
        }

        // 학습 데이터셋 구성
        Mat trainDataMat = new Mat(trainImages.size(), IMG_WIDTH * IMG_HEIGHT, CvType.CV_32FC1);
        MatOfInt labelsMat = new MatOfInt();
        for (int i = 0; i < trainImages.size(); i++) {
            Mat image = trainImages.get(i);
            image.convertTo(image, CvType.CV_32FC1);
            Mat imageVec = image.reshape(1, 1);
            imageVec.copyTo(trainDataMat.row(i));
        }
        labelsMat.fromList(trainLabels);

        // SVM 모델 학습
        SVM svm = SVM.create();
        svm.setType(SVM.C_SVC);
        svm.setKernel(SVM.RBF);
        svm.setTermCriteria(new TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6));
        svm.train(TrainData.create(trainDataMat, Ml.ROW_SAMPLE, labelsMat), 10);

        // 학습된 모델 저장
        svm.save(SVM_MODEL_FILE);
    }

    public static void main(String[] args) throws IOException {
        // DNN 객체 생성
        DNN dnn = new DNN();

        // 학습 실행
        dnn.train("train_data");
    }
}
