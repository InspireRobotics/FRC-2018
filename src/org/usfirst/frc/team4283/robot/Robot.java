package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.auto.CrossAuto;
import org.usfirst.frc.team4283.robot.auto.PlaceAuto;
import org.usfirst.frc.team4283.robot.util.FMSManager;
import org.usfirst.frc.team4283.robot.util.SwitchPosition;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main robot class - this is created automatically. This object manages all of the sub
 * components, the scheduler, and the compressor. 
 * @author Noah
 */
public class Robot extends IterativeRobot {
	
	public static SwitchPosition closePos;
	
	/**
	 * The compressor for the robot. It is started in the 
	 * {@link #robotInit()} method and is never stopped. 
	 * It will automatically stop when the pressure gets to high.
	 */
	private Compressor compressor;
	
	/*
	 * Manages all of the components (drivetrain, climber, intake, etc.) on the robot. 
	 */
	private Components subs = new Components();
	
	@Override
	public void robotInit() {
		SmartDashboard.putBoolean("Place?", true);
		SmartDashboard.putBoolean("Drop Left", false);
		SmartDashboard.putBoolean("Drop Right", false);

		
		//Init and Start the compressor
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		compressor.setName("Compressor");
		compressor.start();
		
		//Initialize all of the subsystems
		subs.robotInit();
		
		//Create all of the control handlers
		Controls.init();
	}
	
	@Override
	public void disabledInit() {
		//Disable all of the subsystems
		subs.disable();
	}
	
	@Override
	public void autonomousInit() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Get data from FMS
		closePos = FMSManager.getCloseSwitch();
		
		System.out.println(closePos);
		System.out.println("FMS Data: " + DriverStation.getInstance().getGameSpecificMessage());
		
		//Remove any commands that may be lingering... 
		Scheduler.getInstance().removeAll();
		
		//Initialize all subsystems
		subs.autoInit();
	
		if(SmartDashboard.getBoolean("Place?", false)){
			System.out.println("Place");
			Scheduler.getInstance().add(new PlaceAuto());
		}else{
			System.out.println("Not Place");
			Scheduler.getInstance().add(new CrossAuto());
		}
	}

	@Override
	public void autonomousPeriodic() {
		//Update all subsystems
		subs.autoPeriodic();
		
		//Run all scheduled commands
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		compressor.start();
		//Remove any commands that may be lingering...
		Scheduler.getInstance().removeAll();
				
		//Update all subsystems
		subs.teleOpInit();
	}
	
	@Override
	public void teleopPeriodic() {
		//Update all subsystems
		subs.teleOpPeriodic();
				
		//Run all scheduled commands
		Scheduler.getInstance().run();
	}
}
