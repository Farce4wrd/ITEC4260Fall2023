package assignment1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

public class Store {
    private Inventory inventory;
    public static final String INVENTORY_URL = "https://gist.githubusercontent.com/tacksoo/b1d2da649f14840b598f6ce12ee2f68a/raw/d56aac3c0d2e6f70aa646a9a7077b901ba4e1bed/inventory.csv";
    public void loadInventoryFromWeb(String url){
        try{
            String str = IOUtils.toString(new URL(url).openStream(), "UTF-8");
            CSVParser parser = new CSVParser(new StringReader(str), CSVFormat.DEFAULT.builder().setIgnoreSurroundingSpaces(true).setHeader().build());
            for(CSVRecord record : parser){
                String steamid = record.get("id");
                String name = record.get("game name");
                //System.out.println(steamid);
                //System.out.println(name);
                Game g = new Game(steamid);
                g.getGameInfoFromSteam();
                inventory.addGame(g);

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Inventory getInventory(){
        return this.inventory;
    }
    public Store(String str){
        this.inventory = new Inventory();
        loadInventoryFromWeb(str);
    }

    public static double calculateTrajectoryOfRocket(double weight){

        return 0.0;
    }
}
