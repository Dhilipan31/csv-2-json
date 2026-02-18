import java.awt.color.ICC_ColorSpace;
import java.io.*;
import java.util.*;

public class CsvParser {

    public void parser(String filePath, String writePath) throws IOException {

        List<Map<String, String>> records = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(writePath));

        String header = br.readLine();

        if (header == null) return;

        List<String> headers = parseLine(header);

        String line = br.readLine();
        bw.write("[\n");
        StringBuilder json = new StringBuilder();
        boolean firstRecord = true;

        while (line != null) {

            if(!firstRecord){
                bw.write(",\n");
            }
            List<String> values = parseLine(line);
            bw.write(" {");
            for (int i = 0; i < headers.size(); i++) {
                bw.write("\"" + headers.get(i) + "\" : ");
                String jsonValue = convertValue(values.get(i));
                bw.write(jsonValue);
                if (i < headers.size() - 1) {
                    bw.write(",");
                }
            }
            bw.write("}");
            firstRecord = false;
            line = br.readLine();
        }

        bw.write("\n]");
        br.close();
        bw.close();

        System.out.println("file created");
    }

    private List<String> parseLine(String line) {

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }

        }
        result.add(current.toString().trim());
        return result;
    }

    private String convertValue(String value) {

        if (value == null || value.isEmpty()) {
            return "null";
        }

        // Boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return value.toLowerCase();
        }

        // Integer
        if (value.matches("-?\\d+")) {
            return value;
        }

        // Decimal
        if (value.matches("-?\\d+\\.\\d+")) {
            return value;
        }

        // Default String (escape quotes)
        return "\"" + value.replace("\"", "\\\"") + "\"";
    }
}
