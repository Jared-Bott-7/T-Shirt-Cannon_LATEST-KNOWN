package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsytem extends SubsystemBase{
    private Victor m_traverseMotor;
    private Victor m_tiltMotor;

    public TurretSubsytem(){
        //Set up Motors
        m_traverseMotor = new Victor(Constants.turretConstants.traverseMotorPort);
        m_tiltMotor = new Victor(Constants.turretConstants.tiltMotorPort);
    }

    public void setMotorSpeeds(double x, double y){
        m_traverseMotor.set(-x);
        m_traverseMotor.feed();
        m_tiltMotor.set(y);
        m_tiltMotor.feed();
    }


    @Override
    public void periodic() {
    }
}
