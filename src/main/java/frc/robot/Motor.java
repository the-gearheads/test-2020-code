package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Motor {

    private PWMTalonSRX pwm;
    private CANSparkMax can;

    public Motor(int channel) {
        pwm = new PWMTalonSRX(channel);
    }

    public Motor(int channel, MotorType type) {
        can = new CANSparkMax(channel, type);
    }

    public PWMTalonSRX PWM() { return pwm; }

    public CANSparkMax CAN() { return can; }
}
