package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public class VisionSubsystem extends SubsystemBase {

    private Limelight3A limelight;

    public VisionSubsystem(final HardwareMap hMap, final String name){
        limelight = hMap.get(Limelight3A.class, name);
    }
}
