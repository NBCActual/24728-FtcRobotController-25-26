package org.firstinspires.ftc.teamcode;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static String FrontLeftDriveMotor = "left_front_drive";

    public static String FrontRightDriveMotor = "right_front_drive";

    public static String RearLeftDriveMotor = "left_rear_drive";

    public static String RearRightDriveMotor = "right_rear_drive";

    public static String ColorSensor = "front_color_sensor";

    public static String ExpansionMotor0 = "expansion_motor_zero";

    public static FollowerConstants followerConstants = new FollowerConstants()
            .centripetalScaling(0.005)
            .forwardZeroPowerAcceleration(-41.278)
            .lateralZeroPowerAcceleration(-59.7819)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1, 0, 0.01, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(2, 0, 0.1, 0))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.1, 0, 0, 0.6, 0))
            .mass(5);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .xVelocity(57.8741)
            .yVelocity(52.295)
            .maxPower(1)
            .leftFrontMotorName(FrontLeftDriveMotor)
            .rightFrontMotorName(FrontRightDriveMotor)
            .leftRearMotorName(RearLeftDriveMotor)
            .rightRearMotorName(RearRightDriveMotor)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .motorCachingThreshold(0.01)
            .useBrakeModeInTeleOp(false);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}