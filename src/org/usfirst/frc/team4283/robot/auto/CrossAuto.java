package org.usfirst.frc.team4283.robot.auto;

import org.usfirst.frc.team4283.robot.Robot;
import org.usfirst.frc.team4283.robot.components.drivetrain.AutoDrive;
import org.usfirst.frc.team4283.robot.components.drivetrain.AutoTurn;
import org.usfirst.frc.team4283.robot.components.intake.AutoOutake;
import org.usfirst.frc.team4283.robot.util.SwitchPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrossAuto extends CommandGroup {
	
	public CrossAuto() {
		this.setName("Cross auto!");
		this.addSequential(new AutoDrive(3, .7));
		if(SmartDashboard.getBoolean("Drop Left", false) && Robot.closePos == SwitchPosition.LEFT){
			System.out.println("Dropping Left!");
			this.addSequential(new AutoTurn(80));
			this.addSequential(new AutoOutake(5));
		}else if(SmartDashboard.getBoolean("Drop Right", false) && Robot.closePos == SwitchPosition.RIGHT){
			System.out.println("Dropping Right!");
			this.addSequential(new AutoTurn(-90));
			this.addSequential(new AutoOutake(5));
		}
		this.addSequential(new PrintCommand("Finished Auto!"));
	}
	
	
}
