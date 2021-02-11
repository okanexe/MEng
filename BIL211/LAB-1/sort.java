public class sort
{
    public static void main(String[] args) {

        int[] arr = {9, 6, 8, 5, 8, 2, 3};

        //int[] arr = {99, 1, 2, 3, 4, 5};
        /*
        2 9 6 8 5 8 3
        2 3 9 6 8 5 8
        2 3 5 9 6 8 8
        2 3 5 6 9 8 8
        2 3 5 6 8 9 8
        2 3 5 6 8 8 9
        2 3 5 6 8 8 9
        */

        sort(arr);

    }

    public static int[] sortArrayToStartEnd(int[] dizi, int start, int end){
        for(int i=end; i>start;i--){
            dizi[i]=dizi[i-1];
        }
        return dizi;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.print("\n");
    }

    static int index=0;

    public static int[] sort(int[] arr){

        if( index == arr.length){
            index = 0;
            return arr;
        }

        int min = arr[index];
        int gap = 0;

        for (int i = index; i < arr.length; i++){
            if (min>arr[i]){
                min = arr[i];
                gap = i;
            }
        }

        arr = sortArrayToStartEnd(arr, index, gap);

        arr[index] = min;
        index = index + 1;

        printArray(arr);

        return sort(arr);
}
}
