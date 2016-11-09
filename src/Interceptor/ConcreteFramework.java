package Interceptor;

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
