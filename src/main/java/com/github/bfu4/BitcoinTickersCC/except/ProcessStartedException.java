package com.github.bfu4.BitcoinTickersCC.except;

/**
 * AlreadyStartedException
 *
 * @author bfu4
 * @since 2/27/2021 @ 12:21 PM
 */
public class ProcessStartedException extends Exception {

    public ProcessStartedException() {
        super("Process has already started!");

    }

}
