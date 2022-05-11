package io.nology;

public abstract class Account {
    private final String name;
    private final String id;

    protected Account(String name) {
        this.name = name;
        this.id = "";    // initialize id
    }

    public String getName() {
        return name;
    }


    public String getId() {
        return id;
    }


}
