/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 *
 * @author cassie
 */
public abstract class Device {
    public int minX;
    public int minY;
    public int maxX;
    public int maxY;
    public abstract HashMap<String, Layer> getLayers();
    public abstract ArrayList<String> getLayerNames();
    public abstract String getName();
    public abstract void addComponent(Component c, String layerName);
    public abstract void removeComponent(Component c, String layerName);
    public abstract Channel addChannel(Component c1, Component c2, String layerName);
    public abstract void removeChannel(Component c1, Component c2, String layerName);
    public abstract void removeChannel(Channel c, String layerName);
    public abstract boolean hasComponent(Component c, String layerName);
    public abstract boolean hasChannel(Channel c, String layerName);
    public abstract Set<Component> getAllComponents(String layerName);
    public abstract Set<Channel> getAllChannels(String layerName);
    public abstract Layer addLayer(String layerName, String photoresist);
    public abstract void removeLayer(String layerName);
    public abstract Module addModule(Module m);
    public abstract void removeModule(Module m);
    public abstract void setLimits();
    public abstract Layer getMainLayer();
    public abstract void setMainLayer(String layerName);
}
