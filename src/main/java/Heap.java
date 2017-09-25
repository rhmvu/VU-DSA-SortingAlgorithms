/*By Ruben van der Ham and Kostas Moumtzakis
* VU Amsterdam CS2
* 25 September 2017*/

class Heap {

    static final int ROOT = 1;
    private int[] array;
    int heapSize;
    int length;

    Heap (int[] input) {
        array = new int[input.length+1];
        for (int i = 1; i<=input.length;i++) {
            array[i] = input[i-1];
        }
        length = input.length;
        heapSize = length;
    }

    int[] toArray() {
        int[] result = new int[length];
        for (int i = 0; i<length;i++) {
            result[i] = array[i+1];
        }
        return result;
    }

    void sort() {
        buildMaxHeap();
        for (int i = length; i>=2; i--) {
            exchange(ROOT,i);
            heapSize -= 1;
            maxHeapify(ROOT);
        }
    }

    void buildMaxHeap() {
        for (int i = (int) Math.floor((length)/2); i>= 1;i--) {
            maxHeapify(i);
        }
    }

    private void maxHeapify(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest;
        try {
            if (l <= heapSize && array[l] > array[i]) {
                largest = l;
            } else {
                largest = i;
            }
            if (r <= heapSize && array[r] > array[largest]) {
                largest = r;
            }
            if (largest != i){
                exchange(i,largest);
                maxHeapify(largest);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    private void exchange(int parent, int largest) {
        int temp = array[largest];
        array[largest]= array[parent];
        array[parent] = temp;
    }

    private int leftChild(int parent) {
        return 2*parent;
    }

    private int rightChild(int parent) {
        return 2*parent+1;
    }
}
