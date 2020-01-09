/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Motor;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

  private final DifferentialDrive driveBase;
  
  private final Motor frontLeft = new Motor(Constants.K_DRIVE_LEFT_FRONT_ID);
  private final Motor backLeft = new Motor(Constants.K_DRIVE_LEFT_BACK_ID);
  private final Motor frontRight = new Motor(Constants.K_DRIVE_RIGHT_FRONT_ID);
  private final Motor backRight = new Motor(Constants.K_DRIVE_RIGHT_BACK_ID);

  private final SpeedControllerGroup leftSide;
  private final SpeedControllerGroup rightSide;

  public Drive() {

    CommandScheduler.getInstance().registerSubsystem(this);

    leftSide = new SpeedControllerGroup(frontLeft.PWM(), backLeft.PWM());
    rightSide = new SpeedControllerGroup(frontRight.PWM(), backRight.PWM());

    driveBase = new DifferentialDrive(leftSide, rightSide);
  }

  public void arcadeDrive(final double velocity, final double rotation) {
    driveBase.arcadeDrive(velocity, rotation);
  }

  public void tankDrive(final double leftSpeed, final double rightSpeed) {
    driveBase.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
  }
}
