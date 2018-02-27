package org.usfirst.frc.team4283.robot.util.pneumatic;

import edu.wpi.first.wpilibj.command.Command;

public class LowerPneumatic extends Command{
	
	private TwoValvePneumatic pneumatic;
	private boolean opened;
	private boolean finished = false;
	private int count = 0;
	
	public LowerPneumatic(TwoValvePneumatic p) {
		this.pneumatic = p;
		this.setInterruptible(false);

	}
	
	@Override
	protected void initialize() {

	}
	
	@Override
	protected void execute() {
		pneumatic.setRaised(false);
		
		if(!opened && !finished){
			pneumatic.a.set(true);
			pneumatic.b.set(false);
			opened = true;
		}else if(!finished && count > 1){
			finished = true;
			pneumatic.a.set(false);
		}
		count++;
	}
	
	@Override
	protected void end() {
		finished = false;
		count = 0;
		opened = false;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
