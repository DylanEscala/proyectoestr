package proyecto_arbol;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dylan
 */
public class GMind {

    Node<String> root;
    String fileS = "datos-1.txt";
    Node<String> current;

    public GMind() {
        cargarArbol(fileS);
        current = root;
    }

    public void cargarArbol(String file) {
        FileReader fr = null;
        Stack pila = new Stack();
        try {
            File fil=new File("datos-1.txt");
            fr = new FileReader(fil);
            BufferedReader bf=new BufferedReader(fr);
            String line=bf.readLine();
            while (line!=null){
                System.out.println(line);
                char l = line.charAt(1);
                if (l == 'R') {
                    Node<String> node = new Node(line.substring(3) + "!");
                    pila.push(node);
                } else {
                    Node<String> node = new Node(line.substring(3) + "?");
                    root=node;
                    node.setRight((Node<String>)pila.pop());
                    node.setLeft((Node<String>)pila.pop());
                    pila.push(node);
                }
                 line = bf. readLine();
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        BufferedWriter bw=null;
        try {
            File f=new File(fileS);
            bw = new BufferedWriter(new FileWriter(f));
            bw.write("");
            bw.close();
            guardarArbolito(root);
        } catch (IOException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void guardarArbolito(Node<String> node) {
        try {
            if (node.hasChilds()) {
                guardarArbolito(node.getLeft());
                guardarArbolito(node.getRight());
                FileWriter fichero=null;
                PrintWriter pw = null;
                fichero = new FileWriter(fileS);
                pw = new PrintWriter(fichero,true);
                pw.println("#P"+node.getData());
                fichero.close();
            }else{
                FileWriter fichero = null;
                PrintWriter pw = null;
                fichero = new FileWriter(fileS,true);
                pw = new PrintWriter(fichero);
                pw.println("#R"+node.getData());
                fichero.close();
            }
        } catch (Exception e) {
        }
    }
    public String getCurrent(){
        return current.getData();
    }
}
