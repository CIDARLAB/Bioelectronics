/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Graphics;

/**
 *
 * @author cassie
 */
public class MLSINode extends Node{
    private Terminal cp1;
    private Terminal cp2;
    private Terminal cp3;
    private Terminal cp4;
    
    public MLSINode(){
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

    @Override
    public void setWidth(int i) {
        w = i;
    }

    @Override
    public void setLength(int i) {
       l = i;
    }

    @Override
    public void updateConnectionPoints() {
        cp1.setPointCoords(x+w/2, y);
        cp2.setPointCoords(x+w, y+l/2);
        cp3.setPointCoords(x+w/2, y+l);
        cp4.setPointCoords(x, y+l/2);
    }

    @Override
    public void rotCW() {
        int i = w;
        w = l;
        l = i;
        updateConnectionPoints();
        Channel t = hmConnections.get(cp1);
        hmConnections.put(cp1, hmConnections.get(cp4));
        hmConnections.put(cp4, hmConnections.get(cp3));
        hmConnections.put(cp3, hmConnections.get(cp2));
        hmConnections.put(cp2, t);
    }

    @Override
    public void rotCCW() {
        int i = w;
        w = l;
        l = i;
        updateConnectionPoints();
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
        g.fillRect(x, y, w, l);
    }

}
