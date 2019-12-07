package linkAlgorithm;

public class SelectLinkable implements Command {

	Linkable linkable;
	public SelectLinkable(Linkable l) {
		linkable = l;
	}
	@Override
	public void execute() {
		linkable.select();
	}

}
