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
		cf.d.addInterceptor(i);
	}
	
	
}
