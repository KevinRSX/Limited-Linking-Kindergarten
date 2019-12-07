package linkAlgorithm;

import java.util.LinkedList;
import java.util.Queue;

// Command Pattern for this package is developed with reference to
// https://notfalse.net/4/command-pattern#-Encapsulate-Receiver
public class CommandManager {
	private Queue<Command> allCommands = new LinkedList<>();
	
	public void addCommand(Command c) {
		allCommands.add(c);
	}
	
	public void removeCommand(Command c) {
		allCommands.remove(c);
	}
	
	public void executeAllCommands() {
		while (!allCommands.isEmpty()) {
			Command cmd = allCommands.poll();
			cmd.execute();
		}
	}
}
