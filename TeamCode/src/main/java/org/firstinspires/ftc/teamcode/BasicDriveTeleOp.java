package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.subsystems.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalLaunchSubsystem;


@TeleOp(name = "25-26 Basic Drive TeleOp", group = "TeleOp")
@Configurable // Panels
public class BasicDriveTeleOp extends CommandOpMode {


    private GamepadEx driverOp;
    private DriveSubsystem m_drive;
    private DriveCommand m_driveCommand;
    private VerticalLaunchSubsystem launchSubsystem;
    private Motor frontLeft, frontRight, rearLeft, rearRight;
    private RevBHIMU imu;

    @Override
    public void initialize() {
        // Initialization code can go here if needed
        frontLeft = new MotorEx(hardwareMap, Constants.FrontLeftDriveMotor);
        frontRight = new MotorEx(hardwareMap, Constants.FrontRightDriveMotor);
        rearLeft = new MotorEx(hardwareMap, Constants.RearLeftDriveMotor);
        rearRight = new MotorEx(hardwareMap, Constants.RearRightDriveMotor);

        m_drive = new DriveSubsystem(
                frontLeft,
                frontRight,
                rearLeft,
                rearRight
        );

        imu = new RevBHIMU(hardwareMap);
        imu.init(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )));

        driverOp = new GamepadEx(gamepad1);

        m_driveCommand = new DriveCommand(m_drive, () -> driverOp.getLeftX(), () -> -driverOp.getLeftY(), () -> -driverOp.getRightX());

        launchSubsystem = new VerticalLaunchSubsystem(hardwareMap, Constants.ExpansionMotor0);

        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .whenHeld(new RunCommand(launchSubsystem::launch, launchSubsystem))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        register(m_drive);
        m_drive.setDefaultCommand(m_driveCommand);
    }
}