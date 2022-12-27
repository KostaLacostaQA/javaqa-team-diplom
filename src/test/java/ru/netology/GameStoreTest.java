package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }


    @Test
    public void publishGameTest() {
        GameStore store = new GameStore();
        Game expectedGame = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game actualGame = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertEquals(expectedGame, actualGame);

        //дублирование игры
        expectedGame = null;
        actualGame = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertEquals(expectedGame, actualGame);
    }

    //отсутствует метод для возврата времени игрока, невозможно выполнить тест
    @Test
    public void addPlayTimeTest() {
        GameStore store = new GameStore();
        //новый игрок
        store.addPlayTime("Константин", 10);

        //игрок есть в базе данных
        store.addPlayTime("Константин", 5);

        //недопустимость отрицательных часов и 0
        store.addPlayTime("Константин", -5);
        store.addPlayTime("Константин", 0);
    }

    @Test
    public void getMostPlayerTest() {
        GameStore store = new GameStore();
        //если игроков нет
        String expectedPlayer = null;
        assertEquals(expectedPlayer, store.getMostPlayer());

        //если есть игроки
        store.addPlayTime("Константин", 10);
        store.addPlayTime("Анна", 5);
        expectedPlayer = "Константин";
        assertEquals(expectedPlayer, store.getMostPlayer());
    }

    @Test
    public void getSumPlayedTimeTest() {
        GameStore store = new GameStore();
        //если игроков нет
        int expectedTime = 0;
        assertEquals(expectedTime, store.getSumPlayedTime());

        //если есть игроки
        store.addPlayTime("Константин", 10);
        store.addPlayTime("Анна", 5);
        expectedTime = 15;
        assertEquals(expectedTime, store.getSumPlayedTime());
    }
}
