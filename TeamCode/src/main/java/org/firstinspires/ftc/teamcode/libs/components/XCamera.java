package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import java.util.ArrayList;
import java.util.List;

/**
 * XCamera component class that implements Limelight3A functionality, such as AprilTag and color blob detection.
 * <p>
 * Created by Gavin Farrell on 2/4/26.
 */

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
    private final List<Double> tagAreas = new ArrayList<>();

    /**
     * Creates a new XCamera component.
     *
     * @param op The XOpMode the XCamera component is created in.
     * @param cameraName Unique name of the camera in the hardware map.
     */
    public XCamera(XOpMode op, String cameraName) {

        this.op = op;

        this.cameraName = cameraName;

        index = 0;

    }

    /**
     * Initializes the XCamera component and the Limelight3A camera.
     * <p>
     * Sets the initial pipeline and starts the camera.
     */
    public void init() {

        limelight = op.hardwareMap.get(Limelight3A.class, cameraName);
        limelight.start();
        index = 0;
        limelight.pipelineSwitch(index);

    }

    /**
     * Main loop method for the XCamera component.
     * <p>
     * Retrieves the latest results from the Limelight3A camera and processes AprilTag and color blob data.
     */
    public void loop() {

        LLResult result = limelight.getLatestResult();
        getTagData(result);
        getColorData(result);

    }

    /**
     * Sets the active pipeline on the Limelight3A camera.
     *
     * @param index The index of the pipeline to set.
     */
    public void setPipeline(int index) {

        this.index = index;

        limelight.pipelineSwitch(index);

    }

    /**
     * Retrieves AprilTag data from the latest LLResult.
     *
     * @param result The latest LLResult from the Limelight3A camera.
     */
    public void getTagData(LLResult result){

        tagIds.clear();
        tagXDegrees.clear();
        tagYDegrees.clear();
        tagXPositions.clear();
        tagYPositions.clear();
        tagAreas.clear();

        if(result != null && result.isValid()) {

            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();

            for(LLResultTypes.FiducialResult fiducialResult : fiducialResults){

                int fiducialId = fiducialResult.getFiducialId();

                double txdeg = fiducialResult.getTargetXDegrees();
                double tydeg = fiducialResult.getTargetYDegrees();

                double tx = fiducialResult.getTargetPoseCameraSpace().getPosition().x;
                double ty = fiducialResult.getTargetPoseCameraSpace().getPosition().y;

                double ta = fiducialResult.getTargetArea();

                tagIds.add(fiducialId);
                tagXDegrees.add(txdeg);
                tagYDegrees.add(tydeg);
                tagXPositions.add(tx);
                tagYPositions.add(ty);
                tagAreas.add(ta);

            }


        }

    }

    /**
     * Retrieves color blob data from the latest LLResult.
     *
     * @param result The latest LLResult from the Limelight3A camera.
     */
    public void getColorData(LLResult result){

        blobXDegrees.clear();
        blobYDegrees.clear();
        blobXPositions.clear();
        blobYPositions.clear();

        if(result != null && result.isValid()) {

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

    /**
     * Checks if an AprilTag with the specified ID is currently detected by the camera.
     *
     * @param ID The ID of the AprilTag to check for.
     *
     * @return True if the AprilTag with the specified ID is detected, false otherwise.
     */
    public boolean seesAprilTag(int ID){

        return tagIds.contains(ID);

    }

    /**
     * Retrieves the index of the specified AprilTag ID in the detected tags list.
     *
     * @param ID The ID of the AprilTag to find.
     *
     * @return The index of the AprilTag with the specified ID, or -1 if not found.
     */
    public int getAprilTagIndex(int ID){

        return tagIds.indexOf(ID);

    }

    //Getter methods for tag data
    public double getTx(int index){

        return tagXDegrees.get(index);

    }

    public double getTy(int index) {

        return tagYDegrees.get(index);

    }

    public double getTa(int index) {

        return tagAreas.get(index);

    }

}
