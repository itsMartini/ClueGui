package clueGame;

public class InvalidBoardClick extends Exception {
	private String ex;
	
	public InvalidBoardClick(String ex) {
		super();
		this.ex = ex;
	}

	@Override
	public String toString() {
		return "ERROR: " + ex;
	}
}
