package com.github.bfu4.BitcoinTickersCC.except;

/**
 * AlreadyInstantiatedException
 *
 * @author bfu4
 * @since 2/27/2021 @ 12:08 PM
 */
public class AlreadyInstantiatedException extends Exception {

    public AlreadyInstantiatedException() {
        super("This class has already been instantiated!");
    }
}
