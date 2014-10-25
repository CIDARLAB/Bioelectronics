/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cassie
 */
public final class MLSIPort extends Port{
    private int r;
    private Terminal cp1;
    private Terminal cp2;
    private Terminal cp3;
    private Terminal cp4;
    
    public MLSIPort(){
        cp1 = new Terminal();
        cp2 = new Terminal();
        cp3 = new Terminal();
        cp4 = new Terminal();
        listConnections.add(cp1);
        listConnections.add(cp2);
        listConnections.add(cp3);
        listConnections.add(cp4);
        hmConnections.put(cp1, null);
        hmConnections.put(cp2, null);
        hmConnections.put(cp3, null);
        hmConnections.put(cp4, null);
    }

    public MLSIPort(int i){
        cp1 = new Terminal();
        cp2 = new Terminal();
        cp3 = new Terminal();
        cp4 = new Terminal();
        listConnections.add(cp1);
        listConnections.add(cp2);
        listConnections.add(cp3);
        listConnections.add(cp4);
        hmConnections.put(cp1, null);
        hmConnections.put(cp2, null);
        hmConnections.put(cp3, null);
        hmConnections.put(cp4, null);
        r = i;
        updateConnectionPoints();
    }
    
    @Override
    public void setWidth(int i) {
        r = i/2;
        w = i;
    }

    @Override
    public void setLength(int i) {
        r = i/2;
        l = i;
    }

    @Override
    public void updateConnectionPoints() {
        cp1.setPointCoords(x+r, y);
        cp2.setPointCoords(x+2*r, y+r);
        cp3.setPointCoords(x+r, y+2*r);
        cp4.setPointCoords(x, y+r);
    }

    @Override
    public void rotCW() {
        Channel t = hmConnections.get(cp1);
        hmConnections.put(cp1, hmConnections.get(cp4));
        hmConnections.put(cp4, hmConnections.get(cp3));
        hmConnections.put(cp3, hmConnections.get(cp2));
        hmConnections.put(cp2, t);
    }

    @Override
    public void rotCCW() {
        Channel t = hmConnections.get(cp1);
        hmConnections.put(cp1, hmConnections.get(cp2));
        hmConnections.put(cp2, hmConnections.get(cp3));
        hmConnections.put(cp3, hmConnections.get(cp4));
        hmConnections.put(cp4, t);    
    }

    @Override
    public void flipVert() {
        Channel t = hmConnections.get(cp1);
        hmConnections.put(cp1, hmConnections.get(cp3));
        hmConnections.put(cp3, t);
    }

    @Override
    public void flipHor() {
        Channel t = hmConnections.get(cp2);
        hmConnections.put(cp2, hmConnections.get(cp4));
        hmConnections.put(cp4, t);
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(x, y, 2*r, 2*r);
    }
    
}
