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
import frc.robot.Motor;

public class DrivePID extends PIDSubsystem {

  private final Motor front;
  private final Motor back;
  private final CANEncoder encoder;
  private final SpeedControllerGroup group;
  private final SimpleMotorFeedforward feedForward;

  public DrivePID(double P, double D, double S, double V, double tolerance, int frontID, int backID,
      double encoderConstant) {
    super(new PIDController(P, 0, D));

    front = new Motor(frontID, Motor.Type.Brushless);
    back = new Motor(backID, Motor.Type.Brushless);
    encoder = front.CAN().getEncoder();
    encoder.setPositionConversionFactor(encoderConstant);
    encoder.setVelocityConversionFactor(encoderConstant / 60.0);
    encoder.setPosition(0);

    getController().setTolerance(tolerance);
    group = new SpeedControllerGroup(front.CAN(), back.CAN());

    feedForward = new SimpleMotorFeedforward(S, V);
    setSetpoint(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    group.setVoltage(output + feedForward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return encoder.getVelocity();
  }

  public void setPoint(double setPoint) {
    setSetpoint(setPoint);
  }
}
