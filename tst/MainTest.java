import assignment1.Game;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class MainTest {

    //from https://steamdb.info

    private static String[] GAME_IDS = {"270880",  "434050",  "813780"};

    //"1113560",
    public static Game getGameFromSteam(String id){
        String url = "https://store.steampowered.com/api/appdetails?appids=";
        ObjectMapper mapper = new ObjectMapper();
        Game game = null;
        try{
            JsonNode root = mapper.readTree(new URL(url +id));
            String name = root.get(id).get("data").get("name").asText();
            String platform = root.get(id).get("data").get("platforms").get("windows").asText();
            int score = root.get(id).get("data").get("metacritic").get("score").asInt();
            String developer = root.get(id).get("data").get("developers").get(0).asText();
            String genre = root.get(id).get("data").get("genres").get(0).get("description").asText();
            String releaseDate = root.get(id).get("data").get("release_date").get("date").asText();
            int price = root.get(id).get("data").get("price_overview").get("final").asInt();
            game = new Game(id, "PC", name, releaseDate, developer,genre, price, score+"");


        }catch(IOException e){
            e.printStackTrace();
        }

        return game;
    }

    @Test
    public void testSteamGames(){
        for(int i = 0; i<GAME_IDS.length; i++){
            Game g = getGameFromSteam(GAME_IDS[i]);
            g.printGame();
        }
    }

    @Test
    public void testCheapSharkGame(){
        Game[] games  = new Game[3];

        //download json file of games and create objs based on it
        String url = "https://www.cheapshark.com/api/1.0/deals?storeID=1";
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode root = mapper.readTree(new URL(url));
            for (int i=0; i < root.size(); i++) {
                String title = root.get(i).get("title").asText();
                double salePrice = root.get(i).get("salePrice").asDouble();
                int criticScore = root.get(i).get("metacriticScore").asInt();
                System.out.println(title + ";" + salePrice + ";" + criticScore);
            }

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void testGetGameInfoFromSteam(){
        Game[] games = new Game[GAME_IDS.length];
        for(int i = 0; i< GAME_IDS.length; i++){
            Game g = new Game(GAME_IDS[i]);
            g.getGameInfoFromSteam();
            g.printGame();
        }

    }


}
