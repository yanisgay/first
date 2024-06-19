// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
  TalonFX master;
  TalonFX slave;
  TalonFXConfigurator motorConfigurator;
  TalonFXConfiguration mTalonFXConfiguration;
  final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);
  public Elevator() {
    master = new TalonFX(Constants.ElevatorConstants.masterID);
    slave = new TalonFX(Constants.ElevatorConstants.slaveID);
    mTalonFXConfiguration.Feedback.SensorToMechanismRatio = Constants.ElevatorConstants.elevatorGearRatio;
    mTalonFXConfiguration.CurrentLimits.SupplyCurrentLimitEnable = true;
    mTalonFXConfiguration.CurrentLimits.SupplyCurrentLimit = 40;
    mTalonFXConfiguration.SoftwareLimitSwitch.ForwardSoftLimitEnable=true;
    mTalonFXConfiguration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = (30/Constants.ElevatorConstants.sprocketCircumference);
    mTalonFXConfiguration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;
    mTalonFXConfiguration.Audio.AllowMusicDurDisable = true;
    var slot0Configs = new Slot0Configs();
    slot0Configs.GravityType = GravityTypeValue.Elevator_Static;
    slot0Configs.kA = 0.0303;
    slot0Configs.kS = 0.1718;
    slot0Configs.kV = 1.356; 
    slot0Configs.kG = 0.082;
    slot0Configs.kP = 0
    
    ;
    slot0Configs.kI = 0;
    slot0Configs.kD = 0.0;
    mTalonFXConfiguration.Slot0 = slot0Configs;
    
    motorConfigurator = master.getConfigurator();
    motorConfigurator.apply(mTalonFXConfiguration);
    slave.setControl(new Follower(Constants.ElevatorConstants.masterID, false));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPercentage(double percent){
    master.set(percent);
  }

  public void setControl(double position){
    master.setControl(m_request.withPosition(position/Constants.ElevatorConstants.sprocketCircumference));
  }
}
