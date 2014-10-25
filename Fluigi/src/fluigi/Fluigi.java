/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluigi;

import graphics.Draw;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import netlist.Device;
import netlist.MLSIDevice;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import ufgrammar.ufNetlistLexer;
import ufgrammar.ufNetlistParser;

/**
 *
 * @author cassie
 */
public class Fluigi {
    
    private static HashMap<String, Device> hmDevices;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, RecognitionException {
        setup();
        Draw graphics = new Draw();
        Path p = Paths.get("/Users/cassie/NetBeansProjects/Fluigi/test/devices/test01.uf");
        System.out.print(p.toAbsolutePath());
        File f = p.toFile();
        Device d = addDevice(f);
        d.setLimits();
        //graphics.drawDevice(d);
        //graphics.drawPhotomasks(d, 4);
        graphics.drawSinglePhotomask(d, 4);
        graphics.cleanup();
        // TODO code application logic here
    }
    
    private static void setup(){
        hmDevices = new HashMap<>();
    }
    
    public static Device addDevice(File f) throws IOException, RecognitionException{        
        ANTLRFileStream input;
        input = new ANTLRFileStream(f.getAbsolutePath());
        ufNetlistLexer lexer = new ufNetlistLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ufNetlistParser parser = new ufNetlistParser(tokens);
        parser.netlist();
        hmDevices.put(parser.d.getName(), parser.d);
        return parser.d;
    }
    
}
