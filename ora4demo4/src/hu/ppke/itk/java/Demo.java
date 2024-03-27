package hu.ppke.itk.java;

public class Demo {

	/**
	 * Futtassuk ezt az alkalmazást többször!
	 * Nézzük meg a kiíratások sorrendjét!
	 */
	public static void main(String[] args) {
		try {
			thrower();
		} catch (MyParentException e){
			System.out.println("Én most egy szülőt kaptam el.");
			System.err.println(e.getMessage());
		} finally {
			System.out.println("Ez mindig lefut.");
		}
		
		try {
			boolean first = true;
			if (first){
				throw new MyOtherChildException();
			} else {
				throw new MyChildException();
			}
		} catch (MyChildException e){
			System.out.println("Elkaptam a MyChildException-t");
		} catch (MyParentException e){
			System.err.println(e.getMessage());
		} finally {
			System.out.println("Ez is lefut.");
		}
		
		try{
			throw new RuntimeException(); //Ellenőrizetlen kivétel
		} finally{
			System.out.println("Ez akkor is lefut, ha elszáll a program."); //es tenyleg
		}
	}
	
	public static void thrower() throws MyChildException{
		throw new MyChildException();
	}

}
