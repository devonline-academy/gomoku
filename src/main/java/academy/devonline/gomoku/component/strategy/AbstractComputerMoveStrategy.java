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

package academy.devonline.gomoku.component.strategy;

import academy.devonline.gomoku.component.ComputerMoveStrategy;
import academy.devonline.gomoku.model.game.Cell;
import academy.devonline.gomoku.model.game.GameTable;
import academy.devonline.gomoku.model.game.Sign;

import java.util.Random;

import static academy.devonline.gomoku.Constants.GAME_TABLE_SIZE;
import static academy.devonline.gomoku.Constants.WIN_COMBINATION_SIZE;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    private final int expectedCountEmptyCells;

    protected AbstractComputerMoveStrategy(final int expectedCountEmptyCells) {
        this.expectedCountEmptyCells = expectedCountEmptyCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        final Sign findSign = getFindSign(moveSign);
        final BestCells bestCells = new BestCells();
        findBestCellsForMoveByRows(gameTable, findSign, bestCells);
        findBestCellsForMoveByCols(gameTable, findSign, bestCells);
        findBestCellsForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellsForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);
        if (bestCells.count > 0) {
            final Cell randomCell = bestCells.emptyCells[new Random().nextInt(bestCells.count)];
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }
    }

    private void findBestCellsForMoveByRows(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i, j + k));
    }

    private void findBestCellsForMoveByCols(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(j + k, i));
    }

    private void findBestCellsForMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i + k, j + k));
    }

    private void findBestCellsForMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j, k) -> new Cell(i + k, j - k));
    }

    protected abstract Sign getFindSign(Sign moveSign);

    private void findBestCellsForMoveUsingLambdaConversion(final GameTable gameTable,
                                                           final Sign findSign,
                                                           final BestCells bestCells,
                                                           final Lambda lambda) {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                final Cell[] localEmptyCells = new Cell[WIN_COMBINATION_SIZE];
                int index = 0;
                int countEmptyCells = 0;
                int countSignCells = 0;
                for (int k = 0; k < WIN_COMBINATION_SIZE; k++) {
                    final Cell cell = lambda.convert(i, j, k);
                    if (gameTable.isValid(cell)) {
                        if (gameTable.isEmpty(cell)) {
                            countEmptyCells++;
                            localEmptyCells[index++] = cell;
                        } else if (gameTable.getSign(cell) == findSign) {
                            countSignCells++;
                        } else {
                            break;
                        }
                    }
                }
                if (countEmptyCells == expectedCountEmptyCells &&
                        countSignCells == WIN_COMBINATION_SIZE - expectedCountEmptyCells) {
                    for (int l = 0; l < index; l++) {
                        bestCells.add(localEmptyCells[l]);
                    }
                }
            }
        }
    }

    /**
     * @author devonline
     * @link http://devonline.academy/java
     */
    @FunctionalInterface
    private interface Lambda {

        Cell convert(int i, int j, int k);
    }

    /**
     * @author devonline
     * @link http://devonline.academy/java
     */
    private static class BestCells {

        private final Cell[] emptyCells = new Cell[GAME_TABLE_SIZE * GAME_TABLE_SIZE];

        private int count;

        private void add(final Cell cell) {
            for (int i = 0; i < count; i++) {
                final Cell emptyCell = emptyCells[i];
                if (emptyCell.getRow() == cell.getRow() && emptyCell.getCol() == cell.getCol()) {
                    return;
                }
            }
            emptyCells[count++] = cell;
        }
    }
}
