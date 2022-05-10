package io.nology;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface BookRepository  {


  default JsonNode getBooks() throws IOException {


      try {
          // create a reader
          Reader reader = null;

          reader = Files.newBufferedReader(Paths.get("src/main/java/io/nology/books.json"));

          //create ObjectMapper instance
          ObjectMapper objectMapper = new ObjectMapper();

          //read customer.json file into tree model
          JsonNode parser = objectMapper.readTree(reader);

          // read book details
          System.out.println(parser.path("Number").asInt());
          System.out.println(parser.path("Title").asText());
          System.out.println(parser.path("Genre").asText());
          System.out.println(parser.path("SubGenre").asText());
          System.out.println(parser.path("Author").asText());
          System.out.println(parser.path("Publisher").asText());

          // close the reader

          reader.close();

      } catch (IOException e) {
          e.printStackTrace();
      }


      return null;
  }}
