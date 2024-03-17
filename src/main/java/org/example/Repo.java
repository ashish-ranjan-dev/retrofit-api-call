package org.example;


public class Repo {
    private String id;
    private String name;

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
