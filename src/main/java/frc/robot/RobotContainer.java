// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.TurretControlMotorsCommand;
import frc.robot.commands.Cannons.CannonShootCommand;
import frc.robot.subsystems.CannonManagerSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TurretSubsytem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class RobotContainer {
  public final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem(Constants.drivetrainConstants.motorPorts, Constants.drivetrainConstants.motorInverts);

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final XboxController m_XboxController = new XboxController(Constants.OperatorConstants.kDriverControllerPort);

  //Declare and set Cannon Manager Subsystem and command. 
  //The CannonManager creates the the barrel objects. 
  private final CannonManagerSubsystem m_CannonManagerSubsystem = new CannonManagerSubsystem(Constants.cannonConstants.numberOfShooters, Constants.cannonConstants.cannonPorts);
  private final Command m_cannonShootCommand = new CannonShootCommand(m_CannonManagerSubsystem, m_XboxController);
  //Declare and set Turret Subsytem and Commands. 
  private final TurretSubsytem m_turretSubsystem = new TurretSubsytem();
  private final TurretControlMotorsCommand m_turretControlMotors = new TurretControlMotorsCommand(m_turretSubsystem, m_driverController);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
    m_CannonManagerSubsystem.setDefaultCommand(m_cannonShootCommand);
    m_drivetrain.setDefaultCommand(new DrivetrainCommand(m_drivetrain, m_driverController)); //TODO: Figure out which method is better, this method or the below method
    m_turretSubsystem.setDefaultCommand(m_turretControlMotors); //When the turret is not being used, use the regular control method. 

  }


  private void configureBindings() {  
  }

  public Command getAutonomousCommand() {return null;}
}
