package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GrabberTest {

    @Test
    public void echo() {
        int expect = 100;
        assertThat(Grabber.echo(100), is(expect));
    }
}