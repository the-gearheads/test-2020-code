/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;
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
    leftTrain.enable();
    rightTrain.enable();
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("angle", RobotContainer.navx.getAngle());
    if (RobotContainer.joy.getAButtonPressed()) {
      leftTrain.enable();
      rightTrain.enable();
    } else if (RobotContainer.joy.getBButtonPressed()) {
      leftTrain.disable();
      rightTrain.disable();
    }
    
    int angle = RobotContainer.joy.getPOV(0);
    double speed = 0;
    int invert = 1;
    if (angle == 0) {
      speed = -1;
    } else if (angle == 90) {
      speed = -.1;
      invert = -1;
    } else if (angle == 180) {
      speed = 1;
    } else if (angle == 270) {
      speed = .1;
      invert = -1;
    }
    
    leftTrain.setSetpoint(speed * Constants.SPEED);
    rightTrain.setSetpoint(speed * Constants.SPEED * invert);
  }
}