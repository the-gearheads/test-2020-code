/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drive extends SubsystemBase {

  private final DifferentialDrive driveBase;

  private final CANSparkMax frontLeft;
  private final CANSparkMax backLeft;
  private final CANSparkMax frontRight;
  private final CANSparkMax backRight;

  private final SpeedControllerGroup leftSide;
  private final SpeedControllerGroup rightSide;

  public Drive() {

    CommandScheduler.getInstance().registerSubsystem(this);

    frontLeft = new CANSparkMax(Constants.K_DRIVE_LEFT_FRONT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.K_DRIVE_RIGHT_FRONT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.K_DRIVE_LEFT_BACK_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.K_DRIVE_RIGHT_BACK_ID, MotorType.kBrushless);

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
