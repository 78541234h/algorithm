public class 在有序但含空的数组中查找字符串 {
    public static void main(String[] args) {
        String[] strs = {null, "a", null, "a", null, "b", null, "c"};
        String[] finds = {"a", null, "d"};
        for (String f : finds) {
            System.out.println(findStrInArr(strs, f));
        }
    }

    public static int findStrInArr(String[] arr, String str) {
        if (arr == null || arr.length == 0 || str == null)
            return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == null) {
                int i = mid - 1;
                while (i >= left && arr[i--] == null) ;
                if (i < left || arr[i].compareTo(str) < 0)
                    left = mid + 1;
                else {
                    right = mid - 1;
                }
            } else if (arr[mid].compareTo(str) >= 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left < arr.length && arr[left] != null && arr[left].equals(str))
            return left;
        return -1;
    }
}
