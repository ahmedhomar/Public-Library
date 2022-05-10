package io.nology;

import java.util.UUID;

public abstract class Person {
  private String name;
private final String id;

  public Person(String name) {
    this.name = this.name;
    this.id = UUID.randomUUID().toString();
  }

  public Person(String id) {

    this.id = id;
  }
}
