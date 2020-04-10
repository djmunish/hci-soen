import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GccHelper { // Linker File
	public static String runCommand(String command) {
		String result="";
		String line=null;
		try {
			Runtime rt = Runtime.getRuntime();
			//Process pr = rt.exec("./test"); //running
			//            Process pr = rt.exec("g++ test.cpp -o test");  //compiling
			Process pr = rt.exec(command);

			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

			while((line = input.readLine()) != null) {
				if (!(isNullOrEmpty(line))){
					result += line + "\n";
					}
				System.out.println("result="+line);
				
			}	
		
			
			if(command.equals("g++ test.cpp -o test")) {
				int exitVal = pr.waitFor();
				System.out.println("command executed, any errors? " + (exitVal == 0 ? "No" : "Yes"));
				result="command executed, any errors? " + (exitVal == 0 ? "No" : "Yes");
				return result;
			}
			else {
				return result;
			}

		} catch(Exception e) {
			return("exception"+e.toString());
		}

	}
	public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}
