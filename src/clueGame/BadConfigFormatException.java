package clueGame;

public class BadConfigFormatException extends Exception {
	private String file;
	private String ex;
	
	public BadConfigFormatException(String file, String ex) {
		super();
		this.ex = ex;
		this.file = file;
	}

	@Override
	public String toString() {
		return "ERROR in " + file + ": " + ex;
	}

}
