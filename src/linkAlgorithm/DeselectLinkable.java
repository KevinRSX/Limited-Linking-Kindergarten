package linkAlgorithm;

public class DeselectLinkable implements Command {
	Linkable linkable;
	public DeselectLinkable(Linkable l) {
		linkable = l;
	}
	
	@Override
	public void execute() {
		linkable.deselect();
	}

}
