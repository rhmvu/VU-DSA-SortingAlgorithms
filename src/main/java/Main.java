import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements DS1Interface  {

    int[] array;

    /* Implement these methods */

    @Override
    public int[] insertionSort(int[] input) {
        array = new int[input.length];
        array = input;

        int i;
        int j;
        int key;
        for (j = 1; j < array.length; j++) {
            i = j - 1;
            key = array[j];

            while (i >= 0 && array[i] > key) {

                array[i+1] = array[i];
                i = i-1;
            }
            array[i+1] = key;
        }
        return array;
    }



    @Override
    public int[] mergeSort(int[] input) {
        return mergeSortImplementation(0,input.length);
    }


    private int[] mergeSortImplementation(int p, int r){
        if(p<r){
            int middleOfArray = (int) ((p+(r-p/2))+0.5);
            mergeSortImplementation(p,middleOfArray);
            mergeSortImplementation(middleOfArray+1,r);
            merge(p,middleOfArray,r);

        }
        return array;
    }

    private void merge(int p, int q, int r){
        int[] temp = new int[array.length];
        int tempIndex = 0;
        int rightArrayIndex = q+1;
        int leftArrayIndex = p;
        while(leftArrayIndex <=q ||rightArrayIndex <=r){
            if (array[leftArrayIndex]<= array[rightArrayIndex]) {
                temp[tempIndex] =  array[leftArrayIndex];
                leftArrayIndex+=1;
            }else{
                temp[tempIndex] = array[rightArrayIndex];
                rightArrayIndex+=1;
            }
            tempIndex+=1;
        }
    }
    @Override
    public int[] heapSort(int[] input) {
        return new int[0];
    }




    /* BEGIN UTIL FUNCTION. DO NOT TOUCH */

    int[] readInput(String file) throws FileNotFoundException {

        InputStream inputStream;
        if (file == null) {
            inputStream = System.in;
        } else {
            inputStream = new FileInputStream(file);
        }
        Scanner in = new Scanner(inputStream);

        List<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            int e = in.nextInt();
            list.add(e);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    void start(String algorithm, String file) throws FileNotFoundException {
        int[] toBeSorted = readInput(file);
        int[] sortedResult = null;
        switch (algorithm) {
            case "insertion":
                sortedResult = insertionSort(toBeSorted);
                break;
            case "merge":
                sortedResult = mergeSort(toBeSorted);
                break;
            case "heap":
                sortedResult = heapSort(toBeSorted);
                break;
            default:
                System.out.printf("Invalid algorithm provided: %s\n", algorithm);
                printHelp(null);
                System.exit(1);
        }

        printArray(sortedResult);
    }

    static void printArray(int[] array) {
        for (int e: array) {
            System.out.printf("%d ", e);
        }
    }

    static void printHelp(String[] argv) {
        System.out.printf("Usage: java -jar DS1.jar <algorithm> [<input_file>]\n");
        System.out.printf("\t<algorithm>: insertion, merge, heap\n");
        System.out.printf("E.g.: java -jar DS1.jar insertion example.txt\n");
    }

    public static void main(String argv[]) {
        if (argv.length == 0) {
            printHelp(argv);
        }
        try {
            (new Main()).start(argv[0], argv.length < 2 ? null : argv[1]);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getMessage());
        }

    }

}
