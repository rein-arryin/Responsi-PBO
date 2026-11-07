package TurnBasedGame.characters;

import TurnBasedGame.interfaces.Action;

import java.util.Scanner;

// subclass dari class abstrak charGame
public class Enemy extends charGame implements Action{
    private int moveCount; // total turn

    // konstruktor boss
    public Enemy(String name){
        super(name, 200,20);
    }

    // konstruktor overload boss
    public Enemy(String name, int maxHp, int baseAttack){
        super(name, maxHp, baseAttack);
    }

    // implement dari action
    @Override
    public String turn(charGame target, Scanner scanner){
        int dmg = getBaseAttack();
        target.takeDamage(dmg);
        return getName() + " attacks " + target.getName() + " for " + dmg + "damage!";
    }

    // meng override method abstrak displayInfo dengan menambahkan tipe musuh
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Enemy Type: Boss");
    }

    // mengoveride method abstract specialAction dengan menambahkan voice line
    @Override
    public String specialAction(){
        if(!Alive()){
            return getName() + ": Witless Tarnished.. Why covet Destined Death? To kill what?";
        } else if (getHp() <= getMaxHp() / 2){
            moveCount++;
            return getName() + ":O, Death. Become my blade, once more!";
        } else {
            moveCount++;
            return getName() + ":Thou who approacheth destined death, I will not have it stole from me again";
        }
    }
    // mengambil jumlah moveCount yang telah dilakukan
   public int getBattleCount(){
        return this.moveCount;
   }
}


