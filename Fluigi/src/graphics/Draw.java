/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import static fluigi.DefaultDeviceParameters.DEFAULT_PT_TO_UM;
import static fluigi.DefaultDeviceParameters.DEFAULT_UM_TO_PT;
import static fluigi.DefaultLayerNames.*;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.epsgraphics.EpsGraphics;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static net.sf.epsgraphics.ColorMode.COLOR_RGB;
import netlist.*;

/**
 *
 * @author cassie
 */
public class Draw {
    /*
    1.  Draw the chip, with all layers on it
    2.  Draw the alignment marks on each layer
    3.  
    for each layer, make an EpsGraphics object, and link it with the layer name in the device.
    */
    private final HashMap<String, EpsGraphics> hmGraphics;
    private final static int OFFSET = 2000; //1mm border around device for things like cutting
    private final static int BORDERS = 5000;
    private int deviceMinX;
    private int deviceMaxX;
    private int deviceMinY;
    private int deviceMaxY;
    private int tileX;  //number of chips in x direction on wafer
    private int tileY;  //number of chips in y direction on wafer
    private final int alignment_mark_width = 400;
    
    public Draw(){
        hmGraphics = new HashMap<>();
    }
    
    public void cleanup() throws IOException{
        for(EpsGraphics e : hmGraphics.values()){
            e.close();
        }
        hmGraphics.clear();
    }
    
    public void setLimits(Device d){
        deviceMinX = (int) ((d.minX- OFFSET)*DEFAULT_PT_TO_UM );
        deviceMaxX = (int) ((d.maxX + OFFSET)*DEFAULT_PT_TO_UM);
        deviceMinY = (int) ((d.minY - OFFSET)*DEFAULT_PT_TO_UM);
        deviceMaxY = (int) ((d.maxY+ OFFSET)*DEFAULT_PT_TO_UM);
    }
    
    public void setup(Device d, int a, int b, boolean hasLabels) throws FileNotFoundException, IOException{
        // add 1mm border around chip for alignment marks etc
        //makes all the epsGraphics objects for each layer in device d
        HashMap<String, Layer> hmLayers = d.getLayers();
        ArrayList<String> listNames = d.getLayerNames();
        for (String s : listNames){
            String title = fluigi.DefaultOutputOptions.outputDirectory + d.getName() + "_" + s;
            OutputStream stream = new FileOutputStream(title + ".eps");
            EpsGraphics canvas;
            if(hasLabels){
                if (hmLayers.get(s).getPhotoresist().equals(DEFAULT_N_RESIST)){//scale layer based on photoresist
                    canvas = new EpsGraphics(title, stream, 0, 0, (int) Math.ceil(1.017*(deviceMaxX-deviceMinX)*a), (int) Math.ceil(1.017*(deviceMaxY-deviceMinY)*b + BORDERS*DEFAULT_PT_TO_UM), COLOR_RGB);
                } else {
                    canvas = new EpsGraphics(title, stream, 0, 0, a*(deviceMaxX-deviceMinX), b*(deviceMaxY-deviceMinY)+(int)(BORDERS*DEFAULT_PT_TO_UM), COLOR_RGB);
                }
                //System.out.println(BORDERS);
                //System.out.println(b*(deviceMaxY-deviceMinY)+BORDERS);
            } else if (hmLayers.get(s).getPhotoresist().equals(DEFAULT_N_RESIST)){//scale layer based on photoresist
                canvas = new EpsGraphics(title, stream, 0, 0, (int) Math.ceil(1.017*(deviceMaxX-deviceMinX)*a), (int) Math.ceil(1.017*(deviceMaxY-deviceMinY)*b), COLOR_RGB);
            } else {
                canvas = new EpsGraphics(title, stream, 0, 0, a*(deviceMaxX-deviceMinX), b*(deviceMaxY-deviceMinY), COLOR_RGB);
            }
            setColor(canvas, s);
            hmGraphics.put(s, canvas);
        }
    }
    
    public void drawDevice(Device d) throws IOException{// draws the device all in one eps file with all layers, and each layer seperately
        //layers in following order, bottom to top: cell trap, flow, control, mux
        setLimits(d);
        String title = fluigi.DefaultOutputOptions.outputDirectory + d.getName();
        OutputStream stream = new FileOutputStream(title + ".eps");
        EpsGraphics canvas = new EpsGraphics(title, stream, 0, 0, deviceMaxX-deviceMinX, deviceMaxY-deviceMinY, COLOR_RGB);
        HashMap<String, Layer> hmLayers = d.getLayers();
        ArrayList<String> listNames = d.getLayerNames();
        canvas.translate(OFFSET*DEFAULT_PT_TO_UM, OFFSET*DEFAULT_PT_TO_UM);
        canvas.scale(DEFAULT_PT_TO_UM,DEFAULT_PT_TO_UM);
        for (String s : listNames){
            Layer l = hmLayers.get(s);
            setColor(canvas, s);
            l.draw(canvas);
            if (s.equals(DEFAULT_CELL_TRAP_LAYER_NAME)){
                for (Component c : l.getAllComponents()){
                    if (c.getClass().getSuperclass()==CellTrap.class){
                        CellTrap ct = (CellTrap) c;
                        ct.setDrawnChambers(true);
                    }
                }
            }
            if (s.equals(DEFAULT_FLOW_LAYER_NAME)){
                for (Component c : l.getAllComponents()){
                    if (c.getClass().getSuperclass()==CellTrap.class){
                        CellTrap ct = (CellTrap) c;
                        ct.setDrawnChambers(false);
                    }
                }
            }
        }
        canvas.close();
    }
    
