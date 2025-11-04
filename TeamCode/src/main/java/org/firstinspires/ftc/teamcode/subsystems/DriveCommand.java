package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    // The subsystem the command runs on
    private final DriveSubsystem m_driveSubsystem;
    private final DoubleSupplier forward;
    private final DoubleSupplier strafe;
    private final DoubleSupplier rotate;

    public DriveCommand(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier rotate) {
        m_driveSubsystem = subsystem;
        this.forward = forward;
        this.strafe = strafe;
        this.rotate = rotate;
        addRequirements(m_driveSubsystem);
    }

    @Override
    public void execute() {
        m_driveSubsystem.drive(forward.getAsDouble(), strafe.getAsDouble(), rotate.getAsDouble());
    }
}
