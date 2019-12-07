package linkAlgorithm;

public class DeselectLinkable implements Command {
	private Linkable linkable;
	public DeselectLinkable(Linkable l) {
		linkable = l;
	}
	
	@Override
	public void execute() {
		linkable.deselect();
	}

}
