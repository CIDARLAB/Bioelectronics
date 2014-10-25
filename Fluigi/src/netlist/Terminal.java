/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

/**
 *
 * @author cassie
 */
public class Terminal {
    private int x;
    private int y;
    private final int[] pointCoords;
    
    public Terminal(){
        pointCoords = new int[2];
    }
    public int[] getPointCoords(){
        return pointCoords;
    }
    
    public void setPointCoords(int a, int b){
        x = a;
        y = b;
        pointCoords[0]=x;
        pointCoords[1]=y;
    }
}
