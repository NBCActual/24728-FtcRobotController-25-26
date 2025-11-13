package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VerticalLaunchSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SensorSubsystem;


@TeleOp(name = "25-26 Basic Drive With Extras TeleOp", group = "TeleOp")
@Configurable // Panels
public class BasicDriveWithExtrasTeleOp extends CommandOpMode {

    private GamepadEx driverOp;
    private DriveSubsystem m_drive;
    private DriveCommand m_driveCommand;
    private VerticalLaunchSubsystem launchSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private SensorSubsystem sensorSubsystem;
    private RevBHIMU imu;
    private CRServo crServo;

    TelemetryData telemetryData = new TelemetryData(telemetry);

    @Override
    public void initialize() {

        driverOp = new GamepadEx(gamepad1);

        m_drive = new DriveSubsystem(hardwareMap,
                Constants.FrontLeftDriveMotor,
                Constants.FrontRightDriveMotor,
                Constants.RearLeftDriveMotor,
                Constants.RearRightDriveMotor
        );

        sensorSubsystem = new SensorSubsystem(hardwareMap);
        launchSubsystem = new VerticalLaunchSubsystem(hardwareMap, Constants.ExpansionMotor0);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, Constants.ExpansionMotor1);

        crServo = new CRServo(hardwareMap, Constants.Servo1);

        imu = new RevBHIMU(hardwareMap);
        imu.init(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )));

        m_driveCommand = new DriveCommand(m_drive, () -> driverOp.getLeftX(), () -> -driverOp.getLeftY(), () -> -driverOp.getRightX());

        driverOp.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new RunCommand(launchSubsystem::launch, launchSubsystem))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.B)
                .whileHeld(new RunCommand(launchSubsystem::reverse, launchSubsystem))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whileHeld(new InstantCommand(() -> {
                    launchSubsystem.enableLaunchMotor(1.0, false);
                }))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whileHeld(new InstantCommand(() -> {
                    launchSubsystem.enableLaunchMotor(1.0, true);
                }))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whileHeld(new InstantCommand(() -> {
                    launchSubsystem.enableGuideMotor(1.0, false);
                }))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whileHeld(new InstantCommand(() -> {
                    launchSubsystem.enableGuideMotor(1.0, true);
                }))
                .whenReleased(new InstantCommand(launchSubsystem::stop, launchSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new RunCommand(intakeSubsystem::intake, intakeSubsystem))
                .whenReleased(new InstantCommand(intakeSubsystem::stop, intakeSubsystem));

        driverOp.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new RunCommand(intakeSubsystem::outtake, intakeSubsystem))
                .whenReleased(new InstantCommand(intakeSubsystem::stop, intakeSubsystem));

        register(m_drive);
        m_drive.setDefaultCommand(m_driveCommand);
    }

    @Override
    public void run() {
        super.run();

        double distance = sensorSubsystem.getDistance(Constants.DistanceSensor1, DistanceUnit.INCH);
        int color = sensorSubsystem.getColor(Constants.ColorSensor1);
        boolean isPressed = sensorSubsystem.isTouchPressed(Constants.TouchSensor1);

        telemetryData.addData("Distance", distance);
        telemetryData.addData("Color", color);
        telemetryData.addData("Touch: IsPressed", isPressed);
        telemetryData.update();
    }
}