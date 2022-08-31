package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.BalltrackConstants;
import frc.robot.Constants.GlobalConstants;

public class BalltrackSubsystem extends SubsystemBase {
    //Balltrack
    private final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(BalltrackConstants.BALLTRACK_CONVEYOR_MOTOR_PORT);
    private final WPI_TalonSRX chamberMotor = new WPI_TalonSRX(BalltrackConstants.BALLTRACK_CHAMBER_MOTOR_PORT);
    private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(BalltrackConstants.BALLTRACK_INTAKE_MOTOR_PORT);

    private final AnalogInput conveyorProximitySensor = new AnalogInput(BalltrackConstants.BALLTRACK_CONVEYOR_SENSOR_PORT);
    private final AnalogInput chamberProximitySensor = new AnalogInput(BalltrackConstants.BALLTRACK_CHAMBER_SENSOR_PORT);

    private final double CONVEYOR_MOTOR_PERCENT_OUTPUT = 0.9;
    private final double CHAMBER_MOTOR_PERCENT_OUTPUT = 1;
    private final double INTAKE_MOTOR_PERCENT_OUTPUT = 1;

    private final double TEST_CONVEYOR_PERCENT_OUTPUT = BalltrackConstants.BALLTRACK_TEST_CONVEYOR_PERCENT_OUTPUT;
    private final double TEST_CHAMBER_PERCENT_OUTPUT = BalltrackConstants.BALLTRACK_TEST_CHAMBER_PERCENT_OUTPUT;
    
    private final double PROXIMITY_SENSOR_THRESHOLD = 50;

    //BalltrackMode is an enum, a special type of variable.
    private BalltrackMode balltrackMode = BalltrackMode.DISABLED;

    //Intake
    private final DoubleSolenoid intakeDoubleSolenoid = new DoubleSolenoid(
        GlobalConstants.PCM_ID, 
        PneumaticsModuleType.REVPH, 
        BalltrackConstants.BALLTRACK_INTAKE_SOLENOID_FORWARD_CHANNEL, 
        BalltrackConstants.BALLTRACK_INTAKE_SOLENOID_REVERSE_CHANNEL);

    private String trackStatus = "DISABLED";

    //Network tables
    private NetworkTable ballTrackTable;
    private NetworkTableEntry ballTrackStatus;
    private NetworkTableEntry conveyorSensorStatus;
    private NetworkTableEntry chamberSensorStatus;
    private NetworkTableEntry testConveyorPercentOutput;
    private NetworkTableEntry testChamberPercentOutput;

    public BalltrackSubsystem() {

        conveyorMotor.setInverted(true);
        chamberMotor.setInverted(true);

        //Set up the ball track table using the default table setup.
        ballTrackTable = NetworkTableInstance.getDefault().getTable("Balltrack");

        //Specify which key each value belongs to in the network table.
        ballTrackStatus = ballTrackTable.getEntry("ballTrackStatus");
        conveyorSensorStatus = ballTrackTable.getEntry("ballPresentInConveyor");
        chamberSensorStatus = ballTrackTable.getEntry("ballPresentInChamber");
        testConveyorPercentOutput = ballTrackTable.getEntry("testConveyorOutput");
        testChamberPercentOutput = ballTrackTable.getEntry("testChamberOutput");
    }
    
    //Add values to the network table.
    public void periodic() {
        ballTrackStatus.setString(trackStatus);
        conveyorSensorStatus.setBoolean(isBallPresentInConveyor());
        chamberSensorStatus.setBoolean(isBallPresentInChamber());

        testConveyorPercentOutput.setDouble(TEST_CONVEYOR_PERCENT_OUTPUT);
        testChamberPercentOutput.setDouble(TEST_CHAMBER_PERCENT_OUTPUT);
    }
    //Manage how the balltrack will respond in a certain state.
    public void update() {
        switch (balltrackMode) {
            case DISABLED:
                retractIntake();
                stopIntakeMotor();
                stopBallTrack();
                break;
            case INTAKE:
                extendIntake();
                runIntake();
                break;
        }
    }
    
    //Defines all possible states the balltrack can be in.
    public enum BalltrackMode {
        DISABLED,
        INTAKE,
    }

    public boolean isBalltrackFull() {
        return isBallPresentInConveyor() && isBallPresentInChamber();
    }

    public boolean isBallPresentInConveyor() {
		return conveyorProximitySensor.getValue() < PROXIMITY_SENSOR_THRESHOLD;
	}

	public boolean isBallPresentInChamber() {
		return chamberProximitySensor.getValue() < PROXIMITY_SENSOR_THRESHOLD;
	}

    public void extendIntake() {
        
    }

    public BalltrackMode getBalltrackMode() {
        return balltrackMode;
    }
    
    public void runBallTrack() {
        conveyorMotor.set(CONVEYOR_MOTOR_PERCENT_OUTPUT);
        chamberMotor.set(CHAMBER_MOTOR_PERCENT_OUTPUT);
        trackStatus = "ENABLED";
    }
    
    public void runConveyorOnly() {
        chamberMotor.stopMotor();
        conveyorMotor.set(CONVEYOR_MOTOR_PERCENT_OUTPUT);
    }

    public void retractIntake() {
        
    }

    public void runIntakeMotor() {
        intakeMotor.set(INTAKE_MOTOR_PERCENT_OUTPUT);
    }
    
    //Handle running the intake.
    public void runIntake() {
        if (isBalltrackFull()) {
            stopBallTrack();
            stopIntakeMotor();
        } 
        else if (isBallPresentInChamber()) {
            runConveyorOnly();
            stopChamber();
            runIntakeMotor();
        } 
        else {
            runBallTrack();
            runIntakeMotor();
        }
    }

    public void setBalltrackMode(BalltrackMode balltrackMode) {
        this.balltrackMode = balltrackMode;
    }

    public void stopBallTrack() {
        conveyorMotor.stopMotor();
        chamberMotor.stopMotor();
    }

    public void stopConveyor() {
        conveyorMotor.stopMotor();
    }

    public void stopChamber() {
        chamberMotor.stopMotor();
    }

    public void stopIntakeMotor() {
        intakeMotor.stopMotor();
    }

    public void testRunBallTrack() {
        conveyorMotor.set(TEST_CONVEYOR_PERCENT_OUTPUT);
        chamberMotor.set(TEST_CHAMBER_PERCENT_OUTPUT);
    }
}
