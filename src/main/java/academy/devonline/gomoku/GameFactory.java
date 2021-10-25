/*
 * Copyright (c) 2019. http://devonline.academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package academy.devonline.gomoku;

import academy.devonline.gomoku.component.CellVerifier;
import academy.devonline.gomoku.component.ComputerMove;
import academy.devonline.gomoku.component.DataPrinter;
import academy.devonline.gomoku.component.Game;
import academy.devonline.gomoku.component.GameOverHandler;
import academy.devonline.gomoku.component.UserInputReader;
import academy.devonline.gomoku.component.UserMove;
import academy.devonline.gomoku.component.WinnerVerifier;
import academy.devonline.gomoku.component.config.CommandLineArgumentParser;
import academy.devonline.gomoku.component.console.CellNumberConverter;
import academy.devonline.gomoku.component.console.ConsoleDataPrinter;
import academy.devonline.gomoku.component.console.ConsoleGameOverHandler;
import academy.devonline.gomoku.component.console.ConsoleUserInputReader;
import academy.devonline.gomoku.component.console.keypad.DesktopNumericKeypadCellNumberConverter;
import academy.devonline.gomoku.component.swing.GameWindow;
import academy.devonline.gomoku.model.config.Level;
import academy.devonline.gomoku.model.config.PlayerType;
import academy.devonline.gomoku.model.config.UserInterface;
import academy.devonline.gomoku.model.game.Player;

import static academy.devonline.gomoku.model.config.PlayerType.USER;
import static academy.devonline.gomoku.model.config.UserInterface.GUI;
import static academy.devonline.gomoku.model.game.Sign.O;
import static academy.devonline.gomoku.model.game.Sign.X;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    private final UserInterface userInterface;

    private final Level level;

    public GameFactory(final String[] args) {
        final CommandLineArgumentParser.CommandLineArguments commandLineArguments =
                new CommandLineArgumentParser(args).parse();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();
        userInterface = commandLineArguments.getUserInterface();
        level = commandLineArguments.getLevel();
    }

    public Game create() {
        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        final GameOverHandler gameOverHandler;
        if (userInterface == GUI) {
            final GameWindow gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
            dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
            userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler(dataPrinter);
        }
        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        } else {
            player1 = new Player(X, new ComputerMove(level.getStrategies()));
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove(level.getStrategies()));
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                dataPrinter,
                player1,
                player2,
                new WinnerVerifier(),
                new CellVerifier(),
                gameOverHandler,
                canSecondPlayerMakeFirstMove
        );
    }
}