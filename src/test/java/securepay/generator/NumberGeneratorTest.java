package securepay.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {
    @Test
    void inRange() {
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(1, NumberGenerator.inRange(1,1));
        }

        for (int i = 0; i < 10; i++) {
            int generatedNum = NumberGenerator.inRange(5,4);
            Assertions.assertTrue(generatedNum >= 4 && generatedNum <= 5);
        }
    }
}