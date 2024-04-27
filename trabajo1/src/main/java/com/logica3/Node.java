package com.logica3;

public class Node {
    private final Planet planet;
    private Node next;
    
    public Node( Planet planet ) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return this.planet;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext( Node next ) {
        this.next = next;
    }
}