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
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.Motor;

public class DrivePID extends PIDSubsystem {

  private final Motor front;
  private final Motor back;
  private final CANEncoder encoder;
  private final SpeedControllerGroup group;
  private final SimpleMotorFeedforward feedForward;
  private final String name;
  private final boolean invert;
  private final double encoder_constant;

  public DrivePID(String name, double P, double D, double S, double V, double tolerance, int frontID, int backID,
      double encoderConstant, boolean invert) {
    super(new PIDController(P, 0, D));

    this.name = name;
    this.invert = invert;
    if (invert) {
      encoder_constant = -encoderConstant;
    } else {
      encoder_constant = encoderConstant;
    }
    front = new Motor(frontID, Motor.Type.Brushless);
    back = new Motor(backID, Motor.Type.Brushless);
    front.CAN().setIdleMode(IdleMode.kBrake);
    back.CAN().setIdleMode(IdleMode.kBrake);
    encoder = front.CAN().getEncoder();
    // encoder.setPositionConversionFactor(encoderConstant);
    // encoder.setVelocityConversionFactor(encoderConstant / 60.0);
    encoder.setPosition(0);

    group = new SpeedControllerGroup(front.CAN(), back.CAN());
    group.setInverted(invert);
    SmartDashboard.putNumber(name + "/P", P);

    feedForward = new SimpleMotorFeedforward(S, V);
    setSetpoint(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    getController().setP(SmartDashboard.getNumber(name + "/P", 0));
    SmartDashboard.putNumber(name + "/velocity", getMeasurement());
    SmartDashboard.putNumber(name + "/setPoint", setpoint);
    SmartDashboard.putNumber(name + "/error", getController().getVelocityError());
    SmartDashboard.putNumber(name + "/position", getMeasurement()*60);
    group.setVoltage(output + feedForward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return encoder.getVelocity() * (encoder_constant / 60);
  }

  public void setPoint(double setPoint) {
    setSetpoint(setPoint);
  }
}
