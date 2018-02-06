package org.usfirst.frc.team4283.robot.components.climber;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.util.pneumatic.LowerPneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LowerClimber extends CommandGroup {

	public LowerClimber(TwoValvePneumatic valveOne, TwoValvePneumatic valveTwo) {
		this.addParallel(new LowerPneumatic(valveOne));
		this.addParallel(new LowerPneumatic(valveTwo));
		this.addSequential(new WaitCommand("Lower Waiting Period", 3));
		this.addSequential(new PrintCommand("Done Lower Climbing!"));
		
		this.requires(Components.CLIMB);
	}

}
