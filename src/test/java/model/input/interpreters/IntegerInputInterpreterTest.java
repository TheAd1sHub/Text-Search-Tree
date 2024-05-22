package model.input.interpreters;

import static org.junit.jupiter.api.Assertions.*;

import model.input.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class IntegerInputInterpreterTest {

    private static IntegerInputInterpreter interpreter;

    @BeforeAll
    public static void init() {
        interpreter = new IntegerInputInterpreter();
    }

    @Test
    @DisplayName("Correct input interpretation")
    public void testCorrectInputInterpretation() {
        final String input1 = "12345",
                     input2 = "00",
                     input3 = "-99912341";

        final int expectedResult1 = 12345,
                  expectedResult2 = 0,
                  expectedResult3 = -99912341;

        assertEquals(expectedResult1, interpreter.interpret(input1));
        assertEquals(expectedResult2, interpreter.interpret(input2));
        assertEquals(expectedResult3, interpreter.interpret(input3 ));
    }

    @Test
    @DisplayName("Exception throwing on incorrect input")
    public void testIncorrectInputThrow() {
        final String input1 = "flex",
                     input2 = "1OO", // 'O' is a letter!
                     input3 = "adsdsda";

        assertThrows(InvalidInputException.class, () -> interpreter.interpret(input1));
        assertThrows(InvalidInputException.class, () -> interpreter.interpret(input2));
        assertThrows(InvalidInputException.class, () -> interpreter.interpret(input3));
    }
}
