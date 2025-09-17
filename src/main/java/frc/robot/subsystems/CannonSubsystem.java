package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CannonSubsystem extends SubsystemBase{
    private DigitalOutput m_relay;

    public CannonSubsystem(int outputPin) {
        m_relay = new DigitalOutput(outputPin);
    }
    
    @Override
    public void periodic(){
    }

    public void closeSolenoid(){
        m_relay.set(false);
        System.out.println("close");
    }

    public void launchShirt(int outputPin){
        m_relay.set(true);
        System.out.println("open");
    }

}
