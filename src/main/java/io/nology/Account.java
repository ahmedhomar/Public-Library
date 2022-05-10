package io.nology;

public abstract class Account {
    private String name;
    private String id;

//  protected Account(String name, String id) {
//    this.name = name;
//    this.id = id;
//  }

 protected Account(String name) {
    this.name = "";
    this.id = "";
  }

  public String getName() {
    return name;
  }



  public String getId() {
    return id;
  }


}
