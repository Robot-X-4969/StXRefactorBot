package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import java.util.ArrayList;
import java.util.List;

public class XCamera {

    private final XOpMode op;

    private Limelight3A limelight;

    private final String cameraName;

    private int index;

    private final List<Integer> tagIds = new ArrayList<>();
    private final List<Double> tagXDegrees = new ArrayList<>();
    private final List<Double> tagYDegrees = new ArrayList<>();
    private final List<Double> tagXPositions = new ArrayList<>();
    private final List<Double> tagYPositions = new ArrayList<>();

    private final List<Double> blobXDegrees = new ArrayList<>();
    private final List<Double> blobYDegrees = new ArrayList<>();
    private final List<Double> blobXPositions = new ArrayList<>();
    private final List<Double> blobYPositions = new ArrayList<>();

    public XCamera(XOpMode op, String cameraName) {

        this.op = op;

        this.cameraName = cameraName;

    }

    public void init() {

        limelight = op.hardwareMap.get(Limelight3A.class, cameraName);
        limelight.start();
        index = 0;
        limelight.pipelineSwitch(index);

    }

    public void setPipeline(int index) {

        this.index = index;

        limelight.pipelineSwitch(index);

    }

    public void getTagData(){

        LLResult result = limelight.getLatestResult();

        if(result != null && result.isValid()) {

            tagIds.clear();
            tagXDegrees.clear();
            tagYDegrees.clear();
            tagXPositions.clear();
            tagYPositions.clear();

            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();

            for(LLResultTypes.FiducialResult fiducialResult : fiducialResults){

                int fiducialId = fiducialResult.getFiducialId();

                double txdeg = fiducialResult.getTargetXDegrees();
                double tydeg = fiducialResult.getTargetYDegrees();

                double tx = fiducialResult.getTargetPoseCameraSpace().getPosition().x;
                double ty = fiducialResult.getTargetPoseCameraSpace().getPosition().y;

                tagIds.add(fiducialId);
                tagXDegrees.add(txdeg);
                tagYDegrees.add(tydeg);
                tagXPositions.add(tx);
                tagYPositions.add(ty);

            }


        }

    }

    public void getColorData(){

        LLResult result = limelight.getLatestResult();

        if(result != null && result.isValid()) {

            blobXDegrees.clear();
            blobYDegrees.clear();
            blobXPositions.clear();
            blobYPositions.clear();

            List<LLResultTypes.ColorResult> colorResults = result.getColorResults();

            for(LLResultTypes.ColorResult colorResult : colorResults){

                double txdeg = colorResult.getTargetXDegrees();
                double tydeg = colorResult.getTargetYDegrees();

                double tx = colorResult.getTargetPoseCameraSpace().getPosition().x;
                double ty = colorResult.getTargetPoseCameraSpace().getPosition().y;

                blobXDegrees.add(txdeg);
                blobYDegrees.add(tydeg);
                blobXPositions.add(tx);
                blobYPositions.add(ty);

            }

        }

    }




}
