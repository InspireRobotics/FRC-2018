package org.usfirst.frc.team4283.robot.util.pneumatic;

import edu.wpi.first.wpilibj.command.Command;

public class RaisePneumatic extends Command{
	
	private TwoValvePneumatic pneumatic;
	private boolean closed;
	private boolean finished = false;
	private int count = 0;
	
	public RaisePneumatic(TwoValvePneumatic p) {
		this.pneumatic = p;
		this.setInterruptible(false);
	}
	
	@Override
	protected void execute() {
		if(!closed && !finished){
			pneumatic.a.set(false);
			pneumatic.b.set(true);
			closed = true;
		}else if(!finished && count > 1){
			finished = true;
			pneumatic.b.set(false);
		}
		count++;
	}
	
	@Override
	protected void end() {
		finished = false;
		closed = false;
		count = 0;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}