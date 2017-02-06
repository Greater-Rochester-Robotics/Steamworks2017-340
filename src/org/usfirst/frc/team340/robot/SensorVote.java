package org.usfirst.frc.team340.robot;

/**
 * <h1><em>SensorVote</em></h1>
 * <br>
 * Class that allows a slew of {@link VotableInput}s
 * to vote on whether or not the result should be
 * ultimately true or false.<br>
 * This is in its own class for the sake of
 * organization, but since methods are {@code static}
 * this shouldn't be much of a problem, if one at all
 */
public class SensorVote {
	
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
		for(int i = 0; i < sensors.length; i++) {
			totalVotes += sensors[i].getVotes();
			
			if(sensors[i].get()) {
				trueVotes += sensors[i].getVotes();
			}
		}
		
		return ((double) trueVotes)/totalVotes >= .5;
	}
}
