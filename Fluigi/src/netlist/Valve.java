/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netlist;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author cassie
 */
public abstract class Valve extends Component {
    public abstract void setWidth(int i);
    public abstract void setLength(int i);
    public abstract void setChannel(Channel c);
    public abstract Channel getChannel();
    @Override
    public abstract void updateConnectionPoints();
    @Override
    public abstract void rotCW();
    @Override
    public abstract void rotCCW();
    @Override
    public abstract void flipVert();
    @Override
    public abstract void flipHor();
    @Override
    public abstract void draw(Graphics g);
    //public abstract void draw(Graphics g);
}
