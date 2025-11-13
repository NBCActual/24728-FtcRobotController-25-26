package org.firstinspires.ftc.teamcode.subsystems;

import static com.seattlesolvers.solverslib.util.MathUtils.clamp;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class IntakeSubsystem extends SubsystemBase {
    
    private final MotorEx motorRotation;
    private final double maxRotationPower;

    public IntakeSubsystem(final HardwareMap hMap, final String name) {
        this(hMap, name, 1.0);
    }

    public IntakeSubsystem(final HardwareMap hMap, final String name, final double maxRotationPower) {
        this.maxRotationPower = maxRotationPower;
        motorRotation = new MotorEx(hMap, name);
        motorRotation.setInverted(false);
        motorRotation.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void intake() {
        motorRotation.setInverted(false);
        setPower(maxRotationPower);
    }

    public void outtake() {
        motorRotation.setInverted(true);
        setPower(maxRotationPower);
    }

    public void stop() {
        motorRotation.set(0.0);
    }

    private void setPower(double power) {
        motorRotation.set(clamp(power, 0.0, 1.0));
    }
}
