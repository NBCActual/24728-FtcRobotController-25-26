package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class LaunchMotorSubsystem extends SubsystemBase {

    private final Motor motorRotation;

    public LaunchMotorSubsystem(final HardwareMap hMap, final String name) {
        motorRotation = new MotorEx(hMap, name);
        motorRotation.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void launch() {
        motorRotation.set(1.0);
    }

    public void stop() {
        motorRotation.set(0.0);
    }
}
