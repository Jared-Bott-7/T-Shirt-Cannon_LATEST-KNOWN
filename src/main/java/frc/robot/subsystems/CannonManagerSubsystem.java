package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class CannonManagerSubsystem extends SubsystemBase{
    private CannonSubsystem[] m_shooters;
    public int currentShooterIndex = 0; //Public to allow for it to be used in auto aiming later. 
    private int m_numOfBarrels;

    public CannonManagerSubsystem(int numberOfShooters, int[] cannonPorts) {
        m_shooters = new CannonSubsystem[numberOfShooters];
        m_numOfBarrels = numberOfShooters;

        //Initialize each shooter subsystem by indexing through each one. 
        for (int i = 0; i < m_numOfBarrels; i++) {
            m_shooters[i] = new CannonSubsystem(cannonPorts[i]);
        }
    }

    @Override
    public void periodic() {
    }

    //This funtion helps with the "cycleAndShoot" function
    private int getNextBarrel(){
        int nextBarrel = currentShooterIndex + 1;
        if (nextBarrel >= m_numOfBarrels){nextBarrel = 0;}
        return nextBarrel;
    }

    public CannonSubsystem getCannonSubsystem(){
        return m_shooters[currentShooterIndex];
    }

    //This function finds the next loaded barrel and fires it, this includes wrapping to the 1st barrel if needed. 
    public void cycleAndShoot() {
        currentShooterIndex = getNextBarrel();
        m_shooters[currentShooterIndex].launchShirt(currentShooterIndex);
    }

}
