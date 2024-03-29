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
    void concatenatesMultipleFilesAndPrintsToStandardOutput() {
        String[] args = new String[]{"src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                "Your heart is the size of an ocean. Go find yourself in its hidden depths."
                "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
                "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
                    
                    
                    
                    
                "If You Can't Make It Good, At Least Make It Look Good."
                "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
                "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."

                "Do not get elated at any victory, for all such victory is subject to the will of God."
                "Wear gratitude like a cloak and it will feed every corner of your life."
                "If you even dream of beating me you'd better wake up and apologize."
                "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void printsToStandardOutputWithNumberedLines() {
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

    @Test
    void concatenatesFilesWithNumberedLines() {
        String[] args = new String[]{"-n", "src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."
                2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
                3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
                4
                5
                6
                7
                8 "If You Can't Make It Good, At Least Make It Look Good."
                9 "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
                10 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."
                11
                12 "Do not get elated at any victory, for all such victory is subject to the will of God."
                13 "Wear gratitude like a cloak and it will feed every corner of your life."
                14 "If you even dream of beating me you'd better wake up and apologize."
                15 "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void concatenatesFilesWithNonBlankLinesNumbered() {
        String[] args = new String[]{"-b", "src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."
                2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
                3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
                
                
                
                
                4 "If You Can't Make It Good, At Least Make It Look Good."
                5 "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
                6 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."
                
                7 "Do not get elated at any victory, for all such victory is subject to the will of God."
                8 "Wear gratitude like a cloak and it will feed every corner of your life."
                9 "If you even dream of beating me you'd better wake up and apologize."
                10 "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void suppressesRepeatedEmptyOutputLines() {
        String[] args = new String[]{"-s", "src/test/resources/test.txt"};
        Javacat.main(args);
        String expectedOutput = """
                "Your heart is the size of an ocean. Go find yourself in its hidden depths."
                "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
                "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
                
                "If You Can't Make It Good, At Least Make It Look Good."
                "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void combiningSAndBFlagsPrintsCorrectOutput() {
        String[] args = new String[]{"-bs", "src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."
                2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."
                3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."
                
                4 "If You Can't Make It Good, At Least Make It Look Good."
                5 "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."
                6 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."
                
                7 "Do not get elated at any victory, for all such victory is subject to the will of God."
                8 "Wear gratitude like a cloak and it will feed every corner of your life."
                9 "If you even dream of beating me you'd better wake up and apologize."
                10 "I Will Praise Any Man That Will Praise Me.\"""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void combiningEAndBFlagsPrintsCorrectOutput() {
        String[] args = new String[]{"-bE", "src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."$
                2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."$
                3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."$
                $
                $
                $
                $
                4 "If You Can't Make It Good, At Least Make It Look Good."$
                5 "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."$
                6 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."$
                $
                7 "Do not get elated at any victory, for all such victory is subject to the will of God."$
                8 "Wear gratitude like a cloak and it will feed every corner of your life."$
                9 "If you even dream of beating me you'd better wake up and apologize."$
                10 "I Will Praise Any Man That Will Praise Me."$""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void combiningSAndBAndEFlagsPrintsCorrectOutput() {
        String[] args = new String[]{"-bsE", "src/test/resources/test.txt","src/test/resources/test2.txt"};
        Javacat.main(args);
        String expectedOutput = """
                1 "Your heart is the size of an ocean. Go find yourself in its hidden depths."$
                2 "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard."$
                3 "Thinking is the capital, Enterprise is the way, Hard Work is the solution."$
                $
                4 "If You Can't Make It Good, At Least Make It Look Good."$
                5 "Heart be brave. If you cannot be brave, just go. Love's glory is not a small thing."$
                6 "I Don't Believe In Failure. It Is Not Failure If You Enjoyed The Process."$
                $
                7 "Do not get elated at any victory, for all such victory is subject to the will of God."$
                8 "Wear gratitude like a cloak and it will feed every corner of your life."$
                9 "If you even dream of beating me you'd better wake up and apologize."$
                10 "I Will Praise Any Man That Will Praise Me."$""";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}