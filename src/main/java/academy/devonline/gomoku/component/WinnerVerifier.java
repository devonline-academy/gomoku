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

package academy.devonline.gomoku.component;

import academy.devonline.gomoku.model.game.Cell;
import academy.devonline.gomoku.model.game.GameTable;
import academy.devonline.gomoku.model.game.Player;
import academy.devonline.gomoku.model.game.Sign;

import static academy.devonline.gomoku.Constants.WIN_COMBINATION_SIZE;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class WinnerVerifier {

    public boolean isWinner(final GameTable gameTable, final Player player) {
        return isWinnerByRows(gameTable, player.getSign()) ||
                isWinnerByCols(gameTable, player.getSign()) ||
                isWinnerByMainDiagonal(gameTable, player.getSign()) ||
                isWinnerBySecondaryDiagonal(gameTable, player.getSign());
    }

    private boolean isWinnerByRows(final GameTable gameTable, final Sign sign) {
        return isWinnerUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i, j + k));
    }

    private boolean isWinnerByCols(final GameTable gameTable, final Sign sign) {
        return isWinnerUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j));
    }

    private boolean isWinnerByMainDiagonal(final GameTable gameTable, final Sign sign) {
        return isWinnerUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j + k));
    }

    private boolean isWinnerBySecondaryDiagonal(final GameTable gameTable, final Sign sign) {
        return isWinnerUsingLambda(gameTable, sign, (i, j, k) -> new Cell(i + k, j - k));
    }

    private boolean isWinnerUsingLambda(final GameTable gameTable, final Sign sign, final Lambda lambda) {
        for (int i = 0; i < gameTable.getSize(); i++) {
            for (int j = 0; j < gameTable.getSize(); j++) {
                int filledCellCount = 0;
                for (int k = 0; k < WIN_COMBINATION_SIZE; k++) {
                    final Cell cell = lambda.convert(i, j, k);
                    if (gameTable.isValid(cell)) {
                        if (gameTable.getSign(cell) == sign) {
                            filledCellCount++;
                        } else {
                            break;
                        }
                    }
                }
                if (filledCellCount == WIN_COMBINATION_SIZE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @author devonline
     * @link http://devonline.academy/java
     */
    @FunctionalInterface
    private interface Lambda {

        Cell convert(int i, int j, int k);
    }
}
