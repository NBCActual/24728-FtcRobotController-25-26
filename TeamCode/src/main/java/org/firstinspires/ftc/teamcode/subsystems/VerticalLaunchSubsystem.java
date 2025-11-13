package org.firstinspires.ftc.teamcode.subsystems;

import static com.seattlesolvers.solverslib.util.MathUtils.clamp;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class VerticalLaunchSubsystem extends SubsystemBase {

    private final Motor motorRotation;
    private final CRServo guideMotor;
    private double launchPower = 1.0;
    private double guideMotorPowerFactor = 1.0;
    private double reversePower = 0.75;

    public VerticalLaunchSubsystem(final HardwareMap hMap, final String name) {
        this(hMap, name, null, 1.0, 1.0, 0.75);
    }

    public VerticalLaunchSubsystem(final HardwareMap hMap, final String motorName, String guidMotorName, double launchPower, double guideMotorPowerFactor, double reversePower) {
        motorRotation = new MotorEx(hMap, motorName);
        if(guidMotorName != null) {
            guideMotor = new CRServo(hMap, guidMotorName);
        } else {
            guideMotor = null;
        }
        this.launchPower = launchPower;
        this.guideMotorPowerFactor = guideMotorPowerFactor;
        this.reversePower = reversePower;
        motorRotation.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void launch() {
        enableLaunchMotor(launchPower, false);
        enableGuideMotor((launchPower * guideMotorPowerFactor), false);
    }

    public void reverse() {
        enableLaunchMotor(reversePower, true);
        enableGuideMotor((reversePower * guideMotorPowerFactor), true);
    }

    public void stop() {
        motorRotation.set(0.0);
        if(guideMotor != null) {
            guideMotor.set(0.0);
        }
    }

    public void enableLaunchMotor(double power, boolean inverted) {
        motorRotation.setInverted(inverted);
        motorRotation.set(clamp(power, 0.0, 1.0));
    }

    public void enableGuideMotor(double power, boolean inverted) {
        if(guideMotor != null) {
            guideMotor.setInverted(inverted);
            guideMotor.set(clamp(power, 0.0, 1.0));
        }
    }
}
