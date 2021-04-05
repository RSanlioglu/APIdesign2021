package dataJSON.sorting;

abstract class SortingAlgorithm {
    /**
     * A simple Merge Sort algorithm used to sort data.
     * The algorithm is obtained from the course ITF20006-1 21V Algorithms and datastructures
     * and modified for the use of this framework
     * @param intToBeSorted - An array of integers that will be sorted (the core data for sorting)
     * @param first - The index for the first value (usually 0)
     * @param last - The index for the last value.
     */
    public static void mergeSortINT(int[] intToBeSorted, int first, int last) {
        if(first==last) {
            return;
        }

        int[] temp;
        int i, left, right;
        int size = last - first + 1;
        int mid = (first + last) / 2;

        temp = new int[size];

        mergeSortINT(intToBeSorted, first, mid);
        mergeSortINT(intToBeSorted, mid + 1, last);

        for(i = 0; i < size; i++) {
            temp[i] = intToBeSorted[first + i];
        }

        left = 0;
        right = mid - first + 1;
        for(i = 0; i < size; i++) {
            if(right <= last - first) {
                if(left <= mid - first) {
                    if(temp[left] > temp[right]) {
                        intToBeSorted[i + first] = temp[right++];
                    } else {
                        intToBeSorted[i + first] = temp[left++];
                    }
                } else {
                    intToBeSorted[i + first] = temp[right++];
                }
            } else {
                intToBeSorted[i + first] = temp[left++];
            }
        }
    }

    /**
     * A simple Merge Sort algorithm used to sort data.
     * The algorithm is obtained from the course ITF20006-1 21V Algorithms and datastructures
     * and modified for the use of this framework
     * @param doubleToBeSorted - An array of doubles that will be sorted (the core data for sorting)
     * @param first  The index for the first value (usually 0)
     * @param last - The index for the last value
     */
    public static void mergeSortDouble(double[] doubleToBeSorted, int first, int last) {
        if(first==last) {
            return;
        }

        double[] temp;
        int i, left, right;
        int size = last - first + 1;
        int mid = (first + last) / 2;

        temp = new double[size];

        mergeSortDouble(doubleToBeSorted, first, mid);
        mergeSortDouble(doubleToBeSorted, mid+1, last);

        for(i = 0; i < size; i++) {
            temp[i] = doubleToBeSorted[first + i];
        }

        left = 0;
        right = mid - first + 1;
        for(i = 0; i < size; i++) {
            if(right <= last - first) {
                if(left <= mid - first) {
                    if(temp[left] > temp[right]) {
                        doubleToBeSorted[i + first] = temp[right++];
                    } else {
                        doubleToBeSorted[i + first] = temp[left++];
                    }
                } else {
                    doubleToBeSorted[i + first] = temp[right++];
                }
            } else {
                doubleToBeSorted[i + first] = temp[left++];
            }
        }
    }

    public static void bstSortString(String[] stringToBeSorted) {
        BinarySearchTree bst = new BinarySearchTree();
        for(String s : stringToBeSorted) {
            bst.insert(s);
        }
        bst.inorder();
    }

}
