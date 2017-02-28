#Gear Sensor Training

**Operator's perspective:**
 
1. Turn on robot.
2. Remove any gear that is already in the robot claw.
3. Wait for the LED strips to display blue to show that the code is ready.
4. Press the gear training button.
5. Wait for the LED strips to display purple to indicate that the robot is ready for a gear to be added.
6. Manually insert the gear into the claw.
7. Wait for the LED strips to display orange to indicate that the robot is ready for a gear to be removed.
8. Manually remove the gear.
9. Wait for the LED strips to display green to indicate successful completion of gear training.
10. Put gear back on for auto.

---

**Robot perspective:**
  
1. Wait for the operator to turn on the robot.  
	a. Code initializes.  (Add more?)
2. Display blue on the LED strips to tell the operator that the code is ready to be used.  
3. Wait for the gear training button to be pressed.  
	a. If sensor A indicates that a gear is present, set aStuckOn to True & flash the port side yellow. Else set aStuckOn to False.  
	b. If sensor B indicates that a gear is present, set bStuckOn to True & flash the starbound side yellow. Else set bStuckOn to False. 
4. Display purple on the LED strips to indicate that the robot is ready for a gear to be added.
5. Wait for at least one sensor to transition to gear present state.  
	a. Wait for (aStuckOn is False & sensor A indicates that a gear is present), or (bStuckOn is False & sensor B indicates that a gear is present).  
	b. Wait one second to allow the gear to physically settle on all of the sensors.  
	c. If sensor A indicates that a gear is absent, set aStuckOff to True & flash the port side yellow. Else set aStuckOff to False.  
	d. If sensor B indicates that a gear is absent, set bStuckOff to True & flash the starbound side yellow. Else set bStuckOff to False.
6. Display orange on the LED strips to indicate that the robot is ready for the gear to be removed.
7. Wait for at least one sensor to transition to gear absent state.  
	a. Wait for (aStuckOff is False & sensor A indicates that a gear is absent), or (bStuckOff is False & sensor B indicates that a gear is absent).  
	b. Wait one second to allow the gear to physically be removed from all of the sensors.  
	c. If sensor A indicates that a gear is present, set aStuckOn to True & flash the port side yellow. Else set aStuckOn to False.  
	d. If sensor B indicates that a gear is present, set bStuckOn to True & flash the starbound side yellow. Else set bStuckOn to False.
8. Display green on the LED strips to indicate that gear training was successful or red if gear training wasn't successful.   
	a. If aStuckOn & aStuckOff are False, set sensorAFunctional true.  
	b. If bStuckOn & bStuckOff are False, set sensorBFunctional true.  
	c. If sensorAFunctional is false and sensorBFunctional is false, set gearSensingFunctional false.  
	d. If sensorAFunctional is true or sensorBFunctional is true, set gearSensingFunctional true.  
	e. If gearSensingFunctional is true, then gear training is successful. Else, gear training is unsuccessful.
	
---

#Glossary:

**aStuckOn** - Boolean variable made for telling the rest of the robot that the sensor A is constantly saying that a gear is present.  
**bStuckOn** - Boolean variable made for telling the rest of the robot that the sensor B is constantly saying that a gear is present.  
**aStuckOff** - Boolean variable made for telling the rest of the robot that the sensor A is constantly saying that a gear is absent.  
**bStuckOff** - Boolean variable made for telling the rest of the robot that the sensor B is constantly saying that a gear is absent.  
**sensorAFunctional** - Boolean variable made for telling the rest of the robot that sensor A is functional.  
**sensorBFunctional** - Boolean variable made for telling the rest of the robot that sensor B is functional.  
**gearSensingFunctional** - Boolean variable made for telling the rest of the robot that gear sensing can be used.  
**sensor A** - This is a switch, when sensor A says that it is on that means that a gear is present in the claw.  
**sensor B** - This is a switch, when sensor B says that it is on that means that a gear is present in the claw.  
**Flash** - This refers to turning on the LED strips to a certain color due to a condition being met, this display is for one second.  
**Port** - When facing the robot from the claw, the port side is the right side.  
**Starboard** - When facing the robot from the claw, the starboard side is the left side.  