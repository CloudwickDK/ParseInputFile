import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTxt {

	public static void main(String[] args){
		
		String word = "the";
		int count = 0;
		String line;
		BufferedReader b= null;
		//Pattern pattern = Pattern.compile("\\b" + WORD + "\\b", Pattern.CASE_INSENSITIVE);
		try{
			b = new BufferedReader(new FileReader("C:/Users/desp/workspace/ReadInputFile/src/pg2600.txt"));
	
	    while ((line = b.readLine()) != null){ // 
	    	//Matcher matcher = pattern.matcher(line);
	    	//line.replaceAll("\\^[A-z]", " ");
		     String[] array = line.split("[^A-z]"); // split("^A-z")
		     //while(matcher.find()){count++}
		     for(int n =0; n<array.length; n++){ // String word: words
		    	 if(array[n].compareToIgnoreCase(word) == 0 ){ //word.compareTo(word == 0) 
		    		 count++;
		    	 }
		     }
		    }	    
			
		}catch(IOException e){
			System.out.println(e);
		}
		finally{
			try{
			if (b != null){
				b.close();
			}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("The word: " +word + " occurs " + count + " times.");

	}
}
