/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.LeftSidePID;
import frc.robot.subsystems.RightSidePID;
import frc.robot.Constants;

public class Tank extends CommandBase {

  private final LeftSidePID leftTrain;
  private final RightSidePID rightTrain;

  public static double leftSpeed = 0.0;
  public static double rightSpeed = 0.0;

  public Tank(LeftSidePID leftPID, RightSidePID rightPID) {
    leftTrain = leftPID;
    rightTrain = rightPID;
    // leftTrain.enable();
    // rightTrain.enable();
    addRequirements(leftPID);
    addRequirements(rightPID);
    // subsystem.setDefaultCommand(this);
  }

  @Override
  public void execute() {
    // leftSpeed = RobotContainer.joy.getRawAxis(0);
    // rightSpeed = RobotContainer.joy.getRawAxis(2);
    double speed = 0;
    if (RobotContainer.joy.getAButton()) {
      leftTrain.enable();
      rightTrain.enable();
      // speed = 0.0001;
    } else if (RobotContainer.joy.getBButton()) {
      leftTrain.disable();
      rightTrain.disable();
      // speed = -0.0001;
    }
    // leftTrain.setSetpoint(speed);
    // rightTrain.setSetpoint(speed);
    // leftTrain.(speed * Constants.SPEED);
    // rightTrain.setSpeed(speed * Constants.SPEED);
  }
}