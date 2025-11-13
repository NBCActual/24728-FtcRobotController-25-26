package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.SensorRevTOFDistance;

import org.firstinspires.ftc.teamcode.SensorRevColorV3;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorSubsystem extends SubsystemBase {

    private final HardwareMap hardwareMap;

    public SensorSubsystem(final HardwareMap hMap) {
        this.hardwareMap = hMap;
    }

    public SensorRevTOFDistance getDistanceSensor(String name) {
        return new SensorRevTOFDistance(hardwareMap, name);
    }

    public SensorRevColorV3 getColorSensor(String name) {
        return new SensorRevColorV3(hardwareMap, name);
    }

    public RevTouchSensor getTouchSensor(String name) {
        return hardwareMap.get(RevTouchSensor.class, name);
    }

    public double getDistance(String name, DistanceUnit unit) {
        SensorRevTOFDistance sensor = getDistanceSensor(name);
        if(sensor != null) {
            return sensor.getDistance(unit);
        }
        return -1; // or throw an exception
    }

    public int getColor(String name) {
        SensorRevColorV3 sensor = getColorSensor(name);
        if(sensor != null) {
            int r = sensor.red();
            int g = sensor.green();
            int b = sensor.blue();
            int a = sensor.alpha();
            return Color.argb(a, r, g, b);
        }
        return 0; // or throw an exception
    }

    public double getColorProximity(String name, DistanceUnit unit) {
        SensorRevColorV3 sensor = getColorSensor(name);
        if(sensor instanceof DistanceSensor) {
            DistanceSensor distSensor = (DistanceSensor) sensor;
            return distSensor.getDistance(unit);
        }
        return 0; // or throw an exception
    }

    public boolean isTouchPressed(String name) {
        RevTouchSensor sensor = getTouchSensor(name);
        if(sensor != null) {
            return sensor.isPressed();
        }
        return false; // or throw an exception
    }
}
