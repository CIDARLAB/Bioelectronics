/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import bio.Cell;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author cassie
 */
public abstract class CellTrap extends Component{
    protected Cell cellType;
    protected int chamberLength;
    protected int chamberWidth;
    protected int chamberSpacing;
    protected int numChambers;
    protected int feedingChannelWidth;
    protected boolean drawnChambers;
    protected boolean orientation;
    public void setDrawnChambers(boolean b){
        drawnChambers = b;
    }
    public boolean getDrawnChambers(){
        return drawnChambers;
    }
    public void setChamberLength(int i) {
        chamberLength = i;
    }
    public int getChamberLength() {
        return chamberLength;
    }
    public void setChamberWidth(int i) {
        chamberWidth = i;
    }
    public int getChamberWidth() {
        return chamberWidth;
    }
    public void setChamberSpacing(int i) {
        chamberSpacing = i;
    }
    public int getChamberSpacing() {
        return chamberSpacing;
    }
    public void setNumChambers(int i) {
        numChambers = i;
    }
    public int getNumChambers() {
        return numChambers;
    }
    public void setCellType(Cell c) {
        cellType =c;
    }
    public Cell getCellType() {
        return cellType;
    }
    public void setFeedingChannelWidth(int i){
        feedingChannelWidth = i;
    }

    public int getFeedingChannelWidth(){
        return feedingChannelWidth;
    }
    
    public void setOrientation(boolean b){
        orientation =b;
    };

    @Override
    public abstract void draw(Graphics g);
    public abstract void drawChambers(Graphics g);
    public abstract void drawChannels(Graphics g);
        @Override
    public abstract void updateConnectionPoints();
    @Override
    public abstract void rotCW();
    @Override
    public abstract void rotCCW();
    @Override
    public abstract void flipVert();
    @Override
    public abstract void flipHor();
    

}
