/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import com.revrobotics.CANEncoder;
import frc.robot.Motor;
import frc.robot.Constants;

public class RightSidePID extends PIDSubsystem {

  private final Motor front;
  private final Motor back;
  private final CANEncoder encoder;
  private final SpeedControllerGroup group;
  private final SimpleMotorFeedforward feedForward;

  public RightSidePID() {
    super(
        new PIDController(Constants.RDRIVE_P, 0, Constants.RDRIVE_D));

    front = new Motor(Constants.K_DRIVE_RIGHT_FRONT_ID, Motor.Type.Brushless);
    back = new Motor(Constants.K_DRIVE_RIGHT_BACK_ID, Motor.Type.Brushless);
    encoder = front.CAN().getEncoder();
    encoder.setPositionConversionFactor((1 / 8.45) * 0.0983 * Math.PI);
    encoder.setVelocityConversionFactor(((1 / 8.45) * 0.0983 * Math.PI) / 60.);
    encoder.setPosition(0);
    
    getController().setTolerance(0.05);
    group = new SpeedControllerGroup(front.CAN(), back.CAN());
    group.setInverted(true);

    feedForward = new SimpleMotorFeedforward(Constants.RDRIVE_S, Constants.RDRIVE_V);
    setSetpoint(0.01);
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
