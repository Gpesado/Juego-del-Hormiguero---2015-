package hormiguero;
import java.io.*;

public class Entrada 
{
	public static String readLine()
	{
		String s = "";
		try {
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			s = in.readLine();
		} catch (Exception e) {
			System.out.println("Error! Exception: "+e); 
		}
		return s;
	}
}
