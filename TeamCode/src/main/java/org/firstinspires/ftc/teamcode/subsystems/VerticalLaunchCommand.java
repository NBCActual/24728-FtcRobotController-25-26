package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.CommandBase;

public class VerticalLaunchCommand extends CommandBase {
    // The subsystem the command runs on
    private final VerticalLaunchSubsystem m_launchSubsystem;

    public VerticalLaunchCommand(VerticalLaunchSubsystem subsystem) {
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
