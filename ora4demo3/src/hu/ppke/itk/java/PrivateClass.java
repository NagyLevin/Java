package hu.ppke.itk.java;

public class PrivateClass {
	public String field = "Ez publikus.";

	@SuppressWarnings("unused")
	// Tisztában vagyunk vele
	private String privateField = "Ez privát.";

	public void allowed() {
		System.out.println("   Ez egy publikus tagfüggvény.");
	}

	@SuppressWarnings("unused")
	// Tisztában vagyunk vele
	private void restricted() {
		System.out.println("   Ez egy privát tagfüggvény.");
	}
}
