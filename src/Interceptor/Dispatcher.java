package Interceptor;

import java.util.ArrayList;
import java.util.List;

/*
 * 6. iterate observers (callback)
 * 6.1 dispatcher.log
 * add/remove Interceptor, callback
 * 
 * Allows "Applications" to register and remove concrete "interceptors".
 * Dispatchers registered concrete "interceptor" callbacks when event occurs.
 * 
 * When an event occurs, the framework notifies the appropriate dispatcher to invoke the callback.
 */

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

	// callback
	@Override
	public void log(ContextObjectInterface coi) 
	{	
		for(Interceptor i : listOfInterceptors)
		{
			i.logBooking(coi);
		}
	}	
}
