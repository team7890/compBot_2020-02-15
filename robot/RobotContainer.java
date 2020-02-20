/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* FRC Team 7890 SeQuEnCe                                                     */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Pneumatics;

import frc.robot.commands.DriveRobot;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ShiftDriveSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import edu.wpi.first.wpilibj2.command.RunCommand;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  XboxController objXboxDriverStick = new XboxController(0);    // TO Do: Add this to Constants!!!

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain objDriveTrain = new DriveTrain();
  private final Pneumatics objPneumatics = new Pneumatics();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  //private final DriveWestCoast oDriveWestCoast = new DriveWestCoast(oDriverStick.getRawAxis(1) * 0.2, oDriverStick.getRawAxis(4) * 0.5, oDrive);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    objDriveTrain.setDefaultCommand(new DriveRobot(() -> objXboxDriverStick.getRawAxis(1) * 0.5, () -> objXboxDriverStick.getRawAxis(4) * 0.5, objDriveTrain));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton bumperL = new JoystickButton(objXboxDriverStick, 5); // Left bumper shifts into high gear!!!
    final JoystickButton bumperR = new JoystickButton(objXboxDriverStick, 6); // Right bumper shifts into low gear!!!
    // objXboxDriverStick.getRawButton(5); // Left bumper shifts into high gear!!!
    // objXboxDriverStick.getRawButton(6); // Right bumper shifts into low gear!!!
    bumperL.whenPressed(new ShiftDriveSpeed(objPneumatics, true));    // shift into high gear on rising edge
    bumperR.whenPressed(new ShiftDriveSpeed(objPneumatics, false));   // shift into low gear on rising edge
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
