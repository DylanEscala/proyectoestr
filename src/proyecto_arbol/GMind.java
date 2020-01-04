
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dylan
 */
public class GMind<String> {

    Node<String> root;
    java.lang.String fileS = "datos-1.txt";
    Node<String> current;

    public GMind() {
        cargarArbol(fileS);
        current = root;
    }

    public void cargarArbol(java.lang.String file) {
        Stack pila = new Stack();
        try {
            Scanner input = new Scanner(new File(file));
            while (input.hasNextLine()) {
                java.lang.String line = (java.lang.String) input.nextLine();
                char l = line.charAt(1);
                if (l == 'R') {
                    Node<String> node = new Node(line.substring(3) + "!");
                    pila.push(node);
                } else {

                    Node<String> node = new Node(line.substring(3) + "?");
                    Node<String> noder = (Node<String>) pila.peek();
                    noder.setFather(node);
                    node.setRight(noder);
                    if (!(pila.isEmpty())) {
                        Node<String> nodel = (Node<String>) pila.peek();
                        node.setLeft(nodel);
                        nodel.setFather(node);
                    }
                    pila.push(node);
                }
            }
            input.close();
        } catch (Exception e) {
        }
        root = (Node<String>) pila.peek();
    }

    public void preguntar(String str) {
        if (str.equals("Si")) {
            if (current.getLeft() == null && current.getRight() == null) {
                System.out.println("gane");
            } else {
                current = current.getLeft();
            }
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                System.out.println("perdi");
            } else {
                current = current.getRight();
            }

        }
    }

    public boolean hasnext() {
        return current.getLeft() != null && current.getRight() != null;
    }

    public void guardarArbolito() {
        guardarArbolito(root);
    }

    private void guardarArbolito(Node<String> node) {
        try {
            if (node.hasChilds()) {
                guardarArbolito(node.getRight());
                guardarArbolito(node.getLeft());
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter(fileS);
                pw = new PrintWriter(fichero);
                pw.println("#P"+node.getData());
                fichero.close();
            }else{
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter(fileS);
                pw = new PrintWriter(fichero);
                pw.println("#R"+node.getData());
                fichero.close();
            }
        } catch (Exception e) {
        }
    }
}
