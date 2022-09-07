package frc.robot;

public class Constants {
    public static final class GlobalConstants {
        public static final int PCM_ID = 17;
        public static final String CANIVORE_NAME = "CANivore";
    }
    public static final class TimesliceConstants {
        public static final float robotPeriodicAllocation = 0.005f;
        public static final float controllerPeriod = 0.002f;
    }
    public static final class BalltrackConstants {
        public static final int BALLTRACK_CONVEYOR_MOTOR_PORT = 11;
        public static final int BALLTRACK_CHAMBER_MOTOR_PORT = 12;
        public static final int BALLTRACK_INTAKE_MOTOR_PORT = 10;

        public static final int BALLTRACK_INTAKE_SOLENOID_FORWARD_CHANNEL = 0;
        public static final int BALLTRACK_INTAKE_SOLENOID_REVERSE_CHANNEL = 1;

        public static final int BALLTRACK_CONVEYOR_SENSOR_PORT = 0;
        public static final int BALLTRACK_CHAMBER_SENSOR_PORT = 1;

        public static final int BALLTRACK_TEST_CONVEYOR_PERCENT_OUTPUT = 1;
        public static final int BALLTRACK_TEST_CHAMBER_PERCENT_OUTPUT = 1;
    }
    public static final class ShooterConstants {
        public static final int SHOOTER_SOLENOID_FORWARD_CHANNEL = 4;
        public static final int SHOOTER_SOLENOID_REVERSE_CHANNEL = 5;
        public static final int SHOOTER_REAR_MOTOR_PORT = 8;
        public static final int SHOOTER_FRONT_MOTOR_PORT = 9;
    }
}
