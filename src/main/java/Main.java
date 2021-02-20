import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String[]> inputOneList = null;
        List<String[]> inputTwoList = null;
       try(CSVReader readIn1 = new CSVReader(new FileReader("src/main/resources/input1.csv"),';','"',1);
           CSVReader readIn2 = new CSVReader(new FileReader("src/main/resources/input2.csv"),';','"',1)){
            inputOneList = readIn1.readAll();
            inputTwoList = readIn2.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String[] row : inputOneList){
            System.out.println(Arrays.toString(row));
        }
        for(String[] row : inputTwoList){
            System.out.println(Arrays.toString(row));
        }

//        String csv = "data.csv";
//        CSVWriter writer = new CSVWriter(new FileWriter("/resources/test1.csv"));
//        //Create record
//        String [] record = "4,David,Miller,Australia,30".split(",");
//        //Write the record to file
//        writer.writeNext(record);
//        //close the writer
//        writer.close();
    }
}

