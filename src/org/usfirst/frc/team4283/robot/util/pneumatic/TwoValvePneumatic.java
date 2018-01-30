package org.usfirst.frc.team4283.robot.util.pneumatic;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Scheduler;

public class TwoValvePneumatic{
	
	protected Solenoid a;
	protected Solenoid b;
	
	public TwoValvePneumatic(int port, int buttonNum, String name) {
		a = new Solenoid(port * 2);
		a.setName(name);
		a.set(false);
		b = new Solenoid((port * 2) + 1);
		b.set(false);
	}
	
	public void raise(){
		Scheduler.getInstance().add(new RaisePneumatic(this));
	}
	
	public void lower(){
		Scheduler.getInstance().add(new LowerPneumatic(this));
	}
	
}