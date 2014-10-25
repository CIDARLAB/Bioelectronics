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
public class MLSICellTrapL extends CellTrap{
    private Terminal cp1;
    private Terminal cp2;
    
    public MLSICellTrapL(){
        cp1 = new Terminal();
        cp2 = new Terminal();
        listConnections.add(cp1);
        listConnections.add(cp2);
        hmConnections.put(cp1, null);
        hmConnections.put(cp2, null);
    }
    
    public void calcWidth(){
        if (orientation){
            w = 2*chamberLength+feedingChannelWidth;
        } else {
            w = numChambers/2*(chamberWidth+chamberSpacing);
        }
    }
    
    public void calcLength(){
        if(orientation){
            l = numChambers/2*(chamberWidth+chamberSpacing);
        } else {
            l = 2*chamberLength+feedingChannelWidth;
        }
    }

    @Override
    public void updateConnectionPoints() {
        calcLength();
        calcWidth();
        if (orientation){
        cp1.setPointCoords(x+w/2, y);
        cp2.setPointCoords(x+w/2, y+l);
        } else{
        cp1.setPointCoords(x, y+l/2);
        cp2.setPointCoords(x+w, y+l/2);
        }
    }

    @Override
    public void rotCW() {
        orientation = !orientation;
        int i = w;
        w = l;
        l = i;
        updateConnectionPoints();
    }

    @Override
    public void rotCCW() {
        orientation = !orientation;
        int i = w;
        w = l;
        l = i;
        updateConnectionPoints();   
    }

    @Override
    public void flipVert() {
        if (orientation){
        Channel t = hmConnections.get(cp1);
        hmConnections.put(cp1, hmConnections.get(cp2));
        hmConnections.put(cp2, t);
        }
    }

    @Override
    public void flipHor() {
        if (!orientation){
        Channel t = hmConnections.get(cp2);
        hmConnections.put(cp2, hmConnections.get(cp1));
        hmConnections.put(cp1, t);
        }
    }


    @Override
    public void drawChambers(Graphics g) {
        if (orientation){
            for (int i = 0; i < numChambers/2; i++){
                g.fillRect(x, y+i*(chamberWidth+chamberSpacing), chamberLength, chamberWidth);
                g.fillRect(x+chamberLength+feedingChannelWidth, y+i*(chamberWidth+chamberSpacing), chamberLength, chamberWidth);
            }
        } else {
            for (int i = 0; i < numChambers/2; i++){
                g.fillRect(x+i*(chamberWidth+chamberSpacing), y, chamberWidth, chamberLength);
                g.fillRect(x+i*(chamberWidth+chamberSpacing), y+chamberLength+feedingChannelWidth, chamberWidth, chamberLength);
            }
        }
        
    }

    @Override
    public void drawChannels(Graphics g) {
        if (orientation){
            g.fillRect(x+chamberLength, y, feedingChannelWidth, l);
        } else {
            g.fillRect(x, y+chamberLength, w, feedingChannelWidth);
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
}
