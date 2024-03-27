package hu.ppke.itk.java;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Demonstrator {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) {

		// Elkérjük az Integer osztályát (később)
		// Itten lesznek ilyen kacsacsőr dolgok, ezek generikus típusok, lesz erről külön óra, viszont elég intuitív.
		Class<Integer> demo = Integer.class;

		// Megkérdezzük az osztálytól, hogy melyik csomagban van
		// A Package reprezentál egy csomagot
		Package pack = demo.getPackage();

		// Nézzük, mit tud a Package
		System.out.println("A csomag neve: " + pack.getName());
		System.out.println("Specifikáció: " + pack.getSpecificationTitle());
		System.out.println("Ő implementálta: " + pack.getImplementationVendor());
		System.out.println("A specifikáció pedig tőle van: " + pack.getSpecificationVendor());
		System.out.println();

		Package pack2 = Package.getPackage("java.lang");
		if (pack2.equals(pack)) {
			System.out.println("Statikus függvénnyel is lekérhetjük a csomagokat.");
			// A függvény nullal tér vissza, ha nincs olyan csomag.
		}
		System.out.println();
		System.out.println();
		/**
		 * Nem kell fejből tudni a Package szolgáltatásait, de nem árt, ha legalább egyszer látta őket az ember. Nem tudhatjátok, mikor jön jól.
		 */


		/**
		 * Most nézzük meg, mit tud a Class osztály. Ez jóval érdekesebb, mint a Package.
		 * 
		 * Le lehet kérdezni az osztály metódusait és adattagjait és hozzáférhetünk a privát tagokhoz is. Jó ez nekünk?
		 */

		/**
		 * Nézzük az alapokat!
		 *
		 * Ahogy a Package egy csomagot, úgy a Class egy általános osztályt reprezentál
		 *
		 * Primitív típusoknak is lekérhetjük az "osztályát" de csak így:
		 * 
		 */
		Class<Boolean> booleanClass = boolean.class;
		Class<Integer> intClass = int.class;

		/**
		 * Az alábbi módok már nem működnek Ez azért van, mert az int nem valódi osztály int i=0; Class dontWork = i.getClass(); Class dontWorkToo = i.class;
		 * 
		 * String esetén már megkérdezhetjük az osztályt tagfüggvénnyel
		 * 
		 */
		String str = "Ez egy string";
		@SuppressWarnings("rawtypes")
		Class strClass = str.getClass();

		/**
		 * De a .class csak típusok esetén működik. Példányok esetén nem.
		 */
		// Class strClass2 = str.class;

		/**
		 * Most van egy str referenciánk, ami egy String-re mutat. És van egy strClass referenciánk, ami az str-től elkért Class-ra mutat.
		 * 
		 * A kettőnek mi köze egymáshoz?
		 * 
		 * 
		 * Semmi, mert a Class egy általános osztályt jelent.
		 */

		/**
		 * Lehetőségünk van az osztály neve alapján elkérni a Class-objektumot.
		 * 
		 * Ilyenkor a típus teljes nevét meg kell adni!
		 */

		try {
			/**
			 * Ez kivételdobással jelzi, hogy ha nem található az osztály.
			 */
			strClass = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			System.out.println("Bocs, de nincs ilyen osztály.");
		}

		/**
		 * Mit tudunk kezdeni egy ilyen Class-szal?
		 */

		// Le tudjuk kérdezni a nevét.
		System.out.println("A neve: " + strClass.getCanonicalName());

		/**
		 * Névlekérdező metódusok közti különbség START
		 */

		 //primitive
		 System.out.println(int.class.getName());
		 System.out.println(int.class.getCanonicalName());
		 System.out.println(int.class.getSimpleName());

		 System.out.println();

		 //class
		 System.out.println(String.class.getName());
		 System.out.println(String.class.getCanonicalName());
		 System.out.println(String.class.getSimpleName());

		 System.out.println();

		 //inner class
		 System.out.println(HashMap.SimpleEntry.class.getName());
		 System.out.println(HashMap.SimpleEntry.class.getCanonicalName());
		 System.out.println(HashMap.SimpleEntry.class.getSimpleName());

		 System.out.println();

		 //anonymous inner class
		 System.out.println(new Serializable(){}.getClass().getName());
		 System.out.println(new Serializable(){}.getClass().getCanonicalName());
		 System.out.println(new Serializable(){}.getClass().getSimpleName());

		/**
		 * Névlekérdező metódusok közti különbség END
		 */

		// El tudjuk kérni a ClassLoader-ét.
		// Akit ez érdekel, nézzen utána! Érdekes. :)
		ClassLoader strClassLoader = strClass.getClassLoader();

		Constructor<String> strConstruct = null;
		try {
			/**
			 * El tudjuk kérni a konstruktorát. Mivel több konstruktor is lehet, ezért meg kell adnunk a megfelelő paraméterlistát.
			 * 
			 */
			strConstruct = strClass.getConstructor(String.class);
			// Ismétlőkérdés: Melyik konstruktor ez?

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("Ennek nincs ilyen konstruktora.");
		}

		/**
		 * Van még millió másik szolgáltatása. Érdemes őket végignézni legalább egyszer.
		 * 
		 * Most inkább példányosítsunk egy String-et!
		 */

		String inst = null;
		try {
			inst = (String) strConstruct.newInstance("Value");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println("Az inst értéke: " + inst);

		/**/

		/**
		 * Most megnézzük, hogyan hívhatunk privát és publikus adattagokat. Játszunk mondjuk a hu.ppke.itk.java.PrivateClass-szal!
		 */

		/**
		 * Először nézzük meg, hogy is működik ez.
		 *
		 * Olvassuk ki a hu.ppke.itk.java.PrivateClass.field-et! Ez publikus
		 */

		// Példányosítom a szokott módon
		PrivateClass pClass = new PrivateClass();
		Class<? extends PrivateClass> classOfPC = pClass.getClass();
		// Ezzel egyenértékű a hu.ppke.itk.java.PrivateClass.class

		Field field = null;
		try {
			field = classOfPC.getField("field");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("Nincs 'field' nevű mezője!");
		}

		/**
		 * Ne feledjük, hogy a field objektumnak semmi köze a konkrét hu.ppke.itk.java.PrivateClass példányhoz.
		 * 
		 * Hogyan tudjuk vele mégis lekérdezni vagy beállítani egy konkrét példány konkrét mezőjét?
		 * 
		 * Így:
		 */

		Object value = null; // Érdemes kipróbálni, mi van, ha elvesszük a null-t.
		try {
			value = field.get(pClass);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (value.getClass().equals(String.class)) {
			/**
			 * Ha nem tudjuk, hogy pontosan mi ez, akkor az összes szóba jöhető típusra végig kell nézni.
			 */
			String str1 = (String) value;
			System.out.println("A mező értéke: " + str1);
		}

		/**
		 * Lekérdeztünk egy publikus adattagot a neve alapján. Egy pillanatra álljunk itt meg! Miért olyan nagy szó ez? Nem lett volna egyszerűbb egy pClass.field?
		 * 
		 * A fent bemutatott módszer lényege az, hogy a meghívandó tagfüggvény neve futás közben derült ki.
		 * 
		 * Lehetséges, hogy fordításidőben még nem is létezett az osztály, csak utólag lett bemásolva a megfelelő class-fájl a helyére.
		 */

		/**
		 * Lépjünk tovább egy szintet, és érjünk el egy publikus metódust.
		 * 
		 */

		Method publicMethod = null;
		try {
			/**
			 * A getMethod első paramétere a metódus neve A második a paraméterlistája.
			 * 
			 * Esetünkben a paraméterlista üres, ilyenkor nem kell a név után semmit sem írni.
			 * 
			 * Ha pl. két String lenne a paraméter, akkor a hívás így nézne ki:
			 * 
			 * .getMethod("allowed", String.class, String.class);
			 * 
			 * Változó hosszúságú paraméterlistákról itt lehet olvasni: http://www.deitel.com/articles/java_tutorials/20060106/VariableLengthArgumentLists.html A mai gyakorlatnak ez nem része.
			 * 
			 */
			publicMethod = classOfPC.getMethod("allowed");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		try {
			/**
			 * Hívjuk meg a metódust. Mivel a method nem köthető példányhoz, át kell adnunk egy példányt is.
			 */
			System.out.println("Meghívom a metódust: ");
			publicMethod.invoke(pClass);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		/**
		 * Most csináljunk egy érdekesebbet. Hívjuk meg a restricted nevű private metódusát!
		 */

		System.out.println();
		System.out.println();
		Method privateMethod = null;
		try {
			System.out.println("Megpróbálom lekérni a privát metódust.");
			privateMethod = classOfPC.getMethod("restricted");
		} catch (SecurityException e) {
			System.out.println("Biztonsági hiba.");
		} catch (NoSuchMethodException e) {
			System.out.println("Nem találok ilyen nevű metódust.");
		}

		try {
			System.out.println("Meghívom a metódust: ");
			privateMethod.invoke(pClass);
		} catch (NullPointerException e) {
			System.out.println("A privateMethod nullpointer volt.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		/**
		 * Nem sikerült meghívnunk a függvényt. Ezzel ugyanis csak publikus függvényekhez és adattagokhoz férhetünk hozzá.
		 * 
		 * De van megoldás arra is, hogy privát adattagokkal játszhassunk.
		 * 
		 * Először is olvassuk ki a hu.ppke.itk.java.PrivateClass privateField nevű adattagját!
		 */

		Field privateField = null;

		System.out.println();
		System.out.println("Nem baj. Csináljuk máshogy.");
		System.out.println("Először hozzá akarok férni egy" + " privát adattaghoz.");

		try {
			/**
			 * Emlékezzetek (vagy görgessetek) vissza arra a részre, amikor a publikus adattaghoz fértünk hozzá!
			 * 
			 * Ott a getField-et használtuk. Az privát adattagok esetén nem működik. (NoSuchFieldException-t dob.)
			 * 
			 * Ha megnézzük a dokumentációban, hogy mi a két függvény között a különbség, akkor kiderül, hogy tényleg csak ennyi.
			 * 
			 * A getField csak public adattagokra működik. A getDeclaredField bármilyen adattagot visszaad.
			 */

			privateField = PrivateClass.class.getDeclaredField("privateField");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		/**
		 * Most már a privateField referencián keresztül hozzá tudunk férni az adattaghoz.
		 * 
		 * Egy gond van még, ez jelenleg se nem írható, se nem olvasható. Ezen az alábbi hívással segíthetünk.
		 */

		privateField.setAccessible(true);
		System.out.println("Az adattag már hozzáférhető.");

		/**
		 * Innentől a kód ugyan az, mint a publikus adattag esetében volt
		 */

		Object privateValue = null; // Érdemes kipróbálni, mi van, ha elvesszük a null-t.
		try {
			privateValue = privateField.get(pClass);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String privateStringValue = (String) privateValue;
		System.out.println("A privát adattag értéke: " + privateStringValue);

		System.out.println();
		System.out.println("Most meghívom a privát metódust.");
		/**
		 * A privát metódus meghívása során hasonló lépéseket kell tennem, mint a privát adattag esetében.
		 */

		try {
			System.out.println("Újra megpróbálom lekérni a privát függvényt.");
			System.out.println("Ezúttal a getDeclaredMethod-dal.");
			privateMethod = classOfPC.getDeclaredMethod("restricted");
		} catch (SecurityException e) {
			System.out.println("Biztonsági hiba.");
		} catch (NoSuchMethodException e) {
			System.out.println("Nem találok ilyen nevű függvényt.");
		}

		// Itt is hozzáférhetővé kell állítanom
		privateMethod.setAccessible(true);

		/**
		 * Ezután ugyan úgy tudom ezt is hívni, mint a publikus metódust.
		 */

		try {
			System.out.println("Meghívom a privát tagfüggvényt: ");
			privateMethod.invoke(pClass);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
/**
 * A teljes Java Reflection API nem fér bele egy gyakorlatba. Ha valaki jobban el akar mélyülni a témában, akkor az alábbi linken talál egy jó tutorialt:
 * http://tutorials.jenkov.com/java-reflection/index.html
 * 
 * Viszont még van egy fontos dolog, amiről beszélni kell: Mikor jön jól az, hogy elérjünk egy privát adattagot?
 * 
 * Akkor, amikor Unit Test-et akarunk írni a saját objektumunknak.
 */
