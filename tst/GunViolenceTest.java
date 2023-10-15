import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.TreeMap;

public class GunViolenceTest {
    public static final String GUN_URL = "https://www.gunviolencearchive.org/export-finished/download?uuid=1e1fc646-a270-420e-8b41-3f497794831f&filename=public%3A//export-1744b729-ac79-4495-9e86-ce013c8c1b5e.csv";

    @Test(timeout = 1000)
    public void testlast72Hours() throws Exception {
        String str = IOUtils.toString(new URL(GUN_URL), "UTF-8");
        CSVParser parser = new CSVParser(new StringReader(str),
                CSVFormat.DEFAULT.builder().setHeader().setIgnoreSurroundingSpaces(true).build());
        TreeMap<String, Integer> counter = new TreeMap<>();
        for(CSVRecord record: parser){
            String state = record.get("State");
            if(counter.containsKey(state)){
                int count = counter.get(state);
                counter.put(state, count+1);
            }else{
                counter.put(state, 1);
            }
            //System.out.println(counter);
        }
        System.out.println(counter);
        parser.close();
    }
    @Test
    public void testCSVLink() throws Exception{
        String rawLink = "https://gist.githubusercontent.com/tacksoo/d1fcb51f8921cdc90d1ffadb0b63b768/raw/6c9a8b9ffadd87b4bd0217b91cdd90bb9e227ef2/schedule.csv";
        String str = IOUtils.toString(new URL(rawLink), "UTF-8");
        CSVParser parser = new CSVParser(new StringReader(str), CSVFormat.DEFAULT.builder().setHeader().setIgnoreSurroundingSpaces(true).build());
        int lineCount = 0;

        for(CSVRecord record: parser){
            lineCount++;
        }
        Assert.assertEquals(28, lineCount);
    }
    @Test
    public void testLowestPrediction() throws Exception{
        String rawLink = "https://gist.githubusercontent.com/tacksoo/b9edbfc8c03e1ca89d459bf1af39842d/raw/75abf553a0297d9202b1a568f185f735055d6f81/stonks.csv";
        String str = IOUtils.toString(new URL(rawLink), "UTF-8");
        CSVParser parser = new CSVParser(new StringReader(str), CSVFormat.DEFAULT.builder().setHeader().setIgnoreSurroundingSpaces(true).build());
        double lowestPrediction = Double.MAX_VALUE;
        for(CSVRecord record: parser) {
            String pre = record.get("Prediction");
            pre = pre.replace("%", "");
            double prediction = Double.parseDouble(pre);
            if(prediction < lowestPrediction){
                lowestPrediction = prediction;
            }
        }
        System.out.println("Lowest Prediction: "+lowestPrediction);
        Assert.assertEquals("Lowest Prediction: ",-1.882, lowestPrediction,10 );
    }
}
