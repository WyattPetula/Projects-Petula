package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BalltrackSubsystem;

public class TestBallTrackCommand extends CommandBase{
    public boolean isBallTrackActive = false;
    private BalltrackSubsystem balltrackSubsystem;

    public TestBallTrackCommand(BalltrackSubsystem balltrackSubsystem) {
        this.balltrackSubsystem = balltrackSubsystem;
    }

    @Override
    public void execute() {
        if (balltrackSubsystem.isBalltackFull())
            balltrackSubsystem.stopBallTrack();
            
        else if (balltrackSubsystem.isBallPresentInChamber())
            balltrackSubsystem.runConveyorOnly();
        else
            balltrackSubsystem.testRunBallTrack();
    }

    @Override
    public void end(boolean interrupted) {
        balltrackSubsystem.stopBallTrack();
    }
}
