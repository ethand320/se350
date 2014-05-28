package pFactories;
import pExceptions.NegativeFloorException;
import pImpls.Person;

/**
 * Factory class for Person objects. An instance of this class is not necessary to create such objects.
 */
public class PersonFactory
{
	/**
	 * Static factory method used to create Person objects.
	 * @param startFloor The floor that this person should start on. Used with destinationFloor to compute which direction this Person needs to
	 * go.
	 * @param destinationFloor The floor that this person should end on. Used with startFloor to compute which direction this Person needs to go.
	 * @return A new Person object whose start and destination floors are initialized to the values of startFloor and destinationFloor
	 * @throws NegativeFloorException if either startFloor or destinationFloor are negative or otherwise outside the bounds of the simulation.
	 */
	public static Person createPerson(int inID, int startFloor, int destinationFloor) throws NegativeFloorException
	{
		return new Person(inID, startFloor, destinationFloor);
	}
}
