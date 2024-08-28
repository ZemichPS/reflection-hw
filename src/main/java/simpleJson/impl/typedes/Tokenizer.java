package simpleJson.impl.typedes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {










    public List<String> getAllElements(String input) {
        List<String> tokens = new ArrayList<String>();
        input = input.trim();
        input = input.replaceAll("\\s+", " ");

        input.lines().forEach(line -> {
            line = line.trim();
            String[] band = line.split("\\s");
            tokens.addAll(Arrays.asList(band));
        });
        return tokens;
    }

    public List<String> getAllLines(String input) {
        return input.trim()
                .replaceAll("\\s+", "")
                .lines()
                .toList();
    }


}

enum JsonToken {
    START_OBJECT, END_OBJECT, START_ARRAY, END_ARRAY, FIELD_NAME, VALUE_STRING, VALUE, END_DOCUMENT
}