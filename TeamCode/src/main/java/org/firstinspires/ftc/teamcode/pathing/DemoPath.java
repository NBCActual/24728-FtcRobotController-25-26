package org.firstinspires.ftc.teamcode.pathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import java.util.Arrays;
import java.util.List;

public class DemoPath {

    private Pose startingPose;

    public PathChain Path1;
    public PathChain Path2;
    public PathChain Path3;
    public PathChain Path4;
    public PathChain Path5;
    public PathChain Path6;
    public PathChain Path7;

    public DemoPath(Follower follower) {

        startingPose = new Pose(56.000, 8.000);

        Path1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startingPose, new Pose(56.000, 36.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();

        Path2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(56.000, 36.000), new Pose(14.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        Path3 = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(14.000, 36.000),
                                new Pose(0.000, 36.000),
                                new Pose(0.000, 59.000),
                                new Pose(14.000, 59.798)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .build();

        Path4 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.000, 59.798), new Pose(56.000, 59.655))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(360))
                .build();

        Path5 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(56.000, 59.655), new Pose(86.200, 38.961))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .build();

        Path6 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(86.200, 38.961), new Pose(72.071, 8.135))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .build();

        Path7 = follower
                .pathBuilder()
                .addPath(new BezierLine(new Pose(72.071, 8.135), new Pose(56.000, 8.000)))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-270))
                .build();
    }

    public List<PathChain> getAllPaths() {
        return Arrays.asList(Path1, Path2, Path3, Path4, Path5, Path6, Path7);
    }

    public Pose getStartingPose() {
        return startingPose;
    }
}
