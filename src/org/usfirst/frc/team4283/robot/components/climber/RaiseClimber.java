package org.usfirst.frc.team4283.robot.components.climber;

import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.util.pneumatic.LowerPneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.RaisePneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RaiseClimber extends CommandGroup {
	
	public RaiseClimber(TwoValvePneumatic valveOne, TwoValvePneumatic valveTwo) {
		this.addParallel(new RaisePneumatic(valveOne));
		this.addParallel(new RaisePneumatic(valveTwo));
		this.addSequential(new WaitCommand("Rasing Waiting Period", 3));
		this.addSequential(new PrintCommand("Done Raising Climber!"));
		
		this.setSubsystem(Subsystems.CLIMBER);
	}
	
}
