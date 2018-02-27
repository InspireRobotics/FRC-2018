package org.usfirst.frc.team4283.robot.util;

import edu.wpi.first.wpilibj.DriverStation;

public class FMSManager {

	public static SwitchPosition getCloseSwitch() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(gameData == null){
			return null;
		}
		
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				return SwitchPosition.LEFT;
			} else {
				return SwitchPosition.RIGHT;
			}
		}
		
		return null;
	}

}
