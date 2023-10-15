package assignment1;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Game> myGames;


    public Inventory(){
        myGames = new ArrayList<>();
    }

    /**
     * Check for duplicate and only add if a duplicate does not exist
     * @param game
     */
    public void addGame(Game game){
        for(Game g : myGames){
            if(g.getSteamID().equals(game.getSteamID())){
                return;
            }
        }
        this.myGames.add(game);
    }
    public int getSize(){
        return this.myGames.size();
    }

    public void removeGame(Game game){
//        for(Game g: myGames){
//            if(g.getSteamID().equals(game.getSteamID())){
//                myGames.remove(g);
//            }
//        }
        for(int i =0; i< myGames.size(); i++){
            if(myGames.get(i).getSteamID().equals(game.getSteamID())){
                myGames.remove(i);
            }
        }
    }

    public Game findCheapestGame(){
        if(myGames.size() == 0){
            return null;
        }

        Game cheapest = myGames.get(0);
        for(Game game: myGames){
            if(game.getPrice() < cheapest.getPrice()){
                cheapest = game;
            }
        }
        return cheapest;
    }

    public Game findMostHighlyRatedGame(){
        if(myGames.size() == 0){
            return null;
        }
        Game best = myGames.get(0);
        for(int i = 0; i<myGames.size(); i++){
            if(Integer.parseInt(myGames.get(i).getRating()) > Integer.parseInt(best.getRating())){
                best = myGames.get(i);
            }
        }
        return best;
    }

    public Game findMostExpensiveGame(){
        if(myGames.size() == 0){
            return null;
        }
        Game expensive = myGames.get(0);
        for(Game game: myGames){
            if(game.getPrice() > expensive.getPrice()){
                expensive = game;
            }
        }
        return expensive;
    }
    public void printAveragePriceOfAllGames() {
        double total = 0;
        for (int i = 0; i < myGames.size(); i++) {
            total += myGames.get(i).getPrice();
        }
        double average =  total/myGames.size();
        System.out.println(average);
    }

}
