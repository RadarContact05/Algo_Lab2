import java.lang.reflect.Array;

public class TestArraysPairs{
  // Notice the throws clause!
  public static void main(String[] args) throws ClassNotFoundException {

    // The following does not compile:
    // Pair<String,Integer> [] word_freq = new Pair<String,Integer>[5];

    // The following compiles but then we cannot do the assignments (runtime)
    // Pair<String,Integer> [] word_freq = (Pair<String,Integer>[]) new Object[5];

    Pair<String,Integer> [] word_freq =
        (Pair<String,Integer> [])Array.newInstance(Class.forName("Pair"), 5);
    word_freq[0] = new Pair<String, Integer>("a", 34) ;
    word_freq[1] = new Pair<String, Integer>("the", 30);
    word_freq[2] = new Pair<String, Integer>("is", 10) ;
    word_freq[3] = new Pair<String, Integer>("and", 110);
    word_freq[4] = new Pair<String, Integer>("he", 1);
    GenericArraySupport.println(word_freq) ;
    GenericSorting.insertionSort(word_freq);
    GenericArraySupport.println(word_freq) ;
  }
}
