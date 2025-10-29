package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "25-26 Basic Drive TeleOp", group = "TeleOp")
public class BasicDriveTeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        // Initialization code can go here if needed
    }

    @Override
    public void runOpMode() throws InterruptedException {

        GamepadEx driverOp = new GamepadEx(gamepad1);
        SensorRevColorV3 colorSensor = new SensorRevColorV3(hardwareMap, Constants.ColorSensor);
        MecanumDrive drive = getMecanumDrive();

        // This is the built-in IMU in the REV hub.
        // We're initializing it by its default parameters
        // and name in the config ('imu'). The orientation
        // of the hub is important. Below is a model
        // of the REV H ub and the orientation axes for the IMU.
        //
        //                           | Z axis
        //                           |
        //     (Motor Port Side)     |   / X axis
        //                       ____|__/____
        //          Y axis     / *   | /    /|   (IO Side)
        //          _________ /______|/    //      I2C
        //                   /___________ //     Digital
        //                  |____________|/      Analog
        //
        //                 (Servo Port Side)
        //
        // (updated class to use the new RevBHIMU wrapper)

        RevBHIMU imu = new RevBHIMU(hardwareMap);

        imu.init(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )));

        telemetry.setAutoClear(true);

        waitForStart();

        while (!isStopRequested()) {

            // Driving the mecanum base takes 3 joystick parameters: leftX, leftY, rightX.
            // These are related to the left stick x value, left stick y value, and
            // right stick x value respectively. These values are passed in to represent the
            // strafing speed, the forward speed, and the turning speed of the robot frame
            // respectively from [-1, 1].

            drive.driveRobotCentric(
                    driverOp.getLeftX(),
                    -driverOp.getLeftY(),
                    -driverOp.getRightX(),
                    false
            );

            int[] argb = colorSensor.getARGB();
            double colorDistance = colorSensor.distance(DistanceUnit.MM);

            telemetry.addData("Alpha", argb[0]);
            telemetry.addData("Red", argb[1]);
            telemetry.addData("Green", argb[2]);
            telemetry.addData("Blue", argb[3]);
            telemetry.addData("Distance (mm)", colorDistance);
            telemetry.update();
        }
    }

    @NonNull
    private MecanumDrive getMecanumDrive() {
        MotorEx frontLeft = new MotorEx(hardwareMap, Constants.FrontLeftDriveMotor);
        MotorEx frontRight = new MotorEx(hardwareMap, Constants.FrontRightDriveMotor);
        MotorEx rearLeft = new MotorEx(hardwareMap, Constants.RearLeftDriveMotor);
        MotorEx rearRight = new MotorEx(hardwareMap, Constants.RearRightDriveMotor);

        // constructor takes in frontLeft, frontRight, backLeft, backRight motors
        // IN THAT ORDER
        return new MecanumDrive(
                frontLeft,
                frontRight,
                rearLeft,
                rearRight
        );
    }
}