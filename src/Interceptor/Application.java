package Interceptor;

/*
 * 2. Creates Interceptor - concreteInterceptor
 * 3. Attaches Interceptor - attachInterceptor
 * 
 * Runs on top of the Concrete Framework.
 * Instantiates "Concrete Interceptors" and registers them with dispatchers.
 */

public class Application 
{	
	public ConcreteFramework cf;
	
	public Application(){
		cf = new ConcreteFramework();
		cf.createDispatcher();
	}
	
	public void attachInterceptor()
	{
		Interceptor i = new ConcreteInterceptor();
		Dispatcher d = cf.getDispatcher();
		d.addInterceptor(i);
	}
	
	public Dispatcher getDispatcherFromConcreteFramework(){
		return cf.getDispatcher();
	}
}
