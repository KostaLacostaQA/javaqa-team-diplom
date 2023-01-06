package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfMoreGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл 2 Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 3);
        player.play(game1, 2);

        int expected = 5;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shoutRefreshTimeGamePlayerTest() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.play(game, 2);

        int expected = 5;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shoutThrowRuntimeExceptionIfNoGameTest() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(NotFoundException.class, () ->
        {
            player.play(game, 3);
        });
    }

    @Test
    public void installGameTest() {
        GameStore store = new GameStore();
        Player player = new Player("Petya");
        Game actualGame = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game expectedGame = player.installGame(actualGame);

        assertEquals(expectedGame, actualGame);

        //Повторное добавление игры игроку

        actualGame = player.installGame(actualGame);
        expectedGame = null;
        assertEquals(expectedGame, actualGame);
    }

    @Test
    public void mostPlayerByGenreTest() {

        //Если установленны 2 игры разного жанра

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Шахматы Онлайн", "Казуальные");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        player.installGame(game1);
        player.play(game1, 2);

        player.mostPlayerByGenre("Аркады");
        Game expectedGenre = game;
        Game actualGenre = player.mostPlayerByGenre("Аркады");
        assertEquals(expectedGenre, actualGenre);

        //если игры в данном жанре не играли

        Game game3 = store.publishGame("Нетология Гонки Онлайн", "Гонки");
        player.installGame(game3);
        player.play(game3, 1);

        expectedGenre = null;
        actualGenre = player.mostPlayerByGenre("Ужасы");
        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    public void playedTimeTest() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Petya");
        player.installGame(game);
        int actualHours = player.play(game, 3);
        int expectedHours = 3;
        assertEquals(expectedHours, actualHours);

        //Суммарное колличество часов

        actualHours = player.play(game, 7);
        expectedHours = 10;
        assertEquals(expectedHours, actualHours);

        // Отрицательные значения

        actualHours = player.play(game, -1);
        expectedHours = 10;
        assertEquals(expectedHours, actualHours);

        // Значение 0

        actualHours = player.play(game, 0);
        expectedHours = 10;
        assertEquals(expectedHours, actualHours);
    }
}
