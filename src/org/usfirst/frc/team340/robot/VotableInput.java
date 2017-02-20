package org.usfirst.frc.team340.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Variant of {@link DigitalInput} that allows
 * for sensor voting
 */
public class VotableInput extends DigitalInput {
	
	/** The previous value of the DI */
	private boolean prevVal = true;
	
	/** Number of votes this DI gets, based on transitions detected */
	private int votes = 3;
	
	/**
	 * Create an instance of a Digital Input class. Creates a digital input given a channel.
	 * @param channel the DIO channel for the digital input 0-9 are on-board, 10-25 are on the MXP
	 * @see DigitalInput#DigitalInput(int)
	 */
	public VotableInput(int channel) {
		super(channel);
	}
	
	/**
	 * Allow the sensors to take a vote
	 * @param sensors the {@link VotableInput}s
	 * taking a vote
	 * @return the ultimate decision
	 */
	public static boolean vote(VotableInput... sensors) {
		int totalVotes = 0; //Total number of allowed votes
		int trueVotes = 0; //Number of votes for true
		
		//Gives totalVotes and trueVotes their values
		for(VotableInput sensor : sensors) {
			totalVotes += sensor.getVotes();
			
			if(sensor.get()) {
				trueVotes += sensor.getVotes();
			}
		}
		
		return ((double) trueVotes)/totalVotes >= .5; //Percent of true votes at least 50%
	}
	
	/**
	 * Get the DI boolean value
	 * @return the inverse of {@link DigitalInput#get()}
	 * to account for electrical inversion
	 */
	@Override
	public boolean get() {
		return !super.get();
	}
	
	/**
	 * Get the number of votes this sensor has during the poll process
	 * @return number of votes
	 */
	public int getVotes() {
		return votes;
	}
	
	/**
	 * Adjusts {@code votes} based on whether or not a change in output was detected
	 */
	public void train() {
		if(!prevVal) {
			if(get()) {
				votes++;
				prevVal = true;
			} else {
				votes--;
			}
		} else {
			if(!get()) {
				votes++;
				prevVal = false;
			} else {
				votes--;
			}
		}
	}
	
	/**
	 * Sets the number of votes this DI has
	 * @param votes number of votes
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
