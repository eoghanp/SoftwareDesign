package DesignPatterns;

public class WriteToFileCommand implements Command {

	private FileSystemReceiver fileSystem;
	
	public WriteToFileCommand(FileSystemReceiver fs) {
		this.fileSystem = fs;
	}
	
	@Override
	public void execute() {
		this.fileSystem.writeToFile();
	}

}
