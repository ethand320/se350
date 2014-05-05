package pImpls;

public class Person 
{
	private int destinationFloor;
	private int currentFloor;
	public Person(int floorToStart, int floorToStop)
	{
		setDestinationFloor(floorToStop);
		setCurrentFloor(floorToStart);
	}
	private void setDestinationFloor(int inDest)
	{
		destinationFloor = inDest;
	}
	private void setCurrentFloor(int inCur)
	{
		currentFloor = inCur;
	}
}
