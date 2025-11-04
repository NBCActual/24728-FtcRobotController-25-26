package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.CommandBase;

public class LaunchCommand extends CommandBase {
    // The subsystem the command runs on
    private final LaunchMotorSubsystem m_launchSubsystem;

    public LaunchCommand(LaunchMotorSubsystem subsystem) {
        m_launchSubsystem = subsystem;
        addRequirements(m_launchSubsystem);
    }

    @Override
    public void initialize() {
        m_launchSubsystem.launch();
    }

    @Override
    public boolean isFinished() {
        m_launchSubsystem.stop();
        return true;
    }
}
