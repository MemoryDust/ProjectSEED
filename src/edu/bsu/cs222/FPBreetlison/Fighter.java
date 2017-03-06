package edu.bsu.cs222.FPBreetlison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fighter {

    private String name;
    private int hp;
    private int maxHP;
    private int attack;
    private int defense;
    private int enAttack;
    private int enDefense;
    private int agility;
    private int tpCost;

    private ArrayList<Skill> skills;

    public Fighter(String info){

        skills = new ArrayList<Skill>();
        List<String> characterInfo = stringParser(info);

        this.name = characterInfo.get(0);
        this.maxHP = Integer.parseInt(characterInfo.get(1));
        this.hp = maxHP;
        this.attack = Integer.parseInt(characterInfo.get(2));
        this.defense = Integer.parseInt(characterInfo.get(3));
        this.enAttack = Integer.parseInt(characterInfo.get(4));
        this.enDefense = Integer.parseInt(characterInfo.get(5));
        this.agility = Integer.parseInt(characterInfo.get(6));
        this.tpCost = Integer.parseInt(characterInfo.get(7));
    }

    //region In-Battle Functionality

    public void doBasicAttack(Fighter target){
       DamageCalculator damageCalculator = new DamageCalculator(this, target);
       target.takeDamage(damageCalculator.calculateDamage());
    }

    //endregion

    //region Reaction Functionality

    public void takeDamage(int damage){
        hp -=damage;
        if(hp < 0){
            hp = 0;
        }
    }

    public void recoverHealth(int heal){
        hp +=heal;
        if(hp > maxHP){
            hp = maxHP;
        }
    }

    public void sufferEffect(){

    }

    //endregion

    //region Text-Related Functionality

    public String generateAttackDescription(){
        //Expand this to include randomized descriptions
        return name + " struck the enemy!";
    }

    private List<String> stringParser(String info){

        List<String> attributes = Arrays.asList(info.split(","));
        return attributes;
    }

    //endregion

    public void learnSkill(Skill skill){
        skills.add(skill);
    }

    //region Setters and Getters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getEnAttack() {
        return enAttack;
    }
    public void setEnAttack(int enAttack) {
        this.enAttack = enAttack;
    }
    public int getEnDefense() {
        return enDefense;
    }
    public void setEnDefense(int enDefense) {
        this.enDefense = enDefense;
    }
    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public int getTpCost() {
        return tpCost;
    }
    public void setTpCost(int tpCost) {
        this.tpCost = tpCost;
    }
    public ArrayList<Skill> getSkills(){
        return skills;
    }
    //endregion

}