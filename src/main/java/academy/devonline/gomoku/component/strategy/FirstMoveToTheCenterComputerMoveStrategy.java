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

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class FirstMoveToTheCenterComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Cell cell = new Cell(gameTable.getSize() / 2, gameTable.getSize() / 2);
        if (gameTable.isEmpty(cell)) {
            gameTable.setSign(cell, sign);
            return true;
        } else {
            return false;
        }
    }
}
