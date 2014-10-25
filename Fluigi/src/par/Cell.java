/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par;

/**
 *
 * @author cassie
 */
class Cell {
    public int b_n;   //base cost of cell
    public double h_n;  //historical cost of cell, updated every global iteration
    public double p_n;  //present cost of cell, updated every time the cell is used.  set to 1 the first global iteration.
    public int numShared; //number of paths currently using this cell.
    private double cellCost;  //cost it takes to use this cell.
    private double pathCost;  //cost of wavefront at this cell.
    private String predecessor;  //reset each signal iteration
    private boolean isReached;  //reset each signal iteration

    //the list of things this can possibly have
    //all public so I don't have to deal with interfaces just yet
    public boolean hasFlowChannel;
    public boolean hasControlChannel;
    public boolean hasValve;
    public boolean hasFlowPort;
    public boolean hasControlPort;
    
    Cell(int baseCost) {
        this.b_n = baseCost;
        this.h_n = 0;
        this.p_n = 1;
        this.cellCost = (this.b_n+this.h_n)*this.p_n;
        this.pathCost = 0;
        this.predecessor = "";
        this.isReached = false;
        this.numShared = 0;
    }

    Cell() {
        this.b_n = 1;
        this.p_n = 1;
        this.h_n = 0;
        this.cellCost = (this.b_n+this.h_n)*this.p_n;
        this.pathCost = 0;
        this.predecessor = "";
        this.isReached = false;
        this.numShared =0;
    }

    public void setPred(String pred) {
        this.predecessor = pred;
    }

    public void setIsReached(boolean reached) {
        this.isReached = reached;
    }

    public void updateCost(){
        this.cellCost = (this.b_n+this.h_n)*this.p_n;
    }

    public void setCost(int cost){
        this.cellCost = cost;
    }
    
    public void setPathCost(double cost){
        this.pathCost = cost;
    }

    public String getPred() {
        return this.predecessor;
    }

    public boolean getIsReached() {
        return this.isReached;
    }

    public double getCost() {
        return this.cellCost;
    }
    
    public double getPathCost(){
        return this.pathCost;
    }
}
