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
import frc.robot.Constants;

public class Tank extends CommandBase {

  private final Drive driveTrain;

  public static double leftSpeed = 0.0;
  public static double rightSpeed = 0.0;

  public Tank(Drive subsystem) {
    driveTrain = subsystem;
    addRequirements(subsystem);
    subsystem.setDefaultCommand(this);
  }

  @Override
  public void execute() {
    leftSpeed = RobotContainer.joy.getX();
    rightSpeed = RobotContainer.joy.getY();
    driveTrain.tankDrive(leftSpeed * Constants.SPEED, rightSpeed * Constants.SPEED);
  }
}