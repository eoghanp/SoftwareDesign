package CommandDP;

/*
 * Instructs the Command to carry out the request in response to an event
 */

public class UserInvoker {

	public Command command;
	
	public UserInvoker(Command command) {
		this.command = command;
	}
	
	public void execute() {
		this.command.execute();
	}
	
}
