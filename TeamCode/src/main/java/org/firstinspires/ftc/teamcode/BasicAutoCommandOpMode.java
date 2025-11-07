package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.util.TelemetryData;

import org.firstinspires.ftc.teamcode.pathing.DemoPath;

@Autonomous(name = "25-26 Auto Command OpMode", group = "Autonomous")
@Configurable // Panels
public class BasicAutoCommandOpMode extends CommandOpMode {
    private Follower follower;
    private DemoPath paths;
    TelemetryData telemetryData = new TelemetryData(telemetry);

    private String schedulerStatus = "Not Started";
    private String pathStatus = "Not Started";
    private String callbackStatus = "Not Started";
    private int callbackCount = 0;

    public void buildPaths() {
        paths = new DemoPath(follower); // Build paths
    }

    // Mechanism commands - replace these with your actual subsystem commands
    private InstantCommand pathCallback() {
        return new InstantCommand(() -> {
            callbackCount++;
            callbackStatus = "Callback " + callbackCount + " executed";
        });
    }

    @Override
    public void initialize() {
        super.reset();

        // Initialize follower
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(paths.getStartingPose());

        // Create the autonomous command sequence
        SequentialCommandGroup autonomousSequence = new SequentialCommandGroup(

                // Initial path to first position
                new FollowPathCommand(follower, paths.Path1)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path1 Completed";
                        }),
                pathCallback(),

                new FollowPathCommand(follower, paths.Path2).setGlobalMaxPower(0.5)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path2 Completed";
                        }), // Sets globalMaxPower to 50% for all future paths
                // (unless a custom maxPower is given)
                pathCallback(),

                new FollowPathCommand(follower, paths.Path3)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path3 Completed";
                        }),
                pathCallback(),

                new FollowPathCommand(follower, paths.Path4)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path4 Completed";
                        }),
                pathCallback(),

                new FollowPathCommand(follower, paths.Path5, 1.0)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path5 Completed";
                        }), // Overrides maxPower to 100% for this path only
                pathCallback(),

                new FollowPathCommand(follower, paths.Path6)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path6 Completed";
                        }),
                pathCallback(),

                new FollowPathCommand(follower, paths.Path7, false)
                        .whenFinished(() -> {
                            /* RUNNABLE */
                            pathStatus = "Path6 Completed";
                        }),
                pathCallback()
        );

        schedulerStatus = "Initializing";
        // Schedule the autonomous sequence
        schedule(autonomousSequence);
        schedulerStatus = "Initialized";
    }

    @Override
    public void run() {
        super.run();

        telemetryData.addData("Scheduler Status", schedulerStatus);
        telemetryData.addData("Path Status", pathStatus);
        telemetryData.addData("Callback Status", callbackStatus);
        telemetryData.addData("X", follower.getPose().getX());
        telemetryData.addData("Y", follower.getPose().getY());
        telemetryData.addData("Heading", follower.getPose().getHeading());
        telemetryData.update();
    }
}
