package org.firstinspires.ftc.teamcode.sandbox;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.SimpleServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class HardwareTestTeleOp extends LinearOpMode {

    private static final double MIN_ANGLE = 0;
    private static final double MAX_ANGLE = 255;

    @Override
    public void runOpMode() throws InterruptedException {


        Motor neverest_motor = new Motor(hardwareMap, "neverest_motor");
        neverest_motor.setRunMode(Motor.RunMode.RawPower);
        neverest_motor.resetEncoder();
        neverest_motor.setDistancePerPulse(18.0);

        Motor goBilda_motor = new Motor(hardwareMap, "gobilda_motor", Motor.GoBILDA.RPM_435);
        goBilda_motor.setRunMode(Motor.RunMode.RawPower);
        goBilda_motor.resetEncoder();
        goBilda_motor.setDistancePerPulse(18.0);

        SimpleServo studica_speed_servo = new SimpleServo(hardwareMap, "studica_speed", MIN_ANGLE, MAX_ANGLE, AngleUnit.DEGREES);
        SimpleServo studica_torque_servo = new SimpleServo(hardwareMap, "studica_torque", MIN_ANGLE, MAX_ANGLE, AngleUnit.DEGREES);

        // the extended gamepad object
        GamepadEx driverOp = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {
            if (driverOp.isDown(GamepadKeys.Button.A)) {
                neverest_motor.set(1);
            } else {
                neverest_motor.stopMotor();
            }

            if (driverOp.isDown(GamepadKeys.Button.B)) {
                goBilda_motor.set(1);
            } else {
                goBilda_motor.stopMotor();
            }

            if (driverOp.isDown(GamepadKeys.Button.DPAD_LEFT)) {
                studica_speed_servo.setPosition(1);
            } else if (driverOp.isDown(GamepadKeys.Button.DPAD_RIGHT)) {
                studica_speed_servo.setPosition(0);
            }

            if (driverOp.isDown(GamepadKeys.Button.DPAD_UP)) {
                studica_torque_servo.setPosition(1);
            } else if (driverOp.isDown(GamepadKeys.Button.DPAD_DOWN)) {
                studica_torque_servo.setPosition(0);
            }

            telemetry.addData("NeveRest Velocity", neverest_motor.getCorrectedVelocity());
            telemetry.addData("GoBilda Velocity", goBilda_motor.getCorrectedVelocity());
            telemetry.addData("NeveRest Distance", getDistanceTravelled(neverest_motor));
            telemetry.addData("GoBilda Distance", getDistanceTravelled(goBilda_motor));
            telemetry.addData("Studica Speed Servo Position", studica_speed_servo.getPosition());
            telemetry.addData("Studica Torque Servo Position", studica_torque_servo.getPosition());
            telemetry.update();

            driverOp.readButtons();}
    }

    public double getDistanceTravelled(Motor motor) {
        return motor.encoder.getDistance();
    }
}