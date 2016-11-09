package Interceptor;

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
