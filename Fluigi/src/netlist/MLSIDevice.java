/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import static fluigi.DefaultDeviceParameters.*;
import static fluigi.DefaultLayerNames.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author cassie
 */
public final class MLSIDevice extends Device {

    protected HashMap<String, Layer> hmLayers;
    protected ArrayList<String> listLayerNames;
    public HashMap<Valve, Channel> hmValveToChannel;
    public HashMap<Channel, Valve> hmChannelToValve;
    public ArrayList<ArrayList<Valve>> listControlLines;
    public ArrayList<ArrayList<Valve>> muxControlLines; //inferred control mux only
    private boolean hasControlMux;
    public String name;

    public MLSIDevice() {
        hmValveToChannel = new HashMap<>();
        hmChannelToValve = new HashMap<>();
        listControlLines = new ArrayList<>();
        listLayerNames = new ArrayList<>();
        hmLayers = new HashMap();
        hasControlMux = false;
        addLayer(DEFAULT_CELL_TRAP_LAYER_NAME, DEFAULT_P_RESIST);
        addLayer(DEFAULT_FLOW_LAYER_NAME, DEFAULT_P_RESIST);
        addLayer(DEFAULT_CONTROL_LAYER_NAME, DEFAULT_N_RESIST);
        
    }

    public MLSIDevice(String n) {
        hmValveToChannel = new HashMap<>();
        hmChannelToValve = new HashMap<>();
        listControlLines = new ArrayList<>();
        listLayerNames = new ArrayList<>();
        hmLayers = new HashMap();
        addLayer(DEFAULT_CELL_TRAP_LAYER_NAME, DEFAULT_P_RESIST);
        addLayer(DEFAULT_FLOW_LAYER_NAME, DEFAULT_P_RESIST);
        addLayer(DEFAULT_CONTROL_LAYER_NAME, DEFAULT_N_RESIST);
        hasControlMux = false;
        name = n;
    }

    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public void setLimits(){
        for(Layer l : hmLayers.values()){
            l.setLimits();
            if(l.minX <= minX){
                minX = l.minX;
            }
            if(l.minY <= minY){
                minY = l.minY;
            }
            if(l.maxX >= maxX){
                maxX = l.maxX;
            }
            if(l.maxY >= maxY){
                maxY = l.maxY;
            }
        }
    }
    @Override
    public HashMap<String, Layer> getLayers() {
        return hmLayers;
    }

    @Override
    public ArrayList<String> getLayerNames(){
        return listLayerNames;
    }
    
    @Override
    public void addComponent(Component c, String layerName) {
        Layer l = hmLayers.get(layerName);
        l.addComponent(c);
        if (c.getClass() == MLSIValve.class) {
            Valve v = (Valve) c;
            hmValveToChannel.put(v, v.getChannel());
            hmChannelToValve.put(v.getChannel(), v);
            // add singlton valve to the list of control lines
            ArrayList<Valve> list = new ArrayList<>();
            list.add(v);
            listControlLines.add(list);
        }
        if (c.getClass() == MLSICellTrapL.class || c.getClass() == MLSICellTrapS.class) {
            hmLayers.get(DEFAULT_CELL_TRAP_LAYER_NAME).addComponent(c);
        }
    }

    @Override
    public void removeComponent(Component c, String layerName) {
        Layer l = hmLayers.get(layerName);
        l.removeComponent(c);
        if (layerName.equals(DEFAULT_CONTROL_LAYER_NAME)) {
            if (c.getClass() == Valve.class) {
                Valve v = (Valve) c;
                hmValveToChannel.remove(v);
                hmChannelToValve.remove(v.getChannel());
                for (ArrayList<Valve> list : listControlLines) {
                    if (list.remove(v)) {
                        if (list.isEmpty()) {
                            listControlLines.remove(list);
                        }
                    }
                }
            }
        }
        if (c.getClass() == CellTrap.class) {
            hmLayers.get(DEFAULT_CELL_TRAP_LAYER_NAME).removeComponent(c);
        }
    }

    @Override
    public Channel addChannel(Component c1, Component c2, String layerName) {
        Layer l = hmLayers.get(layerName);
        return l.addChannel(c1, c2);
    }

    @Override
    public void removeChannel(Component c1, Component c2, String layerName) {
        Layer l = hmLayers.get(layerName);
        l.removeChannel(c1, c2);
    }

    @Override
    public void removeChannel(Channel c, String layerName) {
        Layer l = hmLayers.get(layerName);
        l.removeChannel(c);
    }

    @Override
    public boolean hasComponent(Component c, String layerName) {
        Layer l = hmLayers.get(layerName);
        return l.hasComponent(c);
    }

    @Override
    public boolean hasChannel(Channel c, String layerName) {
        Layer l = hmLayers.get(layerName);
        return l.hasChannel(c);
    }

    @Override
    public Set<Component> getAllComponents(String layerName) {
        Layer l = hmLayers.get(layerName);
        return l.getAllComponents();
    }

