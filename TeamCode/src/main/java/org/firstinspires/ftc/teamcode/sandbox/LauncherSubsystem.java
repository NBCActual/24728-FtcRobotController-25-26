package org.firstinspires.ftc.teamcode.sandbox;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.util.Timing;

public class LauncherSubsystem  extends SubsystemBase {

    private final Motor rotationMotor;
    private final Timing.Timer launchTimer;

    public LauncherSubsystem(final HardwareMap hMap, final String name) {
        rotationMotor = hMap.get(Motor.class, name);
        launchTimer = new Timing.Timer(1);
    }

    /**
     * Launches ball.
     */
    public void launch() {
        launchTimer.start();

        while (!launchTimer.done()) {
            rotationMotor.set(1.0);
        }

        this.stop();
    }

    /**
     * Stops the motor rotation.
     */
    public void stop() {
        rotationMotor.set(0.0);
    }
}
