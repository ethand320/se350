package pFactories;
import pInterfaces.FloorInterface;
import pImpls.Floor;

public class FloorFactory
{
    private FloorFactory()
    {
        
    }
    public static FloorInterface createFloor(int floorId)
    {
        return new Floor(floorId);
    }
}