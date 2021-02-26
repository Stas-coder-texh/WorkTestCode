import com.google.common.collect.ArrayListMultimap;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WriteFile {
    private ArrayListMultimap<String, String> map;

    public WriteFile(ArrayListMultimap<String, String> map) {
        this.map = map;
    }

    public void write() {
        for (String key : map.keys()) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources/answer" + key + ".txt"))) {
                Set<String> set = new HashSet(map.get(key));
                dos.writeUTF(key + "\n" + set);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

