/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Graphics;
import java.util.ArrayList;
import org.jgrapht.graph.DefaultEdge;
import par.Path;

/**
 *
 * @author cassie
 */
public class Channel extends DefaultEdge {
    private static int defaultChannelWidth = 50;
    private int channelWidth;
    private ArrayList<int[]> path;
    private Terminal source;
    private Terminal target;
    
    public Channel(){
        channelWidth = defaultChannelWidth;
        path = new ArrayList<>();
    }
    
    public Channel(int i){
        channelWidth = i;
    }
    
    public void setChannelWidth(int i){
        channelWidth = i;
    }
    
    public int getChannelWidth(){
        return channelWidth;
    }
    
    public ArrayList<int[]> getPath(){
        return path;
    }
    
    public void setPath(ArrayList<int[]> list){
        path = list;
    }
    
    public Terminal getSourcePoint(){
        return source;
    }
    
    public Terminal getTargetPoint(){
        return target;
    }
    
    public void setSourcePoint(Terminal cp){//keep this in mind when you're writing routing code
        source = cp;
        path.add(0, cp.getPointCoords());
    }
    
    public void setTargetPoint(Terminal cp){//keep this in mind when you're writing routing code
        target = cp;
        path.add(cp.getPointCoords());
    }
    
    public void draw(Graphics g){
        //get next point in path, draw between current point and next point.
        for(int i = 0; i< path.size()-1; i++){
            int[] s = path.get(i);
            int[] t = path.get(i+1);
            if (s[0] == t[0]){
                g.fillRect(s[0]-channelWidth/2, Math.min(s[1], t[1])-channelWidth/2, channelWidth, Math.abs(s[1]-t[1])+channelWidth);
            }
            if (s[1] == t[1]){
                g.fillRect(Math.min(s[0], t[0])-channelWidth/2, s[1]-channelWidth/2, Math.abs(s[0]-t[0])+channelWidth, channelWidth);
            }
        }
    }
}
