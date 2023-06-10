import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        Map<String, Supplier<Integer>> wireMap = new HashMap<>();
        Map<String, Integer> wireValueMap = new HashMap<>();
        
        Function<String, Integer> parseOrGet = s -> {
            if (s.matches("\\d+")) return Integer.parseInt(s);
            return wireValueMap.getOrDefault(s, wireMap.get(s).get());
        };

        BiFunction<String, Supplier<Integer>, Supplier<Integer>> supplierGenerator = (key, valueSupplier) -> () -> {
            if (wireValueMap.containsKey(key)) return wireValueMap.get(key);
            int value = valueSupplier.get();
            wireValueMap.put(key, value);
            return value;
        };
        
        for (String line : Files.readAllLines(Path.of("./input.txt"))) {
            String[] parts = line.split(" -> ");
            String[] instructions = parts[0].split(" ");
            String wire = parts[1];

            if (instructions.length == 1) wireMap.put(wire, supplierGenerator.apply(wire, () -> parseOrGet.apply(instructions[0])));
            else if (instructions.length == 2) wireMap.put(wire, supplierGenerator.apply(wire, () -> ~(short) (int) wireMap.get(instructions[1]).get()));
            else {
                Supplier<Integer> valueSupplier = switch (instructions[1]) {
                    case "AND" -> () -> parseOrGet.apply(instructions[0]) & parseOrGet.apply(instructions[2]);
                    case "OR" -> () -> parseOrGet.apply(instructions[0]) | parseOrGet.apply(instructions[2]);
                    case "LSHIFT" -> () -> parseOrGet.apply(instructions[0]) << parseOrGet.apply(instructions[2]);
                    case "RSHIFT" -> () -> parseOrGet.apply(instructions[0]) >> parseOrGet.apply(instructions[2]);
                    default -> () -> 0;
                };

                wireMap.put(wire, supplierGenerator.apply(wire, valueSupplier));
            }
        }

        int aValue = wireMap.get("a").get();
        wireMap.put("b", () -> aValue);
        wireValueMap.clear();
        System.out.println(wireMap.get("a").get());
    }
}
