import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ReadTxt {

	public static void main(String[] args) {
		String line;
		BufferedReader br = null;
		BufferedWriter bw = null;
		File file = new File("topID.csv");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			br = new BufferedReader(new FileReader("./src/pg2600.txt"));
			bw = new BufferedWriter(fw);
			while ((line = br.readLine()) != null) { //
				String[] array = line.split("[^A-z]"); // split("^A-z")
				for (int n = 0; n < array.length; n++) { // String word: words
					String word = array[n];
					if (hm.containsKey(word)) {
						hm.put(word, hm.get(word) + 1);
					} else {
						hm.put(word, 1);
					}
				}
			}// while
			bw.write("word, frequency\n");
			for (String key : hm.keySet()) { // key corresponds to each word
				bw.write(key + ", " + hm.get(key) + "\n");
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
