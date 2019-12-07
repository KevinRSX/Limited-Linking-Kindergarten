package linkAlgorithm;

public class CancelLinkable implements Command {
	Linkable linkable;
	public CancelLinkable(Linkable l) {
		linkable = l;
	}
	
	@Override
	public void execute() {
		linkable.cancel();
	}
}
