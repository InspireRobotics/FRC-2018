package org.usfirst.frc.team4283.robot.pneumatic;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@Deprecated
public class SinglePneumatic{
	
	private Solenoid valve;
	private JoystickButton buttonOpen;
	private JoystickButton buttonClose;
	private boolean open = true;
	
	public SinglePneumatic(int port, int buttonNum, String name) {
		buttonOpen = new JoystickButton(new Joystick(0), (buttonNum * 2) + 1);
		buttonOpen.whenPressed(new ValveSwitchOpen(this));
		buttonClose = new JoystickButton(new Joystick(0), (buttonNum * 2) + 2);
		buttonClose.whenPressed(new ValveSwitchClose(this));
		Scheduler.getInstance().add(new ValveSwitchClose(this));
		valve = new Solenoid(port);
		valve.setName(name);
		valve.set(false);
	}
	
	public void updateDashboard(){
		SmartDashboard.putBoolean("  " + valve.getName(), open);
	}
	
	private class ValveSwitchOpen extends Command{
		
		private SinglePneumatic pneumatic;
		private boolean finished = false;
		
		public ValveSwitchOpen(SinglePneumatic p) {
			this.pneumatic = p;
		}
		
		@Override
		protected void execute() {
			finished = true;
			pneumatic.valve.set(true);
		}
		
		@Override
		protected void end() {
			finished = false;
		}
		
		@Override
		protected boolean isFinished() {
			return finished;
		}
		
	}
	
	private class ValveSwitchClose extends Command{
		
		private SinglePneumatic pneumatic;
		private boolean finished = false;
		
		public ValveSwitchClose(SinglePneumatic p) {
			this.pneumatic = p;
		}
		
		@Override
		protected void execute() {
			finished = true;
			pneumatic.valve.set(false);
		}
		
		@Override
		protected void end() {
			finished = false;
		}
		
		@Override
		protected boolean isFinished() {
			return finished;
		}
		
	}
	
}