package org.firstinspires.ftc.teamcode.sandbox;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    private Limelight3A limelight;

    public VisionSubsystem(final HardwareMap hMap, final String name){
        limelight = hMap.get(Limelight3A.class, name);
    }
}
