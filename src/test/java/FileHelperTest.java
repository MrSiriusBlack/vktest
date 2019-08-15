import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FileHelperTest {

    @org.junit.jupiter.api.Test
    void readArrayFromFile() {
        ArrayList<String> testArray = new ArrayList<String>(Arrays.asList("123", "234", "345", "asd"));
        assertEquals(testArray, FileHelper.readArrayFromFile("d:/testArray.txt"));
    }
}