package pencilbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class pencilBox {
	
	public static void removeBlank(String fl) throws IOException {
		File oldFile = new File(fl);
		Scanner deleter = new Scanner(oldFile);
		String nonBlankData = "";
		while(deleter.hasNextLine()) {
			String currentLine = deleter.nextLine();
			if (!currentLine.isBlank()) {
				nonBlankData += currentLine + System.lineSeparator();
			}
		}
		PrintWriter writer = new PrintWriter(new FileWriter(fl));
		writer.print(nonBlankData);
		writer.close();
		deleter.close();
		
	}
	private static Scanner x;
	public static void main(String args[]) {
		
		String path = "C:/Users/hayth/eclipse-workspace/pencilbox/src/pencilbox/temp.csv";
		String line ="";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			if (args.length < 1){
		        System.out.println("Please provide at least one argument");
		      }
			if (args.length == 1 && "view".equals(args[0])){
				removeBlank(path);
		        while((line = br.readLine()) != null) {
		        	String[] values = line.split(",");
		        	System.out.println("Item: " + values[0] + " \tQuantity: " + values[1]);
		        }
		      }
			if (args.length == 2 && "add".equals(args[0])){
				removeBlank(path);
				String tempFile = "C:/Users/hayth/eclipse-workspace/pencilbox/src/pencilbox/tmp.csv";
				File oldFile = new File(path);
				File newFile = new File(tempFile);
				String item = ""; String quantity = "";
				try {
					new FileWriter(tempFile, false).close();
					FileWriter fw = new FileWriter(tempFile,true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					x = new Scanner(new File(path));
					x.useDelimiter("[,\n]");
					
					
					while(x.hasNext()){
						item = x.next();
						quantity = x.next();
						if(item.equals(args[1])) {
							int i = Integer.parseInt(quantity.trim());
							i=i+1;
							quantity = String.valueOf(i);
							pw.println(item + "," + quantity + "\n");
						}
						else {
							pw.println(item + "," + quantity);
						}
					}
					br.close();
					x.close();
					pw.flush();
					pw.close();
					oldFile.delete();
					File dump = new File(path);
					newFile.renameTo(dump);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if (args.length == 2 && "remove".equals(args[0])){
				removeBlank(path);
				String tempFile = "C:/Users/hayth/eclipse-workspace/pencilbox/src/pencilbox/tmp.csv";
				File oldFile = new File(path);
				File newFile = new File(tempFile);
				String item = ""; String quantity = "";
				try {
					new FileWriter(tempFile, false).close();
					FileWriter fw = new FileWriter(tempFile,true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					x = new Scanner(new File(path));
					x.useDelimiter("[,\n]");
					
					
					while(x.hasNext()){
						item = x.next();
						quantity = x.next();
						if(item.equals(args[1])) {
							int i = Integer.parseInt(quantity.trim());
							i=i-1;
							quantity = String.valueOf(i);
							pw.println(item + "," + quantity + "\n");
						}
						else {
							pw.println(item + "," + quantity);
						}
					}
					br.close();
					x.close();
					pw.flush();
					pw.close();
					oldFile.delete();
					File dump = new File(path);
					newFile.renameTo(dump);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}