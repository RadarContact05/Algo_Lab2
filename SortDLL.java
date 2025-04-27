import java.util.Comparator;
public class SortDLL {
    public static <T> void dllInsertionSort(T[] a, Comparator<T> cmp) {
        DoublyLinkedList<T> dll = new DoublyLinkedList<>(); 
        for (T t : a) {
            dll.addAtFirstSmaller(t, cmp);                
        }
        int i = 0;
        for (T t : dll) {
            a[i++] = t;                                   
        }
    }

    public static void sortIntegers(Integer[] arr) {
        dllInsertionSort(arr, Integer::compareTo);
    }


    public static void sortDoubles(Double[] arr) {
        dllInsertionSort(arr, Double::compareTo);
    }

    public static void main(String[] args) {
        System.out.println("Testing dllInsertionSort on Integer array");
        TestGenericSort.testSortingAlg(SortDLL::sortIntegers);

        System.out.println("Testing Genericsorting on Integer array");
        TestGenericSort.testSortingAlg(GenericSorting::insertionSort);

        System.out.println();

        Double[] sample = {0.1, -0.2, 0.38, 0.009};
        System.out.println("Time for small values: ");
        double tDll = TestGenericSort.measureTime(SortDLL::sortDoubles, sample);
        double tIns = TestGenericSort.measureTime(GenericSorting::insertionSort, sample);
        System.out.println("dllInsertionSort: " + tDll + " s");
        System.out.println("insertionSort: " + tIns + " s");

        System.out.println();

        int n = 10000;
        System.out.println("Time for " + n + " Integer elements: ");
        double avgDll = TestGenericSort.measureTimeInts(SortDLL::sortIntegers, n);
        double avgIns = TestGenericSort.measureTimeInts(GenericSorting::insertionSort, n);
        System.out.println("Time for dllInsertionSort for n =" + n + ": " + avgDll + " s");
        System.out.println("Time for insertionSort for n =" + n + ": " + avgIns + " s");
    }
}
