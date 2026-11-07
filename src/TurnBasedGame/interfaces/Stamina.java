package TurnBasedGame.interfaces;

// Untuk Stamina karakter
public interface Stamina {
    int getStamina();
    void setStamina(int stamina);
    void useStamina(int total);
    void recoverStamina(int total);
}
