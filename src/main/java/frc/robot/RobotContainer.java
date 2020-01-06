/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;

public class RobotContainer {

  private final Drive m_exampleSubsystem = new Drive();
  private final DriveCommand m_autoCommand = new DriveCommand(m_exampleSubsystem);

  XboxController joy = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    DriveCommand.leftSpeed = () -> joy.getRawAxis(0);
    DriveCommand.rightSpeed = () -> joy.getRawAxis(1);
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}