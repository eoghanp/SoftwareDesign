package Interceptor;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher implements Observer 
{
	private List<Interceptor> listOfInterceptors;
	
	public Dispatcher() {
		super();
		listOfInterceptors = new ArrayList<Interceptor>();
	}

	public void addInterceptor( Interceptor i)
	{
		listOfInterceptors.add(i);
	}
	
	public void removeInterceptor(Interceptor i){
		listOfInterceptors.remove(i);
	}

	@Override
	public void log(ContextObjectInterface coi) 
	{	
		for(Interceptor i : listOfInterceptors)
		{
			i.logBooking(coi);
		}
	}
	
	
}
