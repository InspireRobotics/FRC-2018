package org.usfirst.frc.team4283.robot.util.pneumatic;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TwoValvePneumatic{
	
	protected Solenoid a;
	protected Solenoid b;
	private boolean raised = false;
	
	public TwoValvePneumatic(int port, int buttonNum, String name) {
		a = new Solenoid(port);
		a.setName(name);
		a.set(false);
		b = new Solenoid((port + 4));
		b.set(false);
	}
	
	public boolean isRaised() {
		return raised;
	}
	
	public void setRaised(boolean raised) {
		this.raised = raised;
	}
	
	public void raise(){
		Scheduler.getInstance().add(new RaisePneumatic(this));
		
		raised = true;
	}
	
	public void lower(){
		raised = false;
		Scheduler.getInstance().add(new LowerPneumatic(this));
	}
	
}