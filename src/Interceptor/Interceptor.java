package Interceptor;

/*
 * Defines an interface for integrating out-of-band services - (Registers with the framework by predefined interfaces).
 */

public interface Interceptor 
{
	public void logBooking(ContextObjectInterface coi); 
}
