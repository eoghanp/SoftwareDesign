package Interceptor;

public class ConcreteInterceptor implements Interceptor
{

	@Override
	public void logBooking(ContextObjectInterface coi) 
	{
		String vehName = coi.getInfo();
		System.out.println(vehName);
	}
	
}