    public void drawPhotomasks(Device d, int r) throws IOException{// draw each layer of the device on a seperate eps file, tiling to fit in a 4" wafer
        boolean drawLabels = true;
        boolean isMainLayer = false;
        setWaferArea(d, r);
        setup(d, tileX, tileY, drawLabels);
        HashMap<String, Layer> hmLayers = d.getLayers();
        ArrayList<String> listNames = d.getLayerNames();
        for (String s : listNames){
            isMainLayer = s.equals(DEFAULT_FLOW_LAYER_NAME);
            Layer l = hmLayers.get(s);
            EpsGraphics canvas = hmGraphics.get(s);
            if (s.equals(DEFAULT_CONTROL_LAYER_NAME)){
                canvas.scale(DEFAULT_PT_TO_UM*1.017,DEFAULT_PT_TO_UM*1.017);
            } else {
            canvas.scale(DEFAULT_PT_TO_UM,DEFAULT_PT_TO_UM);
            }
            double xOffset = 0;
            double yOffset = 0;
            for(int i = 0; i < tileX; i++){
                canvas.translate(xOffset, 0);
                for(int j = 0; j < tileY; j++){
                    canvas.translate(0, yOffset);
                    setColor(canvas, s);
                    canvas.translate(OFFSET, OFFSET);
                    //canvas.drawString("TEST", 0, 0);
                    l.draw(canvas);                    
                    canvas.translate(-OFFSET, -OFFSET);
                    drawAlignmentMarks(canvas, isMainLayer, listNames.size());
                    setColor(canvas, s);
                    drawGuideLines(canvas);
                   
                    yOffset = (deviceMaxY-deviceMinY)*DEFAULT_UM_TO_PT;
                }
                xOffset = (deviceMaxX-deviceMinX)*DEFAULT_UM_TO_PT;
                yOffset = -(tileY-1)*(deviceMaxY-deviceMinY)*DEFAULT_UM_TO_PT;
                
            }
            xOffset = -(tileX-1)*(deviceMaxX-deviceMinX)*DEFAULT_UM_TO_PT;
            canvas.translate(xOffset,yOffset);
            //drawAlignmentMarks(canvas, isMainLayer, listNames.size());
            //drawGuideLines(canvas);
            drawLabels(canvas);
            //draw tileX x tileY clones of the layer
            //if layer is cell trap, set all components to 'drawn chambers'
            if (s.equals(DEFAULT_CELL_TRAP_LAYER_NAME)){
                for (Component c : l.getAllComponents()){
                    if (c.getClass().getSuperclass()==CellTrap.class){
                        CellTrap ct = (CellTrap) c;
                        ct.setDrawnChambers(true);
                    }
                }
            }
        }
    }
    
    public void drawSinglePhotomask(Device d, int r) throws IOException{
        boolean drawLabels = false;
        boolean isMainLayer = false;
        setWaferArea(d, r);
        setup(d, 1, 1, drawLabels);
        HashMap<String, Layer> hmLayers = d.getLayers();
        ArrayList<String> listNames = d.getLayerNames();
        for (String s : listNames){
            isMainLayer = s.equals(DEFAULT_FLOW_LAYER_NAME);
            Layer l = hmLayers.get(s);
            EpsGraphics canvas = hmGraphics.get(s);
            if (s.equals(DEFAULT_CONTROL_LAYER_NAME)){
                canvas.scale(DEFAULT_PT_TO_UM*1.017,DEFAULT_PT_TO_UM*1.017);
            } else {
            canvas.scale(DEFAULT_PT_TO_UM,DEFAULT_PT_TO_UM);
            }
                    setColor(canvas, s);
                    canvas.translate(OFFSET, OFFSET);
                    //canvas.drawString("TEST", 0, 0);
                    l.draw(canvas);                    
                    canvas.translate(-OFFSET, -OFFSET);
                    drawAlignmentMarks(canvas, isMainLayer, listNames.size());
                    setColor(canvas, s);
                    drawGuideLines(canvas);
            //drawAlignmentMarks(canvas, isMainLayer, listNames.size());
            //drawGuideLines(canvas);
            drawLabels(canvas);
            //draw tileX x tileY clones of the layer
            //if layer is cell trap, set all components to 'drawn chambers'
            if (s.equals(DEFAULT_CELL_TRAP_LAYER_NAME)){
                for (Component c : l.getAllComponents()){
                    if (c.getClass().getSuperclass()==CellTrap.class){
                        CellTrap ct = (CellTrap) c;
                        ct.setDrawnChambers(true);
                    }
                }
            }
        }
    }
    
