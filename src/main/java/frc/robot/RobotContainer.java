/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Tank;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.subsystems.DrivePID;

public class RobotContainer {

  // (1/ GEARING) * WHEEL_DIAMETER * PI
  private final double ENCODER_CONSTANT = -(1 / 8.45) * 0.0983 * Math.PI;

  private final DrivePID leftPID = new DrivePID("LeftDrive", Constants.LDRIVE_P, Constants.LDRIVE_D, Constants.LDRIVE_S,
      Constants.LDRIVE_V, 0.05, Constants.K_DRIVE_LEFT_FRONT_ID, Constants.K_DRIVE_LEFT_BACK_ID, ENCODER_CONSTANT,
      Constants.K_DRIVE_LEFT_INVERT);
  private final DrivePID rightPID = new DrivePID("RightDrive", Constants.RDRIVE_P, Constants.RDRIVE_D, Constants.RDRIVE_S,
      Constants.RDRIVE_V, 0.05, Constants.K_DRIVE_RIGHT_FRONT_ID, Constants.K_DRIVE_RIGHT_BACK_ID, ENCODER_CONSTANT,
      Constants.K_DRIVE_RIGHT_INVERT);
  private final Tank m_autoCommand = new Tank(leftPID, rightPID);

  public static AHRS navx = new AHRS(SPI.Port.kMXP);
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