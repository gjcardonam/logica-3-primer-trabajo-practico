package com.logica3;

public class Route {
    private Node head;

    public Route(){
        this.head = null;
    }

    public void addPlanet(Planet planet){
        Node newNode = new Node(planet);
        if (this.head == null){
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void printRoute(){
        Node current = this.head;
        while (current != null){
            System.out.println(current.getPlanet().getName());
            current = current.getNext();
        }
    }

    public Node getHead(){
        return this.head;
    }
}