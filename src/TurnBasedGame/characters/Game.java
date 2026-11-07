package TurnBasedGame.characters;

import TurnBasedGame.interfaces.Action;
import java.util.Scanner;

public class Game {
    private Player player;      // karakter yang di mainkan pemain
    private Enemy enemy;        // boss yang dilawan
    private Scanner scanner;    // membaca input yang dikirim pemain

    // konstruktor untuk menyiapkan scanner, pemain dan boss
    public Game(){
        scanner = new Scanner(System.in);
        player = new Player("Tarnish");
        enemy = new Enemy("Malekith");
    }

    // untik membersihkan layar terminal agar tidak numpuk
    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // alur dari permainan berulang jika pemain dan boss masih hidup
    public void start(){
        // polimorfisme interface
        Action playerAction = player;
        Action enemyAction = enemy;

        // log untuk menampung kejadian di satu putaran untuk ditampilkan di layar berikutnya
        StringBuilder log = new StringBuilder();

        while (player.Alive() && enemy.Alive()){
            // ENEMY TURN : boss berbicara voice line lalu menyerang
            log.append("===== ENEMY TURN =====\n");
            log.append(enemy.specialAction()).append("\n");
            log.append(enemyAction.turn(player, scanner)).append("\n\n");

            // jika player kalah, memcatat dalog kekalahan lalu menghentikan pertarungan
            if (!player.Alive()){
                log.append(player.specialAction()).append("\n");
                break;
            }

            // membuat layar baru dan membersihkan aksi, hasil akan di catat di log
            clearScreen();
            showStatus();
            System.out.println(log);
            log.setLength(0);

            // PLAYER TURN player memilih aksi lalu hasil nya dicatat di log
            System.out.println("===== PLAYER TURN =====");
            String result = playerAction.turn(enemy, scanner);
            log.append("===== PLAYER TURN =====\n");
            log.append(player.specialAction()).append("\n");
            log.append(result).append("\n\n");

            // jika boss kalah maka log akan dicatat dan pertarungan akan di hentikan
            if (!enemy.Alive()){
                log.append(enemy.specialAction()).append("\n");
                break;
            }
        }

        // layar terakhir menampilkan status akhir,log akhir dan hasil dari pertarungan
        clearScreen();
        showStatus();
        System.out.println(log);
        showResults();
    }

    // menampilkan hp, stamina, dan hp dari pemain dan boss
    private void showStatus(){
        System.out.println("========== STATUS ==========");
        System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Player Stamina: " + player.getStamina());
        System.out.println(enemy.getName() + " HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
        System.out.println("============================\n");
    }

    // menampilkan hasil pemain menang atau kalah beserta total move yang telah dilakukan
    private void showResults(){
        System.out.println("========== BATTLE RESULTS ==========");
        System.out.println(player.Alive() ? "YOU WIN!" : "YOU LOSE!");
        System.out.println("\n========== BATTLE CRY STATISTICS ==========");
        System.out.println(player.getName() + " Battle Cry: " + player.getBattleCount());
        System.out.println(enemy.getName() + " Battle Cry: " + enemy.getBattleCount());
    }
}