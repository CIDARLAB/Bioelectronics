/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author cassie
 */
public abstract class Component {
    
    protected int x;
    protected int y;
    protected int l;
    protected int w;
    protected HashMap<Terminal, Channel> hmConnections;
    protected ArrayList<Terminal> listConnections;
    
    public void Component(){
        hmConnections = new HashMap<>();
        listConnections = new ArrayList<>();
    }
    
        //x, y, coords, etc
    public int getWidth() {
        return w;
    }
    public int getLength(){
        return l;
    }
    public void setX(int i){
        x = i;
    }
    public void setY(int i){
        y = i;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    };

    public int getCenterX() {
        return x+ w/2;
    }

    public int getCenterY() {
        return y + l/2;
    }

    public int leftEdge() {
        return x;
    }

    public int rightEdge() {
        return x+w;
    }

    public int topEdge() {
        return y;
    }

    public int bottomEdge() {
        return y+l;
    }
    
    public int calculateArea(){
        return w*l;
    };

    public ArrayList<Terminal> getConnectionPoints() {
        return listConnections;
    }

    public Channel getConnection(Terminal cp) {
        return hmConnections.get(cp);
    }

    public void setConnection(Terminal cp, Channel c) {
        hmConnections.put(cp, c);
    }

    public abstract void updateConnectionPoints();
    public abstract void rotCW();
    public abstract void rotCCW();
    public abstract void flipVert();
    public abstract void flipHor();
    
    public abstract void draw(Graphics g);
    
}
