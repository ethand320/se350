
/*
Old doesnt need to be run!


public class Main
{
    public static void main (String... args)
    {
        try
        {
            Building.getInstance(15,6);
            System.out.println("Building created with 15 floors and 6 elevators.");
            Thread.sleep(1000);
            Building.getInstance().getElevator(1).pressButton(5);
            Thread.sleep(10000);
            Building.getInstance().getElevator(2).pressButton(14);
            Building.getInstance().getElevator(2).pressButton(13);
            Thread.sleep(20000);
            Building.getInstance().getElevator(1).pressButton(7); //since elevator has timed out, this should go through
            Thread.sleep(10000);
            Building.getInstance().getElevator(1).pressButton(10);
            Thread.sleep(4000);
            Building.getInstance().getElevator(1).pressButton(2);
            Thread.sleep(60000);
            System.out.println("Elevators are now shutting down");
            for (int i = 1; i <= 6; i++)
            {
                Building.getInstance().getElevator(i).shutDownElevator();
            }
            System.out.println("Elevators have shut down");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
*/