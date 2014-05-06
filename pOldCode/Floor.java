public class Floor implements CallBox
{
    private boolean upRequest = false;
    private boolean downRequest = false;
    public Floor()
    {
        
    }
    public Floor(int capacityParam) //does nothing right now, meant to serve as potential future implementation
    {
        
    }
    public void pressButton(int direction) throws InvalidFloorException
    {
        if (direction > 0)
        {
            upRequest = true;
        }
        else
        {
            downRequest = true;
        }
    }
}
