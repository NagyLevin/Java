package hu.ppke.itk.java;

public class MyChildException extends MyParentException {
	private static final long serialVersionUID = 2020999566350253329L;

	public String getMessage(){
		return "It's a child";
	}
}
