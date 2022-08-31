package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BalltrackSubsystem;

public class RunIntakeCommand extends CommandBase {
    private BalltrackSubsystem balltrackSubsystem;

    public RunIntakeCommand(BalltrackSubsystem balltrackSubsystem) {
        this.balltrackSubsystem = balltrackSubsystem;
        addRequirements(balltrackSubsystem);
    }
}
