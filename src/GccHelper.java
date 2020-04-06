import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GccHelper { // Linker File
    public static void runCommand(String command) {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("./test"); //running
//            Process pr = rt.exec("g++ test.cpp -o test");  //compiling
            Process pr = rt.exec(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = null;

            while((line = input.readLine()) != null) {
                System.out.println("result==="+line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);

        } catch(Exception e) {
            System.out.println("exception"+e.toString());
            e.printStackTrace();
        }
    }
}
