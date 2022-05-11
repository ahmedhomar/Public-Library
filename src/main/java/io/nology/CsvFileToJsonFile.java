package io.nology;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvFileToJsonFile {

    public static void main(String[] args) throws Exception {
        File input = new File("src/main/java/io/nology/books_data.csv");
        File output = new File("src/main/java/io/nology/books.json");

        List<Map<?, ?>> data = readObjectsFromCsv(input);   // returns a list of maps
        writeAsJson(data, output); // writes the list of maps to the file
    }

    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);  // returns a mapping iterator

        return mappingIterator.readAll();   // returns a list of maps
    }

    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();  // returns an object mapper
        mapper.writeValue(file, data); // writes the list of maps to the file
    }
}

