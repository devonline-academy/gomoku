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

package academy.devonline.gomoku.component.config;

import academy.devonline.gomoku.model.config.Level;
import academy.devonline.gomoku.model.config.PlayerType;
import academy.devonline.gomoku.model.config.Size;

import static academy.devonline.gomoku.Constants.DELAY_PREFIX;
import static academy.devonline.gomoku.model.config.Level.LEVEL1;
import static academy.devonline.gomoku.model.config.Level.LEVEL2;
import static academy.devonline.gomoku.model.config.PlayerType.COMPUTER;
import static academy.devonline.gomoku.model.config.PlayerType.USER;
import static academy.devonline.gomoku.model.config.Size.SIZE15;

/**
 * @author devonline
 * @link http://devonline.academy/java
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public CommandLineArguments parse() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        Level level = null;
        Size size = null;
        long delayInMillis = -1;
        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because player types already set: player1Type='%s', player2Type='%s'!%n",
                            arg, player1Type, player2Type
                    );
                }
            } else if (LEVEL1.name().equalsIgnoreCase(arg) ||
                    LEVEL2.name().equalsIgnoreCase(arg)) {
                if (level == null) {
                    level = Level.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because level already set: '%s'!%n",
                            arg, level
                    );
                }
            } else if (isSizeArg(arg)) {
                if (size == null) {
                    size = Size.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because game table size already set: '%s'!%n",
                            arg, size
                    );
                }
            } else if (arg.toUpperCase().startsWith(DELAY_PREFIX)) {
                if (delayInMillis == -1) {
                    delayInMillis = getDelayInMillis(arg);
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because delay in millis already set: '%s'!%n",
                            arg, delayInMillis
                    );
                }
            } else {
                System.err.printf("Unsupported command line argument: '%s'%n", arg);
            }
        }
        if (level == null) {
            level = LEVEL2;
        }
        if (size == null) {
            size = SIZE15;
        }
        if (player1Type == null) {
            return new CommandLineArguments(USER, COMPUTER, level, size, delayInMillis);
        } else if (player2Type == null) {
            return new CommandLineArguments(USER, player1Type, level, size, delayInMillis);
        } else {
            return new CommandLineArguments(player1Type, player2Type, level, size, delayInMillis);
        }
    }

    private long getDelayInMillis(final String arg) {
        final String[] values = arg.split("=");
        if (values.length != 2) {
            System.err.printf(
                    "Invalid command line argument: '%s', because it must be follow the next pattern: 'delay=${DELAY_IN_MILLIS}'!%n",
                    arg
            );
            // TODO Define default values for cmd args as static constants
            return 0;
        }
        try {
            final long result = Long.parseLong(values[1]);
            if (result <= 0) {
                System.err.printf(
                        "Invalid command line argument: '%s', because delay value must be positive!%n",
                        arg
                );
                return 0;
            }
            return result;
        } catch (final NumberFormatException exception) {
            System.err.printf(
                    "Invalid command line argument: '%s', because delay value must be a long value!%n",
                    arg
            );
            return 0;
        }
    }

    private boolean isSizeArg(final String arg) {
        for (final Size value : Size.values()) {
            if (value.name().equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author devonline
     * @link http://devonline.academy/java
     */
    public static class CommandLineArguments {

        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private final Level level;

        private final Size size;

        private final long delayInMillis;

        private CommandLineArguments(final PlayerType player1Type,
                                     final PlayerType player2Type,
                                     final Level level,
                                     final Size size,
                                     final long delayInMillis) {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.level = level;
            this.size = size;
            this.delayInMillis = delayInMillis;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }

        public Level getLevel() {
            return level;
        }

        public Size getSize() {
            return size;
        }

        public long getDelayInMillis() {
            return delayInMillis;
        }
    }
}
