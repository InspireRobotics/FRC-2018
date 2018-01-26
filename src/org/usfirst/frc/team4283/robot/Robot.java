package org.usfirst.frc.team4283.robot;

import org.usfirst.frc.team4283.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team4283.robot.intake.Intake;
import org.usfirst.frc.team4283.robot.led.LEDController;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.inspirerobotics.leddar.Leddar;

public class Robot extends IterativeRobot {
	
	/*
	 * Subsystems
	 */
	private final boolean intakeEnabled = false;
	private final boolean blinkyEnabled = false;
	private final boolean leddarEnabled = false;
	
	/*
	 * Auto Choosing 
	 */
	private static final String middleAuto = "Middle";
	private static final String leftAuto = "Left";
	private static final String rightAuto = "Right";
	private String autoSelected;
	private SendableChooser<String> autoChooser = new SendableChooser<>();
	
	/*
	 * Subsystems
	 */
	private Intake intake;
	private Drivetrain drive;
	
	/*
	 * Sensors
	 */
	private Leddar leddar;
	private long lastLeddarUpdate;
	
	@Override
	public void robotInit() {
		autoChooser.addDefault("Default Auto", middleAuto);
		autoChooser.addObject("Left Auto", leftAuto);
		autoChooser.addObject("Right Auto", rightAuto);
		SmartDashboard.putData("Auto choices", autoChooser);
		drive = new Drivetrain();
		
		if(intakeEnabled)
			intake = new Intake();
		if(blinkyEnabled){
			LEDController.init();
			LEDController.setColor(LEDController.Colors.WHITE);
		}
		if(leddarEnabled) {
			leddar = new Leddar(Port.kUSB, 115200);
		}
			
	}
	
	@Override
	public void disabledPeriodic() {
		if(DriverStation.getInstance().isDSAttached()){
			LEDController.setColor(LEDController.Colors.GREEN);
		}else{
			LEDController.setColor(LEDController.Colors.RED);
		}
	}
	
	@Override
	public void autonomousInit() {
		autoSelected = autoChooser.getSelected();
		drive.autoInit();
		System.out.println("Auto selected: " + autoSelected);
	}

	@Override
	public void autonomousPeriodic() {
		drive.autoUpdate(blinkyEnabled);
	}

	@Override
	public void teleopInit() {
		drive.teleopInit();
	}
	
	@Override
	public void teleopPeriodic() {
		drive.updateTeleOp();
		
		if(intakeEnabled)
			intake.updateTeleOp();
		if(blinkyEnabled){
			LEDController.getInstance().update();
			
			if(DriverStation.getInstance().getMatchTime() < 30){
				LEDController.setColor(LEDController.Colors.WHITE);
			}else{
				LEDController.setColor(LEDController.Colors.ORANGE);
			}
		}
		if(leddarEnabled) {
			System.out.println("Leddar Distance: " + leddar.getCurrentDistance());
			if(System.currentTimeMillis() > lastLeddarUpdate + 1000) {
				leddar.sendRequest();
				lastLeddarUpdate = System.currentTimeMillis();
			}
		}
			
	}

	@Override
	public void testPeriodic() {
	}
}
