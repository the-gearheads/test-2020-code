/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControllerInput;
import frc.robot.subsystems.Drive;
import frc.robot.Constants;

public class DriveCommand extends CommandBase {

  private final Drive driveTrain;

  public static ControllerInput leftSpeed;
  public static ControllerInput rightSpeed;

  public DriveCommand(Drive subsystem) {
    driveTrain = subsystem;
    addRequirements(subsystem);
    subsystem.setDefaultCommand(this);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(leftSpeed.Value() * Constants.SPEED, rightSpeed.Value() * Constants.SPEED);
  }
}