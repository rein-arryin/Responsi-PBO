package TurnBasedGame;

import TurnBasedGame.interfaces.*;
import TurnBasedGame.characters.*;
import java.lang.reflect.Modifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== DEMO PERSYARATAN ===");
        System.out.println("1. Jalankan pengujian 10 persyaratan");
        System.out.println("2. Main game");
        System.out.println("Pilih: ");
        String pilih = scanner.nextLine().trim();

        // Memilih mode Main Game atau Jalankan Test
        if (pilih.equals("1")) {
            runTests();
        } else {
            new Game().start();
        }
    }

    // Untuk mengetest 10 kriteria
    private static void runTests() {
        Enemy boss = new Enemy("Maliketh");
        Enemy minion = new Enemy("Godrick", 80, 10);
        Player player = new Player("Tarnish");

        System.out.println("\n[1] State dan Behavior");
        System.out.println("State awal HP " + player.getName() + ": " + player.getHp());
        player.takeDamage(30);
        System.out.println("Setelah takeDamage(30): " + player.getHp());
        System.out.println("Masih hidup? " + player.Alive());

        System.out.println("\n[2] Immutability");
        System.out.println("Konstanta DEFAULT_MAX_HP = " + charGame.DEFAULT_MAX_HP);
        System.out.println("Atribut final name = " + player.getName() + " (tidak ada setter)");

        System.out.println("\n[3] Enkapsulasi dan Overloading");
        System.out.println("Boss     -> HP " + boss.getMaxHp() + ", ATK " + boss.getBaseAttack());
        System.out.println("Minion   -> HP " + minion.getMaxHp() + ", ATK " + minion.getBaseAttack());
        player.setBaseAttack(25);
        player.setMaxStamina(60);
        System.out.println("Setter: ATK player = " + player.getBaseAttack()
                + ", maxStamina = " + player.getMaxStamina());

        System.out.println("\n[4] Inheritance");
        System.out.println("Player extends charGame? " + (player instanceof charGame));
        System.out.println("Enemy extends charGame?  " + (boss instanceof charGame));
        System.out.println("Main dipisah dari class lain: ya (file Main.java)");

        System.out.println("\n[5] Super Chaining");
        System.out.println("super(name, maxHp, baseAttack) dipakai di konstruktor Player dan Enemy.");
        System.out.println("Memanggil displayInfo() yang di dalamnya ada super.displayInfo():");
        player.displayInfo();
        boss.displayInfo();

        System.out.println("\n[6] Method Overriding");
        System.out.println("displayInfo(), turn(), dan specialAction() di-override dengan @Override.");
        System.out.println("Hasil turn() milik Enemy: " + boss.turn(player, null));

        System.out.println("\n[7] Abstract Class");
        System.out.println("charGame abstract? " + Modifier.isAbstract(charGame.class.getModifiers()));

        System.out.println("\n[8] Logika State Percabangan");
        Enemy uji = new Enemy("Boss Uji");
        System.out.println("HP penuh        : " + uji.specialAction());
        uji.takeDamage(120);
        System.out.println("HP di bawah 50% : " + uji.specialAction());
        uji.takeDamage(200);
        System.out.println("HP habis        : " + uji.specialAction());
        System.out.println("battleCount berubah menjadi: " + uji.getBattleCount());

        System.out.println("\n[9] Polimorfisme Interface");
        Action aksi = new Enemy("Dummy");                    // variabel bertipe interface
        System.out.println(aksi.turn(player, null));

        System.out.println("\n[10] Multiple Interface");
        System.out.println("Player implements Action?  " + (player instanceof Action));
        System.out.println("Player implements Stamina? " + (player instanceof Stamina));
        Stamina st = player;
        st.useStamina(20);
        System.out.println("Stamina setelah dipakai 20: " + st.getStamina());
        st.recoverStamina(10);
        System.out.println("Stamina setelah pulih 10  : " + st.getStamina());
    }
}
