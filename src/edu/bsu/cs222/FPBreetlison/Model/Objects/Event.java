package edu.bsu.cs222.FPBreetlison.Model.Objects;

import edu.bsu.cs222.FPBreetlison.Model.GameData;

import java.lang.reflect.Array;
import java.util.*;

import static java.awt.SystemColor.info;

/**
 * Created by cacto on 4/16/2017.
 */
public class Event implements java.io.Serializable {

    private String name;
    private String type;
    private ArrayList<Item> stock;
    private ArrayList<String> enemyPool;
    private ArrayList<Item> itemPool;
    private String displayImagePath;

    private GameData gameData;

    public Event(String eventInfo,GameData gameData){
        this.gameData = gameData;
        List<String> info = stringParser(eventInfo);
        this.name = info.get(0);
        this.type = info.get(1);
        String stockString = info.get(2);
        this.stock = buildStock(stockString);
    }

    private List<String> stringParser(String info){
        return Arrays.asList(info.split(","));
    }

    private ArrayList<Item> buildStock(String stockString){
        List<String> stockList = Arrays.asList(stockString.split("/"));
        ArrayList<Item> builtStock = new ArrayList<>();
        for(int i = 0; i<stockList.size();i++){
            builtStock.add(gameData.getAllItems().get(stockList.get(i)));
            System.out.println(builtStock.get(i).getName());
        }
        return builtStock;
    }

    public void activate(GameData data){
        gameData = data;
        if(type.equals("fountain")){
            activateFountain();
        }
        else if(type.equals("item")){
            searchItem();
        }
        else if(type.equals("enemy")){
            generateEncounter();
        }
        else if(type.equals("shop")){
            openShop();
        }
    }

    private void openShop() {

    }

    private ArrayList<Fighter> generateEncounter() {
        Random random = new Random();
        int randomInt = random.nextInt(enemyPool.size());
        return enemyTeamBuilder(randomInt);

    }

    private ArrayList<Fighter> enemyTeamBuilder(int index) {
        ArrayList<Fighter> enemyTeam = new ArrayList<>();
        String enemies = enemyPool.get(index);
        List<String> splitEnemies = Arrays.asList(enemies.split(","));
        for(int i = 0;i<splitEnemies.size();i++){
            enemyTeam.add(new Fighter(gameData.getAllEnemies().get(splitEnemies.get(i))));
        }
        return enemyTeam;
    }

    private void searchItem() {
    }

    private void activateFountain() {
        ArrayList<Fighter> team = gameData.getTeam();
        HashMap<String,Fighter> allEnemies = gameData.getAllEnemies();
        for(int i = 0; i<team.size();i++){
            team.get(i).recoverHealth(-1);
        }
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public ArrayList<Item> getStock() {
        return stock;
    }
}