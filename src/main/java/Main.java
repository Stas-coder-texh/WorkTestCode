import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] pathFile = new String[]{"src/main/resources/input1.csv", "src/main/resources/input2.csv"};

        ListMultimap<String, String> maps = ArrayListMultimap.create();
        MyThread threadRead = new MyThread();
        for (String path : pathFile) {
            threadRead.setFilePath(path);
            threadRead.run();
        }
    }
}