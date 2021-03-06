package proyecto_arbol;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        if (root==null){
            cargarArbol();
        }
        current = root;
    }

    public void cargarArbol() {
        FileReader fr = null;
        Stack pila = new Stack();
        try {
            File fil=new File("datos-1.txt");
            fr = new FileReader(fil);
            try (BufferedReader bf = new BufferedReader(fr)) {
                String line=bf.readLine();
                while (line!=null){
                    char l = line.charAt(1);
                    if (l == 'R') {
                        Node<String> node = new Node(line.substring(3));
                        pila.push(node);
                    } else {
                        Node<String> node = new Node(line.substring(3));
                        root=node;
                        Node<String> noder=((Node<String>)pila.pop());
                        noder.setFather(node);
                        Node<String> nodel=((Node<String>)pila.pop());
                        nodel.setFather(node);
                        node.setRight(noder);
                        node.setLeft(nodel);
                        pila.push(node);
                    }
                    line = bf. readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fr==null){
                    System.out.println("Error");
                }
                else{
                    fr.close();
                }
                
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
            guardarArbolito(root,bw);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(bw==null){
                    System.out.println("Error");
                }else{
                    bw.close();
                }
                
            } catch (IOException ex) {
                Logger.getLogger(GMind.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void guardarArbolito(Node<String> node,BufferedWriter bw) {
        try {
            if (node.hasChilds()) {
                guardarArbolito(node.getLeft(),bw);
                guardarArbolito(node.getRight(),bw);
                bw.write("#P "+node.getData()+"\n");
            }else{
                bw.write("#R "+node.getData()+"\n");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public String getCurrent(){
        return current.getData();
    }
    public void anadir(String preg,String res){
        Node<String> nuevop=new Node(preg);
        nuevop.setFather(current.getFather());
        Node<String> nuevor=new Node(res);
        if(current.getFather().getLeft()==current){
            current.getFather().setLeft(nuevop);
        }else{
            current.getFather().setRight(nuevop);
        }
        current.setFather(nuevop);
        nuevor.setFather(nuevop);
        Node<String> curr=current;
        current=nuevop;
        current.setLeft(nuevor);
        current.setRight(curr);
    }public void restart(){
        current=root;
    }
}
