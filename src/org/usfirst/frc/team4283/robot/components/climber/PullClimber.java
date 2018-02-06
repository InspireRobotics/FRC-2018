package org.usfirst.frc.team4283.robot.components.climber;

import org.usfirst.frc.team4283.robot.Components;
import org.usfirst.frc.team4283.robot.util.pneumatic.LowerPneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.RaisePneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PullClimber extends CommandGroup {

	public PullClimber(TwoValvePneumatic valve) {
		this.addParallel(new LowerPneumatic(valve));
		this.addSequential(new WaitCommand("Lower Waiting Period", 3));
		this.addSequential(new PrintCommand("Done Lowering Climber!"));
		
		this.requires(Components.CLIMB);
	}

}
