package TurnBasedGame.interfaces;

import TurnBasedGame.characters.charGame;
import java.util.Scanner;

// Untuk aksi karakter
public interface Action {
    String turn(charGame target, Scanner scanner);
}