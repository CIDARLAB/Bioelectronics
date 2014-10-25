/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author cassie
 */
public class MLSIMux extends Mux{
    private ArrayList<Component> inputs;
    private ArrayList<Component> outputs;
    private ArrayList<ArrayList<Valve>> controlLines;
    private SimpleGraph<Component, Channel> flowGraph;
    private SimpleGraph<Component, Channel> controlGraph;
    protected EdgeFactory efChannel;
    
    public MLSIMux(){
        efChannel = new ClassBasedEdgeFactory(Channel.class);
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        controlLines = new ArrayList<>();
        flowGraph = new SimpleGraph(efChannel);
        controlGraph = new SimpleGraph(efChannel);
    }
    
    public MLSIMux(ArrayList<Component> in, ArrayList<Component> out){
        //assuming port lists are in fucking order
        efChannel = new ClassBasedEdgeFactory(Channel.class);
        flowGraph = new SimpleGraph(efChannel);
        controlGraph = new SimpleGraph(efChannel);
        inputs = in;
        outputs = out;
        controlLines = new ArrayList<>();
        if (in.size() > out.size()){
            buildMux("in");
        } else {
            buildMux("out");
        }
    }

    private void buildMux(String s){
        // 1.  take inputs, two at a time, until the list is 1 or less.
        // 2.  for every two inputs, make a new node in the flow graph.  put the node in a temp list
        // 3.  if there are 1 or 0 nodes left, put contents of temp list in inputs.  clear temp list
        // 4.  repeat until temp only has one node.  connect that node to output.
        LinkedList<Component> nodes;
        if (s.equals("in")) {
            nodes = new LinkedList<>(inputs);
        } else if (s.equals("out")){
            nodes = new LinkedList<>(outputs);
        } else nodes = new LinkedList<>();
        LinkedList<Component> temp = new LinkedList<>(nodes);
        LinkedList<Node> stage = new LinkedList<>();
        LinkedList<Valve> tempValvesZero = new LinkedList<>();
        LinkedList<Valve> tempValvesOne = new LinkedList<>();
        while (temp.size() > 1) {
            Component c1 = nodes.poll();
            Component c2 = nodes.poll();
            Node n = new MLSINode();
            flowGraph.addVertex(n);
            Channel h1 = flowGraph.addEdge(c1, n);
            Channel h2 = flowGraph.addEdge(c2, n);
            Valve v1 = new MLSIValve();
            v1.setChannel(h1);
            controlGraph.addVertex(v1);
            Valve v2 = new MLSIValve();
            v2.setChannel(h2);
            controlGraph.addVertex(v2);
            tempValvesZero.add(v1);
            tempValvesOne.add(v2);
            stage.addLast(n);
            if (temp.size() <= 1) {
                temp.addAll(stage);
                controlLines.add(new ArrayList<>(tempValvesZero));
                controlLines.add(new ArrayList<>(tempValvesOne));
                stage.clear();
                tempValvesZero.clear();
                tempValvesOne.clear();
            }
        }
        //connect binary tree to final component
        if (s.equals("in")){
            flowGraph.addEdge(temp.getFirst(), outputs.get(0));
        } else if (s.equals("out")){
            flowGraph.addEdge(temp.getFirst(), inputs.get(0));
        }
        //connect up all those valves
        for (ArrayList<Valve> list : controlLines){
            for (int i = 0; i < list.size()-1; i++){
                Valve v1 = list.get(i);
                Valve v2 = list.get(i+1);
                controlGraph.addEdge(v1, v2);
            }
        }
    }
    
    @Override
    public Set<Component> getFlowComponents() {
        return flowGraph.vertexSet();
    }

    @Override
    public Set<Component> getControlComponents() {
        return controlGraph.vertexSet();
    }

    @Override
    public Set<Channel> getFlowChannels() {
        return flowGraph.edgeSet();
    }

    @Override
    public Set<Channel> getControlChannels() {
        return controlGraph.edgeSet();
    }

    @Override
    public List<Component> getInputs() {
        return inputs;
    }

    @Override
    public List<Component> getOutputs() {
        return outputs;
    }

    @Override
    public void makeFlowElements(Layer l) {
        for(Component c: flowGraph.vertexSet()){
            if (!l.hasComponent(c)){
                l.addComponent(c);
            }
        }
        for (Channel c: flowGraph.edgeSet()){
            if (!l.hasChannel(c)){
                Component s = flowGraph.getEdgeSource(c);
                Component t = flowGraph.getEdgeTarget(c);
                l.addChannel(s, t);
            }
        }
    }

    @Override
    public void makeControlElements(Layer l) {
        for(Component c: controlGraph.vertexSet()){
            if (!l.hasComponent(c)){
                l.addComponent(c);
            }
        }
        for (Channel c: controlGraph.edgeSet()){
            if (!l.hasChannel(c)){
                Component s = controlGraph.getEdgeSource(c);
                Component t = controlGraph.getEdgeTarget(c);
                l.addChannel(s, t);
            }
        }    }

    @Override
    public void removeFlowElements(Layer l) {
        for(Component c: flowGraph.vertexSet()){
            if (l.hasComponent(c) && !inputs.contains(c) && !outputs.contains(c)){
                l.removeComponent(c);
            }
        }
        for (Channel c: flowGraph.edgeSet()){
            if (!l.hasChannel(c)){
                Component s = flowGraph.getEdgeSource(c);
                Component t = flowGraph.getEdgeTarget(c);
                l.removeChannel(s, t);
            }
        }    
    }

    @Override
    public void removeControlElements(Layer l) {
        for(Component c: flowGraph.vertexSet()){
            if (l.hasComponent(c) && !inputs.contains(c) && !outputs.contains(c)){
                l.removeComponent(c);
            }
        }
        for (Channel c: flowGraph.edgeSet()){
            if (!l.hasChannel(c)){
                Component s = flowGraph.getEdgeSource(c);
                Component t = flowGraph.getEdgeTarget(c);
                l.removeChannel(s, t);
            }
        } 
    }

    @Override
    public ArrayList<ArrayList<Valve>> getControlLines() {
        return controlLines;
    }
    
}
