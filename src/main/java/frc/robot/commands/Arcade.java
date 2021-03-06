/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;

public class Arcade extends CommandBase {

  private final Drive driveTrain;

  public static double speed = 0.0;
  public static double rotation = 0.0;

  public Arcade(Drive subsystem) {
    driveTrain = subsystem;
    addRequirements(subsystem);
    // subsystem.setDefaultCommand(this);
  }

  @Override
  public void execute() {
    speed = RobotContainer.joy.getRawAxis(1);
    rotation = RobotContainer.joy.getRawAxis(0);
    if (RobotContainer.joy.getAButton()) {
      speed = -.85;
    }

    driveTrain.arcadeDrive(speed * Constants.SPEED, -rotation * Constants.SPEED);
  }
}
