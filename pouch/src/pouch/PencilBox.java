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

interface Item {
	String getItem();
	String getBrand(); 
	String getProperty();
	void setBrand(String str);
	void setProperty(String str);
	String print();
}

class Factory{
	public Item getObject(String str) {
		if(str.equalsIgnoreCase("pencil")) {
			return new Pencil();
		} else if (str.equalsIgnoreCase("eraser")){
			return new Eraser();
		} else if(str.equalsIgnoreCase("sharpener")) {
			return new Sharpener();
		} else {
			return null;
		}
	}
}

class Pencil implements Item{
	private String brand;
	private String length;

	public String getItem() {
		return "pencil";
	}

	public String getBrand() {
		return brand;
	}

	public String getProperty() {
		return length;
	}

	public String print() {
		return getItem()+","+getBrand()+","+getProperty();
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	
	public void setProperty(String str) {
		this.length = str;
	}
}

class Eraser implements Item{

	private String brand;
	private String shape;

	public String getItem() {
		return "eraser";
	}

	public String getBrand() {
		return brand;
	}

	public String getProperty() {
		return shape;
	}

	public String print() {
		return getItem()+","+getBrand()+","+getProperty();
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	
	public void setProperty(String str) {
		this.shape = str;
	}
}

class Sharpener implements Item{

	private String brand;
	private String state;

	public String getItem() {
		return "sharpener";
	}

	public String getBrand() {
		return brand;
	}

	public String getProperty() {
		return state;
	}

	public String print() {
		return getItem()+","+getBrand()+","+getProperty();
	}
	
	public void setBrand(String str) {
		this.brand = str;
	}
	
	public void setProperty(String str) {
		this.state = str;
	}
}

public class PencilBox {
	
	enum Path{
		PATH("pouch/temp.csv"),
		TEMP("pouch/tmp.csv");
		String FILEPATH;
		
		Path(String path){
			FILEPATH = path;
		}
		
		public String getPath() {
			return FILEPATH;
		}
	}
	
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
		String line ="";
		removeBlank(Path.PATH.getPath());
		
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(Path.PATH.getPath()));
			removeBlank(Path.PATH.getPath());
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
		
		Factory factory = new Factory();
		Item obj = factory.getObject(args[1]);
		obj.setBrand(args[2]);
		obj.setProperty(args[3]);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Path.PATH.getPath(), true));
		bufferedWriter.write(obj.print());
		bufferedWriter.close();
		
		}
	
	public static void removeHelp() {
		System.out.println("remove: USAGE: \nremove <itemname> <brandName>");
		System.out.println("Example: remove pencil apsara");
	}
	
	public static void remove(String[] args) throws IOException {
		File oldFile = new File(Path.PATH.getPath());
		File newFile = new File(Path.TEMP.getPath());
		String item = ""; String property1 = ""; String property2 = "";
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		Scanner scanner = null;
		removeBlank(Path.PATH.getPath());
		try {
			new FileWriter(Path.TEMP.getPath(), false).close();
			fileWriter = new FileWriter(Path.TEMP.getPath(),true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
			scanner = new Scanner(new File(Path.PATH.getPath()));
			scanner.useDelimiter("[,\n]");
			
			
			while(scanner.hasNext()){
				item = scanner.next();
				property1 = scanner.next();
				property2 = scanner.next();
				if(item.equalsIgnoreCase(args[1]) && property1.equalsIgnoreCase(args[2])) {
					continue;
				} else {
					printWriter.println(item + "," + property1 + "," + property2);
				}
			}
			printWriter.flush();
			printWriter.close();
			scanner.close();
			oldFile.delete();
			File dump = new File(Path.PATH.getPath());
			newFile.renameTo(dump);
			removeBlank(Path.PATH.getPath());
		
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
		
		switch(args[0]) {
		case "view":
			if (args.length == 1) {
				view();
			} else {
				System.out.println("view doesn't take more arguments");
			}
			break;
		case "add":
			if(args.length == 4) {
				add(args);
			} else {
				addHelp();
			}
			break;
		case "remove":
			if(args.length == 3) {
				remove(args);
			} else {
				removeHelp();
			}
			break;
		}
		

	}

}
