package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PWMTalonSRX;

public class Motor {

    private PWMTalonSRX pwm;
    private CANSparkMax can;

    public enum Type {
        Brushed(0), Brushless(1);

        private int id;
        private Type(int val) {
            id = val;
        }
    }

    public Motor(int channel) {
        pwm = new PWMTalonSRX(channel);
    }

    public Motor(int channel, Type type) {
        can = new CANSparkMax(channel, MotorType.fromId(type.id));
    }

    public PWMTalonSRX PWM() { return pwm; }

    public CANSparkMax CAN() { return can; }
}
