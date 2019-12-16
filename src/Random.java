public class Random {
    public static void main(String[] args) {
        int i = 0;
        while(true) {
            System.out.println(Math.random());
            if(++i == 1000) break;
        }
    }


}
