package TurnBasedGame.characters;

import TurnBasedGame.interfaces.Action;
import TurnBasedGame.interfaces.Stamina;

import java.util.Scanner;

// subclass dari charGame dan mengimplementasi 2 interface Action dan Stamina
public class Player extends charGame implements Action, Stamina {
    private int stamina;    // stamina
    private int maxStamina; // total stamina
    private int moveCount; // total turn

    // konstruktor
    public Player(String name) {
        super(name, 120, 15);
        this.maxStamina = 50;
        this.stamina = 50;
    }

    // mengoveride method specialAction dengan menambahkan voice line
    @Override
    public String specialAction() {
        if (!Alive()) {
            return getName() + ": Arrggh...";
        } else {
            moveCount++;
            return getName() + ": ...";
        }
    }

    // mengoverride displayInfo dengan menambahkan Stamina dan maxStamina
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Stamina: " + this.stamina + "/" + this.maxStamina);
    }

    // Implement Stamina
    // mengambil stamina
    @Override
    public int getStamina() {
        return this.stamina;
    }

    // mengubah stamina
    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
        if (this.stamina < 0) this.stamina = 0;
        if (this.stamina > maxStamina) this.stamina = maxStamina;
    }

    // mengurangi stamina
    @Override
    public void useStamina(int total) {
        setStamina(this.stamina - total);
    }

    // memulihkan stamina
    @Override
    public void recoverStamina(int total) {
        setStamina(this.stamina + total);
    }

    // Implement Action
    // membuat menu, membaca pilihan dan mengembalikan teks untuk pemain
    @Override
    public String turn(charGame target, Scanner scanner) {
        System.out.println("Your Turn: Choose an action");
        System.out.println("1. [Basic Slash]");
        System.out.println("2. [Heavy Slash]");
        System.out.println("3. [Brace] Skip turn to recover 15HP and 20 Stamina");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("1")){
                int dmg = getBaseAttack();
                target.takeDamage(dmg);
                return getName() + " Slash " + target.getName() + " for " + dmg + "damage";
            } else if (input.equals("2")) {
                if (getStamina() >= 20) {
                    useStamina(20);
                    int dmg = getBaseAttack() + 15;
                    target.takeDamage(dmg);
                    return getName() + " HEAVY SLASH! " + target.getName() + " for " + dmg + "damage (-20 Stamina)";
                } System.out.println("Stamina is not enough!");
            } else if(input.equals("3")){
                setHp(getHp() + 25);
                recoverStamina(20);
                return getName() + " uses Brace +25 HP, +20 Stamina";
            } else {
                System.out.println("Invalid Choice!");
            }
        }
    }

    // mengambil total move yang telah dilakukan
    public int getBattleCount() {
        return this.moveCount;
    }

    // mengambil stamina maksimal
    public int getMaxStamina() {
        return this.maxStamina;
    }

    // mengubah stamina maksimal
    public void setMaxStamina(int maxStamina) {
        if (maxStamina > 0){
            this.maxStamina = maxStamina;
            if (this.stamina > maxStamina){
                this.stamina = maxStamina;
            }
        }
    }
}
