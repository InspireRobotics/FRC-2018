package org.usfirst.frc.team4283.robot.pneumatic;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TwoValvePneumatic{
	
	private Solenoid a;
	private Solenoid b;
	private JoystickButton buttonOpen;
	private JoystickButton buttonClose;
	private boolean open = true;
	
	public TwoValvePneumatic(int port, int buttonNum, String name) {
		buttonOpen = new JoystickButton(new Joystick(0), (buttonNum * 2) + 1);
		buttonOpen.whenPressed(new ValveSwitchOpen(this));
		buttonClose = new JoystickButton(new Joystick(0), (buttonNum * 2) + 2);
		buttonClose.whenPressed(new ValveSwitchClose(this));
		close();
		a = new Solenoid(port * 2);
		a.setName(name);
		a.set(false);
		b = new Solenoid((port * 2) + 1);
		b.set(false);
	}
	
	public void updateDashboard(){
		SmartDashboard.putBoolean("  " + a.getName(), open);
	}
	
	private class ValveSwitchOpen extends Command{
		
		private TwoValvePneumatic pneumatic;
		private boolean opened;
		private boolean finished = false;
		
		public ValveSwitchOpen(TwoValvePneumatic p) {
			this.pneumatic = p;
		}
		
		@Override
		protected void initialize() {
	
		}
		
		@Override
		protected void execute() {
			if(!opened && !finished){
				System.out.println("Opening!");
				pneumatic.a.set(true);
				pneumatic.b.set(false);
				opened = true;
				pneumatic.open = true;
			}else if(!finished){
				System.out.println("Finished Opening!");
				finished = true;
				pneumatic.a.set(false);
			}
		}
		
		@Override
		protected void end() {
			finished = false;
			opened = false;
		}
		
		@Override
		protected boolean isFinished() {
			return finished;
		}
		
	}
	
	private class ValveSwitchClose extends Command{
		
		private TwoValvePneumatic pneumatic;
		private boolean closed;
		private boolean finished = false;
		
		public ValveSwitchClose(TwoValvePneumatic p) {
			this.pneumatic = p;
		}
		
		@Override
		protected void execute() {
			if(!closed && !finished){
				System.out.println("Finished Closing!");
				pneumatic.a.set(false);
				pneumatic.b.set(true);
				closed = true;
				pneumatic.open = false;
			}else if(!finished){
				System.out.println("Finished Closing!");
				finished = true;
				pneumatic.b.set(false);
			}
			
		}
		
		@Override
		protected void end() {
			finished = false;
			closed = false;
		}
		
		@Override
		protected boolean isFinished() {
			return finished;
		}
		
	}

	public void close() {
		Scheduler.getInstance().add(new ValveSwitchClose(this));
	}
	
}