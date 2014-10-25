/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author cassie
 */
public abstract class Module {
    //public abstract ArrayList<ArrayList<Component>> getControls();
    public abstract Set<Component> getFlowComponents();
    public abstract Set<Component> getControlComponents();
    public abstract Set<Channel> getFlowChannels();
    public abstract Set<Channel> getControlChannels();
    public abstract List<Component> getInputs();
    public abstract List<Component> getOutputs();
    public abstract void makeFlowElements(Layer l);
    public abstract void makeControlElements(Layer l);
    public abstract void removeFlowElements(Layer l);
    public abstract void removeControlElements(Layer l);
}
