/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import bio.Cell;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cassie
 */
public class MLSICellTrapS extends CellTrap{
    private Terminal cp1;
    private Terminal cp2;
    private Terminal cp3;
    private Terminal cp4;
    
    public MLSICellTrapS(){
        numChambers=4;
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
    
    public void calcWidth(){
        w = 2*chamberWidth+feedingChannelWidth;
    }
    
    public void calcLength(){
        l = 2*chamberLength+feedingChannelWidth;
    }

    @Override
    public void updateConnectionPoints() {
        calcLength();
        calcWidth();
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
    public void drawChambers(Graphics g) {
        int deltaX = Math.abs(cp2.getPointCoords()[0]-cp4.getPointCoords()[0]);
        int deltaY = Math.abs(cp1.getPointCoords()[1]-cp3.getPointCoords()[1]);
        if (deltaX == w && deltaY == l){
            g.fillRect(x, y, chamberWidth, chamberLength);
            g.fillRect(x+chamberWidth+feedingChannelWidth, y, chamberWidth, chamberLength);
            g.fillRect(x, y+chamberLength+feedingChannelWidth, chamberWidth, chamberLength);
            g.fillRect(x+chamberWidth+feedingChannelWidth, y+chamberLength+feedingChannelWidth, chamberWidth, chamberLength);
        } else if (deltaX == l && deltaY == w){
            g.fillRect(x, y, chamberLength, chamberWidth);
            g.fillRect(x+chamberLength+feedingChannelWidth, y, chamberLength, chamberWidth);
            g.fillRect(x, y+chamberWidth+feedingChannelWidth, chamberLength, chamberWidth);
            g.fillRect(x+chamberLength+feedingChannelWidth, y+chamberWidth+feedingChannelWidth, chamberLength, chamberWidth);
        }
    }

    @Override
    public void drawChannels(Graphics g) {
        int deltaX = Math.abs(cp2.getPointCoords()[0]-cp4.getPointCoords()[0]);
        int deltaY = Math.abs(cp1.getPointCoords()[1]-cp3.getPointCoords()[1]);
        if (deltaX == w && deltaY == l){
            g.fillRect(x+chamberWidth, y, feedingChannelWidth, l);
            g.fillRect(x, y+chamberLength, w, feedingChannelWidth);
        } else if (deltaX == l && deltaY == w){
            g.fillRect(x+chamberLength, y, feedingChannelWidth, l);
            g.fillRect(x, y+chamberWidth, w, feedingChannelWidth);        
        }
    }
    
    @Override
    public void draw(Graphics g){ //first time, draw channels.  second time, draw chambers.
        if (!drawnChambers){
            drawChambers(g);
        } else {
            drawChannels(g);
            
        }
    }

    @Override
    public void setOrientation(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCenterX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCenterY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int leftEdge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rightEdge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int topEdge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int bottomEdge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

