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

import academy.devonline.gomoku.model.config.Level;
import academy.devonline.gomoku.model.config.Size;

import static academy.devonline.gomoku.model.config.Level.LEVEL2;
import static academy.devonline.gomoku.model.config.Size.SIZE15;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public final class Constants {

    public static final int WIN_COMBINATION_SIZE = 5;

    public static final String DELAY_PREFIX = "DELAY=";

    public static final Level DEFAULT_LEVEL = LEVEL2;

    public static final Size DEFAULT_SIZE = SIZE15;

    public static final long DEFAULT_DELAY_IN_MILLIS = 0;

    private Constants() {
    }
}
