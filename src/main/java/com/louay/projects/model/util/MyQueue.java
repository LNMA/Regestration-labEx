package com.louay.projects.model.util;

import java.util.LinkedList;

public class MyQueue<T> {
    private int maxSize;
    private int size;
    private LinkedList<T> list;

    public MyQueue() {
        this.list = new LinkedList<T>();
        this.maxSize = 16;
    }

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        this.list = new LinkedList<T>();
    }

    public void enqueue(T item) throws RuntimeException{
        if (isFull()){
            throw new RuntimeException("Queue is fully, you cannot enqueue to fully queue");
        }
        this.list.addLast(item);
    }

    public T dequeue() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("Queue is empty, You cannot dequeue from empty queue.");
        }
        return this.list.removeFirst();
    }

    public boolean isFull(){
        if (this.list.size() >= this.maxSize ){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return this.list.size();
    }

    public boolean isEmpty(){
        if (list.size() == 0){
            return true;
        }else {
            return false;
        }
    }
}
