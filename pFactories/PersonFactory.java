package pFactories;
import pImpls.Person;

public class PersonFactory
{
    private PersonFactory()
    {
        
    }
    public static Person createPerson(int startFloor, int destinationFloor)
    {
        return new Person(startFloor, destinationFloor);
    }
}
