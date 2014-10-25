/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package par;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cassie
 */
public class Path {//rewrite
    private ArrayList<int[]> pathCoords; //each list is path, starting from source and ending at target
    private HashMap<Cell, String> pathPreds; //list of predecessors, starting from source and ending at target
    private boolean isShared;
    private int[] source;
    private int[] target;

    public Path(){
        this.pathCoords = new ArrayList<>();
        this.pathPreds = new HashMap<>();
        this.isShared = false;
        this.source = new int[2];
        this.target = new int[2];
    }
    
    public Path(int[] source, int[] target){
        this.pathCoords = new ArrayList<>();
        this.pathPreds = new HashMap<>();
        this.isShared = false;
        this.source = source;
        this.target = target;
    }


//setters
    public void setPathCoords(ArrayList<int[]> pathCoords){
        this.pathCoords = pathCoords;
    }

    public void setPathPreds(HashMap<Cell, String> pathPreds){
        this.pathPreds = pathPreds;
    }

    public void setShared(boolean shared){
        this.isShared = shared;
    }

    public void setSource(int[] source){
        this.source = source;
    }

    public void setTargets(int[] target){
        this.target = target;
    }

//getters
    public ArrayList<int[]> getPathCoords(){
        return this.pathCoords;
    }

    public HashMap<Cell, String> getPathPreds(){
        return this.pathPreds;
    }

    public boolean getShared(){
        return this.isShared;
    }

    public int[] getSource(){
        return this.source;
    }

    public int[] getTarget(){
        return this.target;
    }


}
