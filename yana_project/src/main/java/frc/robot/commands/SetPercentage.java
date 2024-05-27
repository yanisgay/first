// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class SetPercentage extends Command {
  /** Creates a new SetPercentage. */
  Elevator elle;
  double percent;

  public SetPercentage(double percent) {
    // Use addRequirements() here to declare subsystem dependencies.
    elle = new Elevator();
    addRequirements(elle);
    this.percent = percent;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elle.setPercentage(percent);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
