package hu.ppke.itk.java;

public class Outer {

	private String field = "Outer field";

	private static int sField = 100;

	@Override
	public String toString() {
		return "Outer class with field: " + field;
	}


	private class Inner1 {

		@Override
		public String toString() {
			return "Inner1 class with Outer class' field: " + field;
		}

	}


	public class Inner2 {

		@Override
		public String toString() {
			return "Inner2 class with Outer class' field: " + field;
		}

	}


	public static class StaticInner {

		@Override
		public String toString() {
			return "StaticInner with Outer class' static sField: " + sField;
		}
	}


	public interface InnerInterface {
		double getResult();
	}
}
