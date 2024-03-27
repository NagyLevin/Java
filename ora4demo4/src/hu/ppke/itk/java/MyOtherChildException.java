package hu.ppke.itk.java;

public class MyOtherChildException extends MyParentException {
	private static final long serialVersionUID = -7493290480940014015L;

	public String getMessage(){
		return "It's an other child";
	}
}
