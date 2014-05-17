package pExceptions;

public class PassengerNotFoundException extends Exception
{
	public PassengerNotFoundException()
	{
		super();
	}
	
	public PassengerNotFoundException(String message)
	{
		super(message);
	}
}
