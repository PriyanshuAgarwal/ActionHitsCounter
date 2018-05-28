import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Arrays;


public class ActionParser{

  public static void printMap(HashMap<String, Integer> map) {
    // Print the content of the hashMap
    Set<Entry<String, Integer>> hashSet = map.entrySet();
    for(Entry entry:hashSet) {
      String s = entry.getKey().toString();
      String[] keyParts = s.split("#");
      System.out.println(keyParts[0] + " => " + keyParts[1] + " action ran " + entry.getValue() + " times");
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("development.log");
    Scanner sc = new Scanner(file);
    HashMap<String, Integer> map = new HashMap<>();
    while(sc.hasNextLine()){
      String s = sc.nextLine();
      if(s.contains("Processing by")) {
        s = s.replaceAll("Processing by", "");
        s = s.replaceAll("as JSONAPI", "");
        s = s.replaceAll("as", "");
        s = s.replaceAll("/", "");
        s = s.replaceAll("\\*\\*", "");
        s = s.trim();
        if (map.containsKey(s)) {
          map.put(s, map.get(s) + 1);
        }
        else {
          map.put(s, 1);
        }
      }
    }
    printMap(map);
  }
}
