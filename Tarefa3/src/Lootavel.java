// representa qualquer personagem que pode deixar um drop (loot) ao ser derrotado
public interface Lootavel {
    Item droparLoot();
    // Pode retornar null se nada for dropado.
    
}