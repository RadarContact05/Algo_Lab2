import java.util.Comparator;
import java.util.function.Consumer;

public class SortDLL {

    public static <T> void dllInsertionSort(T[] a, Comparator<T> cmp) {
        DoublyLinkedList<T> dll = new DoublyLinkedList<>();
        for (T t : a) dll.addAtFirstSmaller(t, cmp);
        int i = 0;
        for (T t : dll) a[i++] = t;
    }

    public static void main(String[] args) {
        // 1) Korrekthetstest på Integer[]
        Consumer<Integer[]> dllIntSort = arr -> dllInsertionSort(arr, Integer::compareTo);
        System.out.println("Testar dllInsertionSort på Integer[]:");
        TestGenericSort.testSortingAlg(dllIntSort);
        System.out.println("Testar GenericSorting.insertionSort på Integer[]:");
        TestGenericSort.testSortingAlg(GenericSorting::insertionSort);

        // 2) Tidmätning på liten Double[]
        Consumer<Double[]> dllDoubleSort = arr -> dllInsertionSort(arr, Double::compareTo);
        Double[] sample = {0.1, -0.2, 0.38, 0.009};
        double tDll = TestGenericSort.measureTime(dllDoubleSort, sample);
        double tIns = TestGenericSort.measureTime(GenericSorting::insertionSort, sample);
        System.out.printf("dllInsertionSort small Double[]: %.6f s%n", tDll);
        System.out.printf("insertionSort small Double[]: %.6f s%n", tIns);

        // 3) Genomsnittlig tid på Integer[] storlek 10000
        int n = 10000;
        double avgDll = TestGenericSort.measureTimeInts(dllIntSort, n);
        double avgIns = TestGenericSort.measureTimeInts(GenericSorting::insertionSort, n);
        System.out.println("Avg dllInsertionSort for value: " + n + " = " + avgDll);
        System.out.println("Avg insertionSort for value: " + n + " = " + avgIns);
    }
}
