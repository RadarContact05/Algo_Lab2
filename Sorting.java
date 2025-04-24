public class Sorting{

  public static void selectionSort(int[] a){
    for (int i = 0; i < a.length ; i++ ) {
      int minPosAfterI = minAfter(i, a);
      exch(i, minPosAfterI, a);
    }
  }

  private static int minAfter(int pos, int[] a){
    // find the position of the minimum element after position pos in a
    int minPos = pos;
    for (int j = pos + 1; j < a.length ; j++ ) {
      if(a[j] < a[minPos]){
        minPos = j;
      }
    }
    return minPos;
  }

  public static void insertionSort(int[] a){
    for (int i = 1; i < a.length ; i++) {
      percolate(i, a);
    }
  }

  public static void percolate(int pos, int[] a){
    int j = pos;
    while(j > 0 && a[j] < a[j-1]){
      exch(j, j-1, a);
      j--;
    }
  }

  public static void exch(int p1, int p2, int[] a){
    int swap = a[p1];
    a[p1]    = a[p2];
    a[p2]    = swap;
  }

  public static void println(int[] numarray){
    for (int i = 0; i < numarray.length ; i++) {
      System.out.print(numarray[i] + " ");
    }
    System.out.println();
  }

  /*
  The purpose of main is to demonstrate the methods defined in this class.
  */
  public static void main(String[] args) {
    int[] numbers = {9,1,8,2,7,3,6,4,5};
    selectionSort(numbers);
    System.out.println("\nSelection sorting [9,1,8,2,7,3,6,4,5] results in ");
    println(numbers);

    int[] numbers1 = {9,1,8,2,7,3,6,4,5};
    insertionSort(numbers1);
    System.out.println("\nInsertion sorting [9,1,8,2,7,3,6,4,5] results in ");
    println(numbers1);
  }
}
