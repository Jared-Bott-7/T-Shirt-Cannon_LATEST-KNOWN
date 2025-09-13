package frc.robot.commands.Cannons;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CannonManagerSubsystem;
import edu.wpi.first.wpilibj.RobotController;


public class CannonShootCommand extends Command{
    private final CannonManagerSubsystem m_CannonManagerSubsystem;
    private final XboxController m_XboxController;
    private double shotClock;
    private int readyToShoot = 1;

    public CannonShootCommand(CannonManagerSubsystem shooterSystem, XboxController xboxController) {
        m_CannonManagerSubsystem = shooterSystem;
        m_XboxController = xboxController;
        addRequirements(m_CannonManagerSubsystem);
    }
    
    @Override
    public void execute() {
        if(m_XboxController.getRightBumperButtonPressed() && m_XboxController.getRightTriggerAxis() == readyToShoot){
        m_CannonManagerSubsystem.cycleAndShoot();
        shotClock = RobotController.getTime();
        readyToShoot = 3;
        }
        if(readyToShoot == 3){
            if((RobotController.getTime() - shotClock)/1000000 >= Constants.cannonConstants.waitTime){
            m_CannonManagerSubsystem.getCannonSubsystem().closeSolenoid();
            readyToShoot = 2;
            }
        }
        if(readyToShoot == 2){
            if((RobotController.getTime() - shotClock)/1000000 >= Constants.cannonConstants.waitTime2){
            readyToShoot = 1;
            }
        }
    }
}
