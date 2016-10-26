package CommandDP;

/*
 * Specifies a binding between a Receiver and an action to be carried out.
 * Implements the execute() operation by invoking the action on the specified Receiver
 */

public class CloseFileCommand implements Command {

	private UserReceiver user;
	
	public CloseFileCommand(UserReceiver ur) {
		this.user = ur;
	}
	
	@Override
	public void execute() {
		this.user.closeFile();	
	}

}
