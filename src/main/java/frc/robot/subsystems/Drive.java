/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

  private final DifferentialDrive driveBase;

  private final PWMTalonSRX frontLeft;
  private final PWMTalonSRX backLeft;
  private final PWMTalonSRX frontRight;
  private final PWMTalonSRX backRight;

  private final SpeedControllerGroup leftSide;
  private final SpeedControllerGroup rightSide;

  public Drive() {

    CommandScheduler.getInstance().registerSubsystem(this);

    frontLeft = new PWMTalonSRX(Constants.K_DRIVE_LEFT_FRONT_ID);
    frontRight = new PWMTalonSRX(Constants.K_DRIVE_RIGHT_FRONT_ID);
    backLeft = new PWMTalonSRX(Constants.K_DRIVE_LEFT_BACK_ID);
    backRight = new PWMTalonSRX(Constants.K_DRIVE_RIGHT_BACK_ID);

    leftSide = new SpeedControllerGroup(frontLeft, backLeft);
    rightSide = new SpeedControllerGroup(frontRight, backRight);

    driveBase = new DifferentialDrive(leftSide, rightSide);
  }

  public void arcadeDrive(double velocity, double rotation) {
    driveBase.arcadeDrive(velocity, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    driveBase.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
  }
}
