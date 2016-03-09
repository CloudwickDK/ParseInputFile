import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class ReadTxt {

	public static void main(String[] args) {

		String line;
		BufferedReader br = null;
		BufferedWriter bw = null, bw2 = null;
		File file = new File("tf.csv"), file2 = new File("top10.csv");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (!file2.exists()) {
				file2.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			FileWriter fw2 = new FileWriter(file2);
			// HashMap<String, Integer> hm = new HashMap<String, Integer>();
			ArrayList<MyStruct> list = new ArrayList<MyStruct>();

			br = new BufferedReader(new FileReader("./src/pg2600.txt"));
			bw = new BufferedWriter(fw);
			bw2 = new BufferedWriter(fw2);

			while ((line = br.readLine()) != null) { //
				line = line.toLowerCase(Locale.US);
				String[] array = line.split("[^A-z]"); // split("^A-z")
				for (int n = 0; n < array.length; n++) { // String word: words
					
					String word = array[n];
					//System.out.println(word);
					if (word.isEmpty()) {
						continue;
					}
					MyStruct word_frequency = new ReadTxt().new MyStruct(word,0); // initiate object for this word
					ListIterator<MyStruct> listIterator = list.listIterator();
					boolean addNew = true;

					for (MyStruct element: list) {

						if (word.equals(element.word) ) {
							element.setCount(element.count + 1);
							addNew = false;
						} 
					}
					if (addNew) {
						//System.out.println("Adding");
						word_frequency.setCount(1);
						list.add(word_frequency);
					}

					/*
					 * if (list.contains(word_frequency)) {
					 * word_frequency.setCount(word_frequency.count);
					 * 
					 * } else { word_frequency.setCount(word_frequency.count); }
					 * list.add(word_frequency);
					 */
				}
			}// while

			bw.write("word, frequency\n");
			for (MyStruct object : list) { // key corresponds to each word
				bw.write(object.word + ", " + object.count + "\n");
				//System.out.println(object.word);
			}
			
			Collections.sort(list);
			System.out.println(" Writting to file top 10!");
			int loopcount = 0;
			
			for (MyStruct object : list) { // key corresponds to each word
				bw2.write( object.word + ", " + object.count + "\n");
				System.out.println(object.word);
				if(loopcount++ > 10) break;
			}
			/*
			 * Map<String, Integer> lhm = sortByValue(hm);
			 * bw2.write("word, frequency\n"); int loopcount = 0; for (String
			 * key : lhm.keySet()) { // key corresponds to each word if
			 * (loopcount++ > 10) break; // print only the first 10
			 * bw2.write(key + ", " + lhm.get(key) + "\n");
			 * System.out.println(key + ", " + lhm.get(key) + "\n"); }
			 */
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
				if (bw2 != null) {
					bw2.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Map.Entry<K, V>> st = map.entrySet().stream();
		st.sorted(
				Comparator.comparing(e -> ((Map.Entry<K, V>) e).getValue())
						.reversed()).forEachOrdered(
				e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

	public class MyStruct implements Comparable<MyStruct>{
		String word;
		int count;

		public MyStruct(String word, int count) {
			this.word = word;
			this.count = count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		@Override
		public boolean equals(Object object) {
			boolean isEqual = false;

			if (object != null && object instanceof MyStruct) {
				isEqual = (this.word == ((MyStruct) object).word);
			}

			return isEqual;
		}
		
		@Override
		public int compareTo(MyStruct w) {
		    return w.count - count; 
		}

	}// MyStruct
}
