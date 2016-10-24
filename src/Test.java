import java.util.Vector;
public class Test {
	public static void main(String[] args) {
		Vector<String> test = new Vector<String>();
		test.addElement("bonjour");
		test.addElement("ça va ?");
		for(int i=0; i < test.size(); i++){
			System.out.print("elem "+i+": "+test.get(i));
			if(i != test.size() -1)
				System.out.print(" / ");
		}
		System.out.println();
		System.out.println("Hello world");
	}
}
