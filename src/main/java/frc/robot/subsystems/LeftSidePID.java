/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Motor;

public class LeftSidePID extends PIDSubsystem {

  private final Motor front;
  private final Motor back;
  private final CANEncoder encoder;
  private final SpeedControllerGroup group;
  private final SimpleMotorFeedforward feedForward;

  public LeftSidePID() {
    super(new PIDController(Constants.LDRIVE_P, 0, Constants.LDRIVE_D));

    front = new Motor(Constants.K_DRIVE_LEFT_FRONT_ID, Motor.Type.Brushless);
    back = new Motor(Constants.K_DRIVE_LEFT_BACK_ID, Motor.Type.Brushless);
    encoder = front.CAN().getEncoder();
    encoder.setPositionConversionFactor((1 / 8.45) * 0.0983 * Math.PI);
    encoder.setVelocityConversionFactor(((1 / 8.45) * 0.0983 * Math.PI) / 60.);
    encoder.setPosition(0.01);

    getController().setTolerance(0.05);
    group = new SpeedControllerGroup(front.CAN(), back.CAN());

    feedForward = new SimpleMotorFeedforward(Constants.LDRIVE_S, Constants.LDRIVE_V);
    setSetpoint(0.01);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    SmartDashboard.putNumber("voltage", output);
    group.setVoltage(output + feedForward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    SmartDashboard.putNumber("velocity", encoder.getVelocity());
    return encoder.getVelocity();
  }

  public void setPoint(double setPoint) {
    setSetpoint(setPoint);
  }
}