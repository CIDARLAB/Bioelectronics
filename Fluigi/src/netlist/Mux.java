/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.util.ArrayList;

/**
 *
 * @author cassie
 */
public abstract class Mux extends Module{
    /* 
    because god only knows if such a thing exists in other technology, and if it 
    does, i want to leave it here just in case
    */
    public abstract ArrayList<ArrayList<Valve>> getControlLines();
    
}
