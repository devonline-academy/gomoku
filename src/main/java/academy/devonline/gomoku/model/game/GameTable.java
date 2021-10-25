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

package academy.devonline.gomoku.model.game;

import java.util.Arrays;

import static academy.devonline.gomoku.Constants.GAME_TABLE_SIZE;
import static academy.devonline.gomoku.model.game.Sign.EMPTY;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class GameTable {

    private final Sign[][] table;

    public GameTable() {
        table = new Sign[GAME_TABLE_SIZE][GAME_TABLE_SIZE];
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                table[i][j] = EMPTY;
            }
        }
    }

    public boolean isEmpty(final Cell cell) {
        return table[cell.getRow()][cell.getCol()] == EMPTY;
    }

    public Sign getSign(final Cell cell) {
        return table[cell.getRow()][cell.getCol()];
    }

    public void setSign(final Cell cell, final Sign sign) {
        table[cell.getRow()][cell.getCol()] = sign;
    }

    public boolean isValid(final Cell cell) {
        return cell.getRow() >= 0 && cell.getRow() < GAME_TABLE_SIZE &&
                cell.getCol() >= 0 && cell.getCol() < GAME_TABLE_SIZE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameTable{");
        sb.append("table=");
        for (int i = 0; i < table.length; i++) {
            sb.append(Arrays.toString(table[i]));
            if (i < table.length - 1) {
                sb.append(';');
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
