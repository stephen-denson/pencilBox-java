package pouch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Pencil {
	private String brand;
	private String length;
	
	public String getItem() {
		return "pencil";
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	public void setLength(String str) {
		this.length = str;
	}
}

class Eraser {
	private String brand;
	private String shape;
	
	public String getItem() {
		return "eraser";
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getShape() {
		return shape;
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	public void setShape(String str) {
		this.shape = str;
	}
}

class Sharpener {
	private String brand;
	private String state;
	
	public String getItem() {
		return "sharpener";
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getState() {
		return state;
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	public void setState(String str) {
		this.state = str;
	}
}

public class PencilBox {
	
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
	
	public static void view() throws IOException, FileNotFoundException {
		String path = "C:\\Users\\hayth\\eclipse-workspace\\pouch\\src\\pouch\\temp.csv";
		String line ="";
		removeBlank(path);
		
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
			removeBlank(path);
	        while((line = bufferedReader.readLine()) != null) {
	        	String[] values = line.split(",");	        	
	        	if(values[0].trim().equalsIgnoreCase("pencil")) {
	        		System.out.println("Item: " + values[0] + " \tBrand: " + values[1] + " \tLength: " + values[2]);
	        	} else if(values[0].trim().equalsIgnoreCase("eraser")){
	        		System.out.println("Item: " + values[0] + " \tBrand: " + values[1] + " \tShape: " + values[2]);
	        	} else {
	        		System.out.println("Item: " + values[0] + " Brand: " + values[1] + " \tState: " + values[2]);
	        	}
	        	
	        	
	        }
		} finally {
			if( bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
	
	public static void addHelp() {
		System.out.println("add: USAGE: \nadd <itemname> <property1> <property2>");
		System.out.println("item: pencil properties: brand length");
		System.out.println("item: eraser properties: brand shape");
		System.out.println("item: sharpener properties: brand state");
	}
	
	public static void add(String[] args) throws IOException {
		String path = "C:\\Users\\hayth\\eclipse-workspace\\pouch\\src\\pouch\\temp.csv";
		
			if("pencil".equalsIgnoreCase(args[1])) {
				Pencil obj = new Pencil();
				obj.setBrand(args[2]);
				obj.setLength(args[3]);
				String str = obj.getItem() + "," + obj.getBrand() + "," + obj.getLength();
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
				bufferedWriter.write(str);
				bufferedWriter.close();				
				
			} else if("eraser".equalsIgnoreCase(args[1])) {
				Eraser obj = new Eraser();
				obj.setBrand(args[2]);
				obj.setShape(args[3]);
				String str = obj.getItem() + "," + obj.getBrand() + "," + obj.getShape();
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
				bufferedWriter.write(str);
				bufferedWriter.close();
				
			} else if("sharpener".equalsIgnoreCase(args[1])) {
				Sharpener obj = new Sharpener();
				obj.setBrand(args[2]);
				obj.setState(args[3]);
				String str = obj.getItem() + "," + obj.getBrand() + "," + obj.getState();
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
				bufferedWriter.write(str);
				bufferedWriter.close();
			}
		}
	
	public static void removeHelp() {
		System.out.println("remove: USAGE: \nremove <itemname> <brandName>");
		System.out.println("Example: remove pencil apsara");
	}
	
	public static void remove(String[] args) throws IOException {
		String path = "C:\\Users\\hayth\\eclipse-workspace\\pouch\\src\\pouch\\temp.csv";
		String temp = "C:\\Users\\hayth\\eclipse-workspace\\pouch\\src\\pouch\\tmp.csv";
		File oldFile = new File(path);
		File newFile = new File(temp);
		String item = ""; String property1 = ""; String property2 = "";
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		Scanner scanner = null;
		removeBlank(path);
		try {
			new FileWriter(temp, false).close();
			fileWriter = new FileWriter(temp,true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
			scanner = new Scanner(new File(path));
			scanner.useDelimiter("[,\n]");
			
			
			while(scanner.hasNext()){
				item = scanner.next();
				property1 = scanner.next();
				property2 = scanner.next();
				if(item.equals(args[1]) && property1.equals(args[2])) {
					continue;
				} else {
					printWriter.println(item + "," + property1 + "," + property2);
				}
			}
			printWriter.flush();
			printWriter.close();
			scanner.close();
			oldFile.delete();
			File dump = new File(path);
			newFile.renameTo(dump);
			removeBlank(path);
		
	} finally {
		if(bufferedWriter != null) {
			bufferedWriter.close();
		}
		if(fileWriter != null) {
			fileWriter.close();
		}
		}		
	}
		
	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		if (args.length == 1 && "view".equals(args[0])){
			view();
		} else if ("add".equals(args[0]) && args.length < 4) {
			addHelp();
		} else if ("add".equals(args[0]) && args.length == 4){
			add(args);
		} else if ("remove".equals(args[0]) && args.length < 3) {
			removeHelp();
		} else if ("remove".equals(args[0]) && args.length == 3){
			remove(args);
		}
		

	}

}
