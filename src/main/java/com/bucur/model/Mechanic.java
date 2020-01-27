package com.bucur.model;

import java.util.Objects;

public class Mechanic {

    long id;
    String name;

    public Mechanic() {
    }

    public static Mechanic mechanic() {
        return new Mechanic();
    }

    public Mechanic withId(long id) {
        this.id = id;
        return this;
    }

    public Mechanic withName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mechanic mechanic = (Mechanic) o;
        return id == mechanic.id &&
                Objects.equals(name, mechanic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
