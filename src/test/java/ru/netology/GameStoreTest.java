package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameStoreTest {

    //добавление игры в каталог (первоначальный тест)
    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    //проверка игра в каталоге или нет
    @Test
    public void containsGameTest() {

        GameStore store1 = new GameStore();
        GameStore store2 = new GameStore();
        Game game1 = store1.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store2.publishGame("Нетология Баттл Онлайн 2", "Аркады");

        //игра есть в первом каталоге
        assertTrue(store1.containsGame(game1));

        //игры нет в первом каталоге
        assertTrue(!store1.containsGame(game2));
    }

    //добавление игры в каталог
    @Test
    public void publishGameTest() {
        GameStore store = new GameStore();
        //игры еще нет в каталоге
        Game expectedGame = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game actualGame = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertEquals(expectedGame, actualGame);

        //дублирование игры
        expectedGame = null;
        actualGame = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertEquals(expectedGame, actualGame);
    }

    //добавление времени игроку
    @Test
    public void addPlayTimeTest() {
        GameStore store = new GameStore();
        //новый игрок
        store.addPlayTime("Константин", 10);
        Map<String, Integer> playedTime = store.getPlayedTime();
        int expectedHours = 10;
        int actualHours = playedTime.get("Константин");
        assertEquals(expectedHours, actualHours);

        //игрок уже есть в базе данных
        store.addPlayTime("Константин", 5);
        expectedHours = 15;
        actualHours = playedTime.get("Константин");
        assertEquals(expectedHours, actualHours);

        //недопустимость отрицательных часов и 0
        store.addPlayTime("Константин", -1);
        expectedHours = 15;
        actualHours = playedTime.get("Константин");
        assertEquals(expectedHours, actualHours);

        store.addPlayTime("Константин", 0);
        expectedHours = 15;
        actualHours = playedTime.get("Константин");
        assertEquals(expectedHours, actualHours);
    }

    //вывод имени игрока с наибольшим игровым временем
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

    //вывод суммарного времени игроков
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