    @Override
    public Set<Channel> getAllChannels(String layerName) {
        Layer l = hmLayers.get(layerName);
        return l.getAllChannels();
    }

    @Override
    public Layer addLayer(String layerName, String photoresist) {
        Layer l = new Layer();
        l.setPhotoresist(photoresist);
        hmLayers.put(layerName, l);
        listLayerNames.add(layerName);
        return l;
    }

    @Override
    public void removeLayer(String layerName) {
        Layer l = hmLayers.get(layerName);
        l.cleanUp();
        hmLayers.remove(layerName);
    }

    @Override
    public Module addModule(Module m) {//this is hacked until i get modules ironed out completely
        Layer flowLayer = hmLayers.get(DEFAULT_FLOW_LAYER_NAME);
        Layer controlLayer = hmLayers.get(DEFAULT_CONTROL_LAYER_NAME);
        m.makeFlowElements(flowLayer);
        m.makeControlElements(controlLayer);
        for (Component c : m.getControlComponents()) {
            if (c.getClass() == Valve.class) {
                Valve v = (Valve) c;
                hmValveToChannel.put(v, v.getChannel());
                hmChannelToValve.put(v.getChannel(), v);
            }

        }
        if (m.getClass() == Mux.class) {
            Mux mux = (Mux) m;
            listControlLines.addAll(mux.getControlLines());
        }
        return m;
    }

    @Override
    public void removeModule(Module m) {
        Layer flowLayer = hmLayers.get(DEFAULT_FLOW_LAYER_NAME);
        Layer controlLayer = hmLayers.get(DEFAULT_CONTROL_LAYER_NAME);
        m.removeFlowElements(flowLayer);
        m.removeControlElements(controlLayer);
        for (Component c : m.getControlComponents()) {
            if (c.getClass() == Valve.class) {
                Valve v = (Valve) c;
                hmValveToChannel.remove(v);
                hmChannelToValve.remove(v.getChannel());
            }
        }
        if (m.getClass() == Mux.class) {
            Mux mux = (Mux) m;
            listControlLines.removeAll(mux.getControlLines());
        }
    }

    public void setControlMux(boolean b) {
        hasControlMux = b;
    }

    public boolean hasControlMux() {
        return hasControlMux;
    }

    public void inferControlMux() {
        addLayer(DEFAULT_MUX_LAYER_NAME, DEFAULT_P_RESIST);
        Layer muxLayer = hmLayers.get(DEFAULT_MUX_LAYER_NAME);
        Layer controlLayer = hmLayers.get(DEFAULT_CONTROL_LAYER_NAME);
        /*
         so this is the last step in the entire complicated shebang.  and should behave roughly as follows.
         or at least if it does, it *shouldn't* break anything.
         */
        // 1.  take control layer.  find number of control lines.  keep track of this somewhere
        // 2.  for each control line, terminate with a node.
        // 3.  that list of nodes is now a list of inputs into a mux
        muxControlLines = new ArrayList<>();
        ArrayList<Component> nodes = new ArrayList<>();
        boolean top = true;
        for (ArrayList<Valve> l : listControlLines) {
            Component n = new MLSINode();
            controlLayer.addComponent(n);
            if (l.size() == 1) {
                controlLayer.addChannel(n, l.get(0));
            } else if (top) {
                controlLayer.addChannel(n, l.get(l.size() - 1));
                top = !top;
            } else {
                controlLayer.addChannel(n, l.get(0));
            }
            nodes.add(n);
        }
        // 4.  make a control port.  that control port is the output of the mux
        Port p = new MLSIPort(DEFAULT_PORT_RADIUS);
        controlLayer.addComponent(p);
        ArrayList<Component> out = new ArrayList<>();
        out.add(p);
        // 5.  make a new mux module.
        Mux m = new MLSIMux(nodes, out);
        // 6.  add the new mux module to the control and flow layers, with the caveat that 
        //the mux control elements are on the flow layer and the mux flow elements are on the control layer.
        m.makeFlowElements(controlLayer);
        m.makeControlElements(muxLayer);
        // 7.  make the flow ports to go with the mux lines
        muxControlLines.addAll(m.getControlLines());
        for (ArrayList<Valve> l : listControlLines) {
            Component port = new MLSIPort();
            muxLayer.addComponent(port);
            if (l.size() == 1) {
                muxLayer.addChannel(port, l.get(0));
            } else if (top) {
                muxLayer.addChannel(port, l.get(l.size() - 1));
                top = !top;
            } else {
                muxLayer.addChannel(port, l.get(0));
            }
        }
        hasControlMux = true;
    }

    @Override
    public Layer getMainLayer() {
        for (String s : listLayerNames){
            Layer l = hmLayers.get(s);
            if (l.isMainLayer()){
                return l;
            }
        }
        return null;
    }

    @Override
    public void setMainLayer(String layerName) {
        for (String s : listLayerNames){
            Layer l = hmLayers.get(s);
            if (l.isMainLayer()){
                l.setMainLayer(false);
            } else if (layerName.equals(s)){
                l.setMainLayer(true);
            }
        }        
    }
}
