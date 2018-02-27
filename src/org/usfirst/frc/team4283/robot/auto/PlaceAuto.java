package org.usfirst.frc.team4283.robot.auto;

import org.usfirst.frc.team4283.robot.Robot;
import org.usfirst.frc.team4283.robot.components.drivetrain.AutoDrive;
import org.usfirst.frc.team4283.robot.components.drivetrain.AutoTurn;
import org.usfirst.frc.team4283.robot.components.intake.AutoOutake;
import org.usfirst.frc.team4283.robot.util.SwitchPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class PlaceAuto extends CommandGroup{
	
	public PlaceAuto() {
		System.out.println("Place: " + Robot.closePos);
		this.setName("Place auto!");
		this.addSequential(new AutoDrive(.75, .65));
		if(Robot.closePos == SwitchPosition.LEFT){
			this.addSequential(new AutoTurn(-30));
			this.addSequential(new AutoDrive(3, .65));
			this.addSequential(new AutoTurn(30));
			this.addSequential(new AutoDrive(.3, .7));
		}else{
			this.addSequential(new AutoTurn(30));
			this.addSequential(new AutoDrive(3.3, .65));
			this.addSequential(new AutoTurn(-20));
			this.addSequential(new AutoDrive(0.6, .7));
		}
		
		this.addSequential(new AutoOutake(5));
		this.addSequential(new PrintCommand("Done Placing..."));
	}
	
}
