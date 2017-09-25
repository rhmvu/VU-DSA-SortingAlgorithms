class Heap {

    static final int ROOT = 0;
    private int[] array;
    int heapSize;
    int length;

    Heap(int[] input){
        array = input;
        length = array.length;
        heapSize = length;
    }


    int[] toArray(){
        return array;
    }

    void sort(){
        buildMaxHeap();
        for(int i = array.length-1; i>=2; i--) {
            exchange(ROOT,i);
            heapSize -= 1;
            maxHeapify(ROOT);
        }
    }

    void buildMaxHeap(){
        for (int i = (int) Math.floor(length/2); i> 1;i--){
            maxHeapify(i);
        }
    }

    private void maxHeapify(int i){
        int l = leftChild(i);
        int r = rightChild(i);
        int largest;
        try {
            if (i < heapSize && array[l] > array[i]) {
                largest = l;
            } else {
                largest = i;
            }
            if (r < heapSize && array[r] > array[largest]) {
                largest = r;
            }
            if(largest != i){
                exchange(i,largest);
                maxHeapify(largest);
            }
        }catch (ArrayIndexOutOfBoundsException e){
        }


    }

    private void exchange(int parent, int largest){
        int temp = array[largest];
        array[largest]= array[parent];
        array[parent] = temp;
    }

    private int leftChild(int parent){
        return 2*parent;
    }

    private int rightChild(int parent){
        return 2*parent+1;
    }
}
