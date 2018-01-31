package org.usfirst.frc.team4283.robot.components.arm;

import org.usfirst.frc.team4283.robot.Subsystems;
import org.usfirst.frc.team4283.robot.util.pneumatic.RaisePneumatic;
import org.usfirst.frc.team4283.robot.util.pneumatic.TwoValvePneumatic;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RaiseArm extends CommandGroup {

	public RaiseArm(TwoValvePneumatic valveOne, TwoValvePneumatic valveTwo) {
		this.addParallel(new RaisePneumatic(valveOne));
		this.addParallel(new RaisePneumatic(valveTwo));
		this.addSequential(new WaitCommand("Raise Waiting Period", 3));
		this.addSequential(new PrintCommand("Done Raising Arm!"));
		
		this.setSubsystem(Subsystems.ARM);
	}

}