    public void setWaferArea(Device d, int diameter){
        setLimits(d);
        int edge = (int) Math.floor(25400 * diameter/2 * Math.sqrt(2.0)*DEFAULT_PT_TO_UM) ;
        tileX = edge/(deviceMaxX-deviceMinX);
        tileY = edge/(deviceMaxY-deviceMinY);
    }
    
    public void drawAlignmentMarks(EpsGraphics canvas, boolean b, int i){
        int minXShift = OFFSET/4;//in from left edge
        int maxXShift = i*alignment_mark_width-OFFSET/4;// in from right edge
        int minYShift = OFFSET/4;//in from top
        int maxYShift = 2*alignment_mark_width-OFFSET/4;//in from bottom
        if(b){
            for(int j = 0; j < i; j ++){
                if(j%2 == 0){
                    drawPlusMarks(canvas, minXShift+j*alignment_mark_width, minYShift);//top left;
                    drawPlusMarks(canvas, minXShift+j*alignment_mark_width,(int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom left;
                    drawPlusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), minYShift);//top right;
                    drawPlusMarks(canvas,(int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom right;
                } else {
                    drawMinusMarks(canvas, minXShift+j*alignment_mark_width, minYShift);//top left;
                    drawMinusMarks(canvas, minXShift+j*alignment_mark_width, (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom left;
                    drawMinusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), minYShift);//top right;
                    drawMinusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom right;
                }            
            }
        } else {
            for(int j = 0; j < i; j ++){
                if(j%2 == 0){
                    drawMinusMarks(canvas, minXShift+j*alignment_mark_width, minYShift);//top left;
                    drawMinusMarks(canvas, minXShift+j*alignment_mark_width, (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom left;
                    drawMinusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), minYShift);//top right;
                    drawMinusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom right;
                       } else {
                    drawPlusMarks(canvas, minXShift+j*alignment_mark_width, minYShift);//top left;
                    drawPlusMarks(canvas, minXShift+j*alignment_mark_width,(int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom left;
                    drawPlusMarks(canvas, (int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), minYShift);//top right;
                    drawPlusMarks(canvas,(int)(DEFAULT_UM_TO_PT*deviceMaxX-maxXShift+j*alignment_mark_width), (int)( DEFAULT_UM_TO_PT*deviceMaxY-maxYShift));//bottom right;
                }
            }
        }
        
    }
    
    private void drawPlusMarks(EpsGraphics canvas, int x, int y){
        Color c = canvas.getColor();
        canvas.fillRect(x, y, 400, 400);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+50, y+50, 300, 300);
        canvas.setColor(c);
        canvas.fillRect(x+100, y+100, 200, 200);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+150, y+150, 100, 100);
        //bottom
        canvas.setColor(c);
        canvas.fillRect(x+50, y+450, 300, 300);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+100, y+500, 200, 200);
        canvas.setColor(c);
        canvas.fillRect(x+150, y+550, 100, 100);
    }
    
    private void drawMinusMarks(EpsGraphics canvas, int x, int y){
        Color c = canvas.getColor();
        canvas.fillRect(x+50, y+50, 300, 300);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+100, y+100, 200, 200);
        canvas.setColor(c);
        canvas.fillRect(x+150, y+150, 100, 100);
        
        canvas.fillRect(x, y+400, 400, 400);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+50, y+450, 300, 300);
        canvas.setColor(c);
        canvas.fillRect(x+100, y+500, 200, 200);
        canvas.setColor(canvas.getBackground());
        canvas.fillRect(x+150, y+550, 100, 100);
        canvas.setColor(c);
    }
    public void drawGuideLines(EpsGraphics canvas){
        //System.out.println(DEFAULT_UM_TO_PT*(deviceMaxX-deviceMinX));
        canvas.drawRect(0, 0, (int)(DEFAULT_UM_TO_PT*(deviceMaxX-deviceMinX)), (int) (DEFAULT_UM_TO_PT*(deviceMaxY-deviceMinY)));
    }
    
    public void drawLabels(EpsGraphics canvas){
        canvas.scale(DEFAULT_UM_TO_PT, DEFAULT_UM_TO_PT);
        //canvas.translate(0, DEFAULT_UM_TO_PT*deviceMaxY-BORDERS/4);
        //canvas.drawString("TEST", 0, deviceMaxY);
        canvas.drawString("THIS SIDE UP", 0, (int) ((tileY)*(deviceMaxY-deviceMinY)+BORDERS*DEFAULT_PT_TO_UM));
    }

    private void setColor(EpsGraphics canvas, String s){
        //set different color for each canvas layer
        switch(s){
            case DEFAULT_FLOW_LAYER_NAME: 
                canvas.setColor(Color.BLUE);
                break;
            case DEFAULT_CONTROL_LAYER_NAME:
                canvas.setColor(Color.RED);
                break;
            case DEFAULT_CELL_TRAP_LAYER_NAME:
                canvas.setColor(Color.CYAN);
                break;
            case DEFAULT_MUX_LAYER_NAME:
                canvas.setColor(Color.GREEN);
                break;
            default:
                canvas.setColor(Color.BLACK);
                break;
        }
    }
}
