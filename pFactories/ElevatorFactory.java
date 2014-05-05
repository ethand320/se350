package pFactories;
import pImpls.Elevator;
import pInterfaces.ElevatorInterface;
import pImpls.Elevator;

public class ElevatorFactory
{
    private ElevatorFactory()
    {
        
    }
    public static ElevatorInterface createElevator(int elevatorId, int capacity)
    {
        return new Elevator(elevatorId, capacity);
    }
}