package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.CommandBase;

public class LaunchCommand extends CommandBase {

    private final LauncherSubsystem launcherSubsystem;

    public LaunchCommand(final LauncherSubsystem launcherSubsystem) {
        this.launcherSubsystem = launcherSubsystem;
        addRequirements(launcherSubsystem);
    }

    @Override
    public void initialize() {
        launcherSubsystem.launch();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
