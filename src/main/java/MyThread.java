import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.io.*;
import java.util.*;

public class MyThread extends Thread {
    private String filePath;
    private final ListMultimap<String, String> maps;
    private WriteFile writeFile;

    public MyThread() {
        maps = ArrayListMultimap.create();
    }

    @Override
    public void run() {
        ArrayList<String[]> sortedString;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String t;
            ArrayList<String> headers = new ArrayList<>(Arrays.asList(reader.readLine().split(";")));
            sortedString = new ArrayList<>();
            synchronized (maps) {
                while ((t = reader.readLine()) != null) {
                    sortedString.add(t.split(";"));
                }

                for (int i = 0; i < headers.size(); i++) {
                    for (String[] strings : sortedString) {
                        maps.put(headers.get(i), strings[i]);
                    }
                }
            }
            writeFile = new WriteFile((ArrayListMultimap<String, String>) maps);
            writeFile.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFilePath(String path) {
        this.filePath = path;
    }
}
