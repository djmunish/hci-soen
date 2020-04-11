import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class GccHelper { // Linker File
	public static String runCommand(String command) {
		String result = "";
		String line = null;
		try {
			Runtime rt = Runtime.getRuntime();
			//Process pr = rt.exec("./test"); //running
			//            Process pr = rt.exec("g++ test.cpp -o test");  //compiling
			Process pr = rt.exec(command);

			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

			while((line = input.readLine()) != null) {
				if(!isNullOrEmpty(line)){
					result += line + "\n";
				}
				System.out.println("result="+line);
			}
			System.out.println("dsad="+result);

			if(command.equals("g++ test.cpp -o test")) {
				int exitVal = pr.waitFor();
				System.out.println("command executed, any errors? " + (exitVal == 0 ? "No" : "Yes"));
				result="command executed, any errors? " + (exitVal == 0 ? "No" : "Yes");
				return result;
			}
			else if (command.equals("g++ -D DEBUG test.cpp -o debug")){
				return result;
			}
			else {
				return result;
			}

		} catch(Exception e) {
			return("exception"+e.toString());
		}

		
	}


	public static void runCompile(String command){

		try {
			Process process = Runtime.getRuntime().exec(command);

			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(process.getOutputStream()));


			TextInputDialog dialog = new TextInputDialog("Enter your input");

			dialog.setHeaderText("Enter your inputs if any:");
			dialog.setContentText("Input:");

			Optional<String> result = dialog.showAndWait();

			result.ifPresent(input -> {
				System.out.println(input);
				try {
					writer.write(input);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			writer.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public static void compileCode(String command) {

		try {
			Process process = Runtime.getRuntime().exec(command);
			String line;
			BufferedReader errorReader = new BufferedReader(
					new InputStreamReader(process.getErrorStream()));
			while ((line = errorReader.readLine()) != null) {
				System.out.println(line);
			}
			errorReader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	public static boolean isNullOrEmpty(String str) {
		if(str != null && !str.isEmpty())
			return false;
		return true;
	}
}
