package programs.designPatterns.CommandPattern;

public class UndoRemoteButton extends Button {
	public UndoRemoteButton() {

	}

	@Override
	public void press() {
		prevCommand.undo();
	}
}
