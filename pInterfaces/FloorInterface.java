package pInterfaces;
import pImpls.Direction;

public interface FloorInterface
{
    public void summonElevator(Direction directionToGo);
    public int getId();
    //SIMPSON TODO:
    //this method isn't final, but here's the initial thought for how this method should run:
    //removeFromFloor calls elevatorToEnter.addPassengers(this.goingUp);
    public void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo);
}