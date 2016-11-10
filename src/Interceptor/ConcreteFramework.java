package Interceptor;

/*
 * 1. Creates Dispatcher
 * 4. bookVehicle
 * 5. notifyObsevers
 * 
 * Defines application services.
 * Integrates "Dispatchers" that allow applications to intercept events.
 * Delegates events to associated dispatchers. 
 */

public class ConcreteFramework 
{
	public Dispatcher d;
	
	public void createDispatcher()
	{
		d = new Dispatcher();
		
	}
	
	public Dispatcher getDispatcher(){
		return this.d;
	}
}
