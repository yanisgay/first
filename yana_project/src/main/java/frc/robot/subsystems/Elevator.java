// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  TalonFX master;
  TalonFX slave;
  public Elevator() {
    master = new TalonFX(Constants.ElevatorConstants.masterID);
    slave = new TalonFX(Constants.ElevatorConstants.slaveID);
    slave.setControl(new Follower(Constants.ElevatorConstants.masterID, false));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPercentage(double percent){
    master.set(percent);
  }
}
