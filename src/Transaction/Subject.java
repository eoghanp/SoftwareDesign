package Transaction;

import Interceptor.Dispatcher;

public interface Subject {
	public void registerObserver(Dispatcher d);
	public void removeObserver(Dispatcher d);
	public void notifyObservers();
}
