import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ReadTxt {

	public static void main(String[] args){
		
		String word = "the";
		int count = 0;
		String line;
		File file = new File("topID.csv");
		BufferedReader br = null;
		BufferedWriter bw = null;
        
		try{
			
  	  	if (!file.exists()) {
  	  		file.createNewFile();
  	  	}

  	  	FileWriter fw = new FileWriter(file);
        
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		//Pattern pattern = Pattern.compile("\\b" + WORD + "\\b", Pattern.CASE_INSENSITIVE);
		
			br = new BufferedReader(new FileReader("C:/Users/desp/workspace/ReadInputFile/src/pg2600.txt"));
	  	  	bw = new BufferedWriter(fw);
	
	    while ((line = br.readLine()) != null){ // 
	    	//Matcher matcher = pattern.matcher(line);
	    	//line.replaceAll("\\^[A-z]", " ");
		     String[] array = line.split("[^A-z]"); // split("^A-z")
		     //while(matcher.find()){count++}
		     for(int n =0; n < array.length; n++){ // String word: words
		    	 if(array[n].compareToIgnoreCase(word) == 0 ){ //word.compareTo(word == 0) 
		    		 count++;
		    	 }
		    	 if(hm.containsKey(word)){
		    		 hm.put(word,  hm.get(word) + 1);
		    	 }else{
		    		 hm.put(word, 1);
		    	 }
		     }
	    }//while
		for (String key : hm.keySet()) { //key corresponds to each word
			bw.write( key + (hm.get(word)));
			//fw.append(key + (hm.get(word)));
		}
	    
			
		}catch(IOException e){
			System.out.println(e);
		}
		finally{
			try{
			if (br != null){
				br.close();
			}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("The word: " + word + " occurs " + count + " times.");
	}


}
