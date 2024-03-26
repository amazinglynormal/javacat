package javacat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class JavacatTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void readsFileAndPrintsToStandardOutput() {
        String[] args = new String[] { "src/test/resources/test2.txt" };
        Javacat.main(args);
        String expectedOutput = """
                "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."
                
                "Do not get elated at any victory, for all such victory is subject to the will of God."
                "Wear gratitude like a cloak and it will feed every corner of your life."
                "If you even dream of beating me you'd better wake up and apologize."
                "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void readsNumberOptionAndPrintsToStandardOutput() {
        String[] args = new String[] { "-n", "src/test/resources/test2.txt" };
        Javacat.main(args);
        String expectedOutput = """
                1 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."
                2
                3 "Do not get elated at any victory, for all such victory is subject to the will of God."
                4 "Wear gratitude like a cloak and it will feed every corner of your life."
                5 "If you even dream of beating me you'd better wake up and apologize."
                6 "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}