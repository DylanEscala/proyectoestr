/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dylan
 */
public class Node<E> {
    private E data;
    private Node<E> left;
    private Node<E> right;
    private Node<E> father;

    public Node<E> getFather() {
        return father;
    }

    public void setFather(Node<E> father) {
        this.father = father;
    }

        
    public Node(E data){
        right=left=null;
        this.data=data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
    public boolean hasChilds(){
        return this.getLeft()!=null || this.getRight()!=null;
    }
    
}
