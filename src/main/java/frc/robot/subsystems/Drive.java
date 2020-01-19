/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Motor;

public class Drive extends SubsystemBase {

  private final DifferentialDrive driveBase;

  private final Motor leftFront = new Motor(Constants.K_DRIVE_LEFT_FRONT_ID, Motor.Type.Brushless); // 10
  private final Motor leftBack = new Motor(Constants.K_DRIVE_LEFT_BACK_ID, Motor.Type.Brushless); // 1 (on howie)
  private final Motor rightFront = new Motor(Constants.K_DRIVE_RIGHT_FRONT_ID, Motor.Type.Brushless); // 3
  private final Motor rightBack = new Motor(Constants.K_DRIVE_RIGHT_BACK_ID, Motor.Type.Brushless); // 12

  private final SpeedControllerGroup leftSide;
  private final SpeedControllerGroup rightSide;

  PIDController leftPID;
  PIDController rightPID;

  CANEncoder leftEncoder;
  CANEncoder rightEncoder;

  public Drive() {

    CommandScheduler.getInstance().registerSubsystem(this);

    rightFront.CAN().setIdleMode(IdleMode.kBrake);
    leftFront.CAN().setIdleMode(IdleMode.kBrake);
    leftSide = new SpeedControllerGroup(leftFront.CAN(), leftBack.CAN());
    rightSide = new SpeedControllerGroup(rightFront.CAN(), rightBack.CAN());

    leftPID = new PIDController(5.62, 0, 2610);
    rightPID = new PIDController(5.63, 0, 2630);

    leftEncoder = leftFront.CAN().getEncoder();
    rightEncoder = rightFront.CAN().getEncoder();

    driveBase = new DifferentialDrive(leftSide, rightSide);

    // System.out.println(backRight.CAN().getPIDController().getP());
  }

  public void arcadeDrive(final double velocity, final double rotation) {
    driveBase.arcadeDrive(velocity, rotation);
  }

  public void tankDrive(final double leftSpeed, final double rightSpeed) {
    // driveBase.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    leftSide.set(leftPID.calculate(leftEncoder.getPosition()));
    rightSide.set(rightPID.calculate(rightEncoder.getPosition()));
  }
}
