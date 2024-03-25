package javacat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JavacatTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printsHelloWorld() throws FileNotFoundException {
        String[] args = new String[] { "src/test/resources/test2.txt" };
        Javacat.main(args);
        String expectedOutput = "\"I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process.\"\n" +
        "\"Do not get elated at any victory, for all such victory is subject to the will of God.\"\n" +
        "\"Wear gratitude like a cloak and it will feed every corner of your life.\"\n" +
        "\"If you even dream of beating me you'd better wake up and apologize.\"\n" +
        "\"I Will Praise Any Man That Will Praise Me.\"";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}