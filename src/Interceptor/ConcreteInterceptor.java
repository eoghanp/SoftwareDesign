package Interceptor;

/*
 * 6.1.1 Logs stuff - i.logBooking
 * Implements a specific out-of-band service.
 * Uses "Context Object" to control the concrete framework.
 */

public class ConcreteInterceptor implements Interceptor
{

	@Override
	public void logBooking(ContextObjectInterface coi) 
	{
		String vehName = coi.getInfo();
		System.out.println(vehName);
	}
	
}
