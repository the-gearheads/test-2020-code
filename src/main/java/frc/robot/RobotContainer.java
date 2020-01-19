/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Tank;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.LeftSidePID;
import frc.robot.subsystems.RightSidePID;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.XboxController;

public class RobotContainer {

  private final LeftSidePID leftPID = new LeftSidePID();
  private final RightSidePID rightPID = new RightSidePID();
  private final Tank m_autoCommand = new Tank(leftPID, rightPID);

  public static XboxController joy = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}