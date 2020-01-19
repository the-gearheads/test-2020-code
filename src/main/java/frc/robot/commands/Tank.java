/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivePID;

public class Tank extends CommandBase {

  private final DrivePID leftTrain;
  private final DrivePID rightTrain;

  public static double leftSpeed = 0.0;
  public static double rightSpeed = 0.0;

  public Tank(DrivePID leftPID, DrivePID rightPID) {
    leftTrain = leftPID;
    rightTrain = rightPID;
    addRequirements(leftPID);
    addRequirements(rightPID);
  }

  @Override
  public void execute() {
    if (RobotContainer.joy.getAButton()) {
      leftTrain.enable();
      rightTrain.enable();
      leftTrain.setSetpoint(0.05);
      rightTrain.setSetpoint(0.05);
    } else if (RobotContainer.joy.getBButton()) {
      leftTrain.disable();
      rightTrain.disable();
      leftTrain.setSetpoint(0.05);
      rightTrain.setSetpoint(0.05);
    }
  }
}