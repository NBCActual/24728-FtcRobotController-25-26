package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class DriveSubsystem extends SubsystemBase {

    private final MecanumDrive drive;

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem(Motor frontLeft,
                          Motor frontRight,
                          Motor rearLeft,
                          Motor rearRight) {

        drive = new MecanumDrive(
                frontLeft,
                frontRight,
                rearLeft,
                rearRight);
    }

    /**
     * Creates a new DriveSubsystem with the hardware map and configuration names.
     */
    public DriveSubsystem(HardwareMap hMap, final String frontLeftName, String frontRightName,
                          String rearLeftName, String rearRightName) {
        this(new Motor(hMap, frontLeftName),
             new Motor(hMap, frontRightName),
             new Motor(hMap, rearLeftName),
             new Motor(hMap, rearRightName));
    }

    public void drive(double forward, double strafe, double rotate) {
        drive.driveRobotCentric(forward, strafe, rotate);
    }
}
