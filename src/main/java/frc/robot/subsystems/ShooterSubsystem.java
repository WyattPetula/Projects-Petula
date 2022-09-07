import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ShooterSubsystem extends SubsystemBase implements Updatable {
    //Motors
    private final WPI_TalonFX climberMotor = new WPI_TalonFX(ShooterConstants.SHOOTER_REAR_MOTOR_PORT, GlobalConstants.CANIVORE_NAME);
    
    private final DoubleSolenoid climberSolonoid = new DoubleSolenoid(
        GlobalConstants.PCM_ID, 
        PneumaticsModuleType.REVPH, 
        ShooterConstants.SHOOTER_SOLENOID_FORWARD_CHANNEL, 
        ShooterConstants.SHOOTER_SOLENOID_REVERSE_CHANNEL
    )
}