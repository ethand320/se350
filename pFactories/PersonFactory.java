package pFactories;
import pExceptions.NegativeFloorException;
import pImpls.Person;

public class PersonFactory
{
    public static Person createPerson(int startFloor, int destinationFloor) throws NegativeFloorException
    {
        return new Person(startFloor, destinationFloor);
    }
}
