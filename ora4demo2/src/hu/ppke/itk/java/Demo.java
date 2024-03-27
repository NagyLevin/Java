package hu.ppke.itk.java;


public class Demo {
	private static int[] createArrayWithResults(int[] array, IExample ie) {
		if (array != null && ie != null) {
			int[] ret = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				ret[i] = ie.getResult(array[i]);
			}
			return ret;
		}
		return null;
	}

	public static void main(String[] args) {
		Outer outer = new Outer();
		System.out.println(outer);

		// Az Inner1 classot itt nem érjük el

		// Az Inner2 példányt csak egy Outer példányon keresztül tudjuk példányosítani!
		Outer.Inner2 inner2 = outer.new Inner2();
		System.out.println(inner2);

		// A StaticInner mivel nem függ az Outer osztálytól ezért egyszerűen példányosítható.
		Outer.StaticInner staticInner = new Outer.StaticInner();
		System.out.println(staticInner);

		// Anonymous inner class példa:
		System.out.println("The anonymous InnerInterface's result: " + new Outer.InnerInterface() {

			@Override
			public double getResult() {
				return Math.random() * 100;
			}
		}.getResult());


		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

		// Anonym osztály:
//		 ie = new IExample() {
//		 @Override
//		 public int getResult(int x) {
//		 return x*x;
//		 }
//		 };

		// Lambda kifejezés:
		IExample ie = (x) -> x * x;
//	  IExample ie = (int x) -> { return x * x; };

		for (int value : array) {
			int result = ie.getResult(value);
			System.out.println("The result for " + value + " is: " + result);
		}
		System.out.println();

		int[] results = createArrayWithResults(array, (x) -> x * 100);
		for (int i = 0; i < array.length; i++) {
			System.out.println("The result for " + array[i] + " is: " + results[i]);
		}
		System.out.println();

		int q = 4;
		results = createArrayWithResults(array, (x) -> x / q);
		for (int i = 0; i < array.length; i++) {
			System.out.println("The result for " + array[i] + " is: " + results[i]);
		}
	}
}
