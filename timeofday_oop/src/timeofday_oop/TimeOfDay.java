package timeofday_oop;
//procedure for defining a data abstraction: (=defining the API)
//
//0. declare a class and write a line of informal docs.
//
//1.Define the raw abstract state space=
//  declare the inspectores= getters
//
//2.Define the set of valid abstract states=
//  write down the abstract state invariants
//  = @invar clauses in the class's Javadoc comment
//    and/or @post clauses Javadoc comments for the getters	
//
//3. Declare and document the constructors and/or factory methods and the mutators
//

//Procedure for implementing the abstract states
//
//1. Declare the fields =
//   Define the raw concrete state space e.g. int private hours and int private minutes
//
// 2. Define the set of valid concrete states =
//    write down the representation invariants (look at the invars above int hours and int minutes, these talk about the fields not the getters!!)
//
//3. Define the mapping from concrete states to abstract states =
//   implementing the  inspectors (= the getters)
//   Must satisfy the property that each valid concrete state is mapped
//   to a valid abstract state (otherwise there is something wrong with the implementation, might seem trivial now, but see more later)
//
//4. Implement the constructors, factory methods an mutators


/** 
 *Each instance of this class stores the time of day with 1-minute granularity.
 *
 * @invar | 0 <= getHours() && getHours() <= 23
 * @invar | 0 <= getMinutes() && getMinutes() <= 59
 * @invar | getMinutesSinceMidnight() == getHours()*60 + getMinutes()
 * 
 */

public class TimeOfDay { // these three int-values defines the state of the TimeOfDay object
	
	/**
	 * @invar  | 0 <= hour && hours() <= 23
	 * @invar  | 0 <= minutes() && minutes() <= 59
	 */
	
	private int hours;
	
	private int minutes;
	
	public int getHours() {return hours;}
	
	public int getMinutes() {return minutes;}
	
	/**
	 * @post | result == getHours() *60 + getMinutes()
	 */
	
	public int getMinutesSinceMidnight() {return hours*60 + minutes;}
	
	/**
	 * defensive programming (throws)
	 * 
	 * @throws IllegalArgumentException | hours < 0 || 23 < hours
	 * @throws IllegalArgumentException | minutes<0 || 59 < minutes
	 *
	 *@post | getHours() == hours
	 *@post | getMinutes() == minutes (you might also specify MinutesSinceMidnight, but this already follows from the abstract state invariants)
	 */
	
	public TimeOfDay(int hours, int minutes){ //	what the constructor takes as arguments is independent of the choice of what you will internally store the information about the time of day object
		if (hours<0 || 23<hours)
			throw new IllegalArgumentException ("'hours' out of range");
		if (minutes<0 || 59 < minutes)
			throw new IllegalArgumentException ("'minutes' out of range");	
		this.hours= hours;
		this.minutes=minutes;
	}
	
	/*
	 * contractual programming (pre)
	 * 
	 * @pre | 0 <= hours && hours <=23
	 * @mutates | this 
	 * @post | getHours() == hours
	 * @post | getMinutes() == old(getMinutes()) (we wouldn't want the value of the minutes to change when we are just getting the hours)
	 * 
	 * You could also write it the following way, BUT only if you specified getMinutesSinceMidnight as a derived property, otherwise you'd have a problem, for now don't use this
	 * 
	 * @mutates_properties | this.getHours()
	 * @post | getHours() == hours
	 * 
	 */
	public void setHours(int hours) {this.hours=hours;}
	
	/**
	 * 
	 * @pre | 0 <= minutes && minutes <= 59
	 * @mutates | this 
	 * @post | getHours() == old(getHours())
	 * @post | getminutes()== minutes
	 */
	
	public void setMinutes(int minutes) {this.minutes=minutes;}
	
	/**
	 * @pre | 0 <= minutesSinceMidnight && minutesSinceMidnigth < 24*60
	 * @mutates | getMinutesSinceMidnight() = minutesSinceMidnight
	 * 
	 */
	
	public void setMinutesSinceMidnight(int minutesSinceMidnight) {
		this.hours= minutesSinceMidnight/60; // if you divide an int by an int, you get an int again
		this.minutes= minutesSinceMidnight % 60; // this % means what remains after dividing by 60 
	}
	
	
	
		
		
	
	

}
