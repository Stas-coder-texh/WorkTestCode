import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Поток что читает из первого файла input1
        InputOneThread oneTherd = new InputOneThread();
        oneTherd.start();

        //Поток что читает из второго файла input2
        InputTwoTherd twoTherd = new InputTwoTherd();
        twoTherd.start();

        //Сон для того чтобы потоки успели прочитать данные
        Thread.sleep(10);
        //Вывод сгрупированых данных
        System.out.println(oneTherd.toString());
        System.out.println(twoTherd.toString());

        // Не до конца понял что требуется на выходе
        //Решил подстраховаться и сделать добавочную работу

        //В этом коде я создаю новые папки в которых будут храниться данные
        // с учётом расположения файла
        Thread.sleep(10);

        for (int i = 0; i < oneTherd.getIndex().size(); i++) {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/main/resources" + oneTherd.getPath().get(i) + ".txt"));
            dos.writeUTF( " version: " + oneTherd.getVersion().get(i) + "\n" +
                             "name: " + twoTherd.getNameAnimal().get(i) + "\n" +
                             "sex: " + twoTherd.getSex().get(i) + "\n");
        }
    }
}

    class InputOneThread extends Thread {
        private ArrayList<String> index;
        private ArrayList<String> version;
        private ArrayList<String> path;

        @Override
        public void run() {

            try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/input1.csv"), ';', '"', 1)) {
                String[] nextLine;
                index = new ArrayList<>();
                version = new ArrayList<>();
                path = new ArrayList<>();

                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine != null) {
                        index.add(nextLine[0]);
                        version.add(nextLine[1]);
                        path.add(nextLine[2]);
                    }
                }
                Thread.sleep(10);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            Set indexSet = new HashSet(index);
            Set versionSet = new HashSet(version);
            Set pathSet = new HashSet(path);
            return "InputOneTherd{" +
                    "id=" + indexSet +
                    ", version=" + versionSet +
                    ", path=" + pathSet +
                    '}';
        }

        public ArrayList<String> getIndex() {
            return index;
        }

        public ArrayList<String> getVersion() {
            return version;
        }

        public ArrayList<String> getPath() {
            return path;
        }

    }

    class InputTwoTherd extends Thread {
        private ArrayList<String> index;
        private ArrayList<String> name;
        private ArrayList<String> sex;

        @Override
        public void run() {
            index = new ArrayList<>();
            name = new ArrayList<>();
            sex = new ArrayList<>();

            try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/input2.csv"), ';', '"', 1)) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine != null) {
                        index.add(nextLine[0]);
                        name.add(nextLine[1]);
                        sex.add(nextLine[2]);
                    }
                }
                Thread.sleep(10);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            Set indexSet = new HashSet(index);
            Set nameSet = new HashSet(name);
            Set sexSet = new HashSet(sex);

            return "InputTwoTherd{" +
                    "id=" + indexSet +
                    ", name=" + nameSet +
                    ", sex=" + sexSet +
                    '}';
        }

        public ArrayList<String> getNameAnimal() {
            return name;
        }
        public ArrayList<String> getSex() {
            return sex;
        }
    }




