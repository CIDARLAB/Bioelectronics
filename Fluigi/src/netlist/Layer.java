/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Graphics;
import java.util.Set;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author cassie
 */
public class Layer {
    protected SimpleGraph<Component, Channel> layerGraph;
    protected EdgeFactory efChannel;
    protected String photoresist;
    protected String name;
    protected boolean isMainLayer;
    public double d;
    public int minX;
    public int minY;
    public int maxX;
    public int maxY;

    public Layer(){
        efChannel = new ClassBasedEdgeFactory(Channel.class);
        layerGraph = new SimpleGraph<>(efChannel);
    }
    
    public Layer(String s){
        efChannel = new ClassBasedEdgeFactory(Channel.class);
        layerGraph = new SimpleGraph<>(efChannel);
        name = s;
    }
    public void cleanUp(){
        layerGraph = null;
        efChannel = null;
    }
    
    public void setPhotoresist(String s){
        photoresist = s;
    }
    
    public String getPhotoresist(){
        return photoresist;
    }
    public void addComponent(Component c){
        layerGraph.addVertex(c);
    }
    
    public void removeComponent(Component c){
        layerGraph.removeVertex(c);
    }
    
    public Channel addChannel(Component c1, Component c2){
        Channel c = layerGraph.addEdge(c1, c2);
        return c;
    }
    
    public void removeChannel(Component c1, Component c2){
        layerGraph.removeEdge(c1, c2);
    }
    
    public void removeChannel(Channel c){
        layerGraph.removeEdge(c);
    }
    
    public int getNumChannels(Component c){
        return layerGraph.degreeOf(c);
    }
    
    public Set<Channel> getChannelsOfComponent(Component c){
        return layerGraph.edgesOf(c);
    }
    
    public Component getChannelSource(Channel c){
        return layerGraph.getEdgeSource(c);
    }
    
    public Component getChannelTarget(Channel c){
        return layerGraph.getEdgeTarget(c);
    }
    
    public boolean hasComponent(Component c){
        return layerGraph.containsVertex(c);
    }
    
    public boolean hasChannel(Channel c){
        return layerGraph.containsEdge(c);
    }
    
    public Set<Component> getAllComponents(){
        return layerGraph.vertexSet();
    }
    
    public Set<Channel> getAllChannels(){
        return layerGraph.edgeSet();
    }
    
    public void draw(Graphics g){
        Set<Component> components = layerGraph.vertexSet();
        for(Component c : components){
            c.draw(g);
        }
        Set<Channel> channels = layerGraph.edgeSet();
        for(Channel h : channels){
            h.draw(g);
        }
    }
    
    public void setLimits(){//add in stuff with channels later
        for(Component c: layerGraph.vertexSet()){
            if (c.getX() <= minX){
                minX = c.getX();
            }
            if (c.getY() <= minY){
                minY = c.getY();
            }
            if (c.getX()+c.getWidth() >= maxX){
                maxX = c.getX()+c.getWidth();
            }
            if (c.getY()+c.getLength() >= maxY){
                maxY = c.getY()+c.getLength();
            }
        }
    }
    
    public void setMainLayer(boolean b){
        isMainLayer = b;
    }
    
    public boolean isMainLayer(){
        return isMainLayer;
    }
}
