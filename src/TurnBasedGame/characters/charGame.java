package TurnBasedGame.characters;

import TurnBasedGame.interfaces.Action;

public abstract class charGame implements Action {
    private final String name;  // nama karakter tidak bisa diubah
    private int hp;             // darah karakter
    private int maxHp;          // maksimal darah
    private int baseAttack;     // base damage attack

    //nilai HP bawaan jika tidak di terntukan
    public static final int DEFAULT_MAX_HP = 100;

    //aksi special (voice line) untuk setiap karakter
    public abstract String specialAction();

    // Konstruktor
    public charGame(String name, int maxHp, int baseAttack){
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.baseAttack = baseAttack;
    }

    // Menampilkan info karakter
    public void displayInfo(){
        System.out.println("Character Name: " + this.name);
        System.out.println("HP: " + this.hp + "/" + this.maxHp);
        System.out.println("Base Attack: " + this.baseAttack);
    }

    // Konstruktor overload
    public charGame(String name){
        this(name, DEFAULT_MAX_HP,10);
    }

    // Mengubah nilai Hp
    public void setHp(int hp){
        this.hp = hp;
        if (this.hp <= 0) this.hp = 0;
        if (this.hp > maxHp) this.hp = maxHp;
    }

    // Mengubah nilai MaxHP
    public void setMaxHp(int maxHp){
        if  (maxHp > 0) {
            this.maxHp = maxHp;
            if (this.hp > maxHp){ this.hp = maxHp; }
        }
    }

    // mengubah kekuatan base attack
    public void setBaseAttack(int baseAttack){
        if (baseAttack >= 0){ this.baseAttack = baseAttack; }
    }

    // mengurangi hp karakter sebesar damage yang ditrtima
    public void takeDamage(int dmg){
        setHp(this.hp - dmg);
    }

    // mengecek apakah karakter masih hidup atau tidak
    public boolean Alive(){
        return this.hp > 0;
    }

    // mengambil nama karakter
    public String getName(){
        return name;
    }

    // mengambil HP karakter
    public int getHp(){
        return hp;
    }

    // mengambil HP maksimal karakter
    public int getMaxHp(){
        return maxHp;
    }

    // mengambil kekuatan base attack karakter
    public int getBaseAttack(){
        return baseAttack;
    }
}
