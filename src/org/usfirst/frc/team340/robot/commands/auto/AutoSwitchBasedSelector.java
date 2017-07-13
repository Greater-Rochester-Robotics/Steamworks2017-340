package org.usfirst.frc.team340.robot.commands.auto;

import org.usfirst.frc.team340.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSwitchBasedSelector {

	DigitalInput autoSwitchAllianceColor;
	DigitalInput autoSwitchAirshipPos;
	DigitalInput autoSwitch2;
	DigitalInput autoSwitch3;
	
	public AutoSwitchBasedSelector(){
	    autoSwitchAllianceColor = new DigitalInput(RobotMap.AUTO_SWITCH_ALLIANCE_COLOR);
	    autoSwitchAirshipPos = new DigitalInput(RobotMap.AUTO_SWITCH_AIRSHIP_POS);
	    autoSwitch2 = new DigitalInput(RobotMap.AUTO_SWITCH_TWO);
	    autoSwitch3 = new DigitalInput(RobotMap.AUTO_SWITCH_THREE);
	}
	public Command getSelected(){
		this.pushToSmartDashboard();
		
		if(autoSwitchAllianceColor.get()){
			//alliance color blue
			if(autoSwitchAirshipPos.get()){
				//airship position side
				if(!autoSwitch2.get() && autoSwitch3.get()){
					return new AutoBlueSidePegThenCrossField();
				}else{
					return new AutoBlueSidePegThenNothing();
				}
			}else{
				//airship position center
				if(autoSwitch2.get() && autoSwitch3.get()){
					return new AutoBlueCenterPegThenBoilerGear();
				}else if(!autoSwitch2.get() && autoSwitch3.get()){
					return new AutoBlueCenterPegThenLoadingGear();
				}else if(autoSwitch2.get() && !autoSwitch3.get()){
					return new AutoBlueCenterPegThenCrossField();
				}else{
					return new AutoCenterPegThenNothing();
				}
			}
		}else{
			//alliance color red
			if(autoSwitchAirshipPos.get()){
				//airship position side
				if(!autoSwitch2.get() && autoSwitch3.get()){
					return new AutoRedSidePegThenCrossField();
				}else{
					return new AutoRedSidePegThenNothing();
				}
			}else{
				//airship position center
				if(!autoSwitch2.get() && autoSwitch3.get()){
					return new AutoRedSidePegThenCrossField();
				}else if(autoSwitch2.get() && !autoSwitch3.get()){
					return new AutoRedSidePegThenNothing();//AutoRedCenterPegThenBoilerGear();
				}else{
					return new AutoCenterPegThenNothing();
				}
			}
		}
	}
	
	/**
	 * pushes info to the dashboard
	 */
	public void pushToSmartDashboard(){
		SmartDashboard.putBoolean("autoSwitch0", autoSwitchAllianceColor.get());
		SmartDashboard.putBoolean("autoSwitch1", autoSwitchAirshipPos.get());
		SmartDashboard.putBoolean("autoSwitch2", autoSwitch2.get());
		SmartDashboard.putBoolean("autoSwitch3", autoSwitch3.get());		
	}
}
