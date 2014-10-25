grammar ufNetlist;

@header {
package ufgrammar;
import netlist.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
}

@members {
/** Map variable name to Integer object holding value */

public HashMap<String, Component> components = new HashMap<>();
public HashMap<String, Channel> channels = new HashMap<>();
public HashMap<String, Module> modules = new HashMap<>();

public String techType;

public Device d;

private String curLayer;

private ArrayList<Component> tempComponents = new ArrayList<>();
private ArrayList<Channel> tempChannels = new ArrayList<>();

public void cleanUp(){
	components.clear();
	components = null;
	channels.clear();
	channels = null;
	d = null;
	techType = null;
	curLayer = null;
	}
	

}

netlist : header (block)+
	EOF
	;

header  :	'DEVICE' name=ID 'of' tech ';' {
            techType = $tech.text;
            if (techType.equals("MLSI")){
            	d = new MLSIDevice($name.text);
            } else {
            //throw some sort of 'this isn'st suppoerted error
            }
            }
	;

/*
Add additonal technology types here
*/
tech 	:	'MLSI'
	|	'Other'
	;

block 	:	layerStat (s=stat)+ 'END LAYER;';

stat 	:	portStat
	|	channelStat
	|	muxStat
	|	nodeStat
	|	cellTrapStat
	|	valveStat
	|	setCoordStat
	|	inferMuxStat
	;

layerStat 
	: 'LAYER' layerName=ID  'of' photoresist';' {
		curLayer = $layerName.text;
		if (!d.getLayers().containsKey(curLayer)){
		d.addLayer(curLayer, $photoresist.text);
		}
	}
	;

photoresist 
	:	'P'
	|	'N'
	;
		
portStat: 'PORT' name=ID {
	MLSIPort p = new MLSIPort();
	components.put($name.text, p);
	d.addComponent(p, curLayer);
	tempComponents.add(p);
	}(',' name=ID {
	p = new MLSIPort();
	components.put($name.text, p);
	d.addComponent(p, curLayer);
	tempComponents.add(p);	
	})*('r='r=INT)?';'{
	for(Component c: tempComponents){
		if(c.getClass()==MLSIPort.class){
		p = (MLSIPort) c;
		p.setWidth(2*$r.int);
		}
	}
	tempComponents.clear();
	} 
	;

channelStat
	:  'CHANNEL' name=ID 'from' component1=ID port1=INT 'to' component2=ID port2=INT ('w='w=INT)?';'{
	// fetch component1 and 2 from the hashmaps.  on correct layer, make
		Component c1 = components.get($component1.text);
		ConnectionPoint cp1 = c1.getConnectionPoints().get($port1.int-1);
		Component c2 = components.get($component2.text);
		ConnectionPoint cp2 = c2.getConnectionPoints().get($port2.int-1);
		Channel c = d.addChannel(c1, c2, curLayer);
		c.setChannelWidth($w.int);
		c.setSourcePoint(cp1);
		c.setTargetPoint(cp2);
		c1.setConnection(cp1, c);
		c2.setConnection(cp2, c);
		channels.put($name.text, c);	
	}
	;

nodeStat: 'NODE' name=ID{
	MLSINode n = new MLSINode();
	components.put($name.text, n);
	d.addComponent(n, curLayer);
	}(',' name=ID{
	n = new MLSINode();
	components.put($name.text, n);
	d.addComponent(n, curLayer);
	})*';'
	;

cellTrapStat
	: 'SQUARE CELL TRAP' name=ID {
	MLSICellTrapS ct = new MLSICellTrapS();
	components.put($name.text, ct);
	d.addComponent(ct, curLayer);
	tempComponents.add(ct);
	} (',' name=ID {
	ct = new MLSICellTrapS();
	components.put($name.text, ct);
	d.addComponent(ct, curLayer);
	tempComponents.add(ct);
	})* ('channelWidth='i0=INT)? ('chamberLength='i1=INT)? ('chamberWidth='i2=INT)? ('chamberSpacing='i3=INT)?';' {
	for(Component c: tempComponents){
		if(c.getClass()==MLSICellTrapS.class){
		ct = (MLSICellTrapS) c;
		ct.setFeedingChannelWidth($i0.int);
		ct.setChamberLength($i1.int);
		ct.setChamberWidth($i2.int);
		ct.setChamberSpacing($i3.int);
		ct.updateConnectionPoints();
		}
	}
	tempComponents.clear();
	
	} 
	| 'LONG CELL TRAP' name=ID {
	CellTrap ct = new MLSICellTrapL();
	components.put($name.text, ct);
	d.addComponent(ct, curLayer);
	tempComponents.add(ct);
	} (',' name=ID {
	ct = new MLSICellTrapL();
	components.put($name.text, ct);
	d.addComponent(ct, curLayer);
	tempComponents.add(ct);
	})* ('channelWidth='i0=INT)? ('numChambers='i4=INT)? ('chamberLength='i1=INT)? ('chamberWidth='i2=INT)? ('chamberSpacing='i3=INT)? 'orientation='b=ID';' {
	for(Component c: tempComponents){
		if(c.getClass()==MLSICellTrapL.class){
		ct = (CellTrap) c;
		ct.setFeedingChannelWidth($i0.int);
		ct.setChamberLength($i1.int);
		ct.setChamberWidth($i2.int);
		ct.setChamberSpacing($i3.int);
		ct.setNumChambers($i4.int);
		if($b.text.equals("v")){
			ct.setOrientation(true);
		} else if ($b.text.equals("h")){
			ct.setOrientation(false);
		}
		}
	}
	tempComponents.clear();
	}

	;
	
valveStat 
	: 'VALVE' name=ID 'on' channel=ID ('w='w=INT)? ('l='l=INT)?';'{
	MLSIValve v = new MLSIValve();
	v.setChannel(channels.get($channel.text));
	v.setWidth($w.int);
	v.setLength($l.int);
	d.addComponent(v, curLayer);
	components.put($name.text, v);
	}
	;
	
muxStat	: 'MUX' name=ID {
	ArrayList<Component> inputs = new ArrayList<>();
	ArrayList<Component> outputs = new ArrayList<>();
	} 'in('node=ID 	{
	Component c = components.get($node.text);
	inputs.add(c);
	}(',' node=ID{
	c = components.get($node.text);
	inputs.add(c);
	})+')' 'out('node=ID{
	c = components.get($node.text);
	outputs.add(c);
	Mux m = new MLSIMux(inputs, outputs);
	modules.put($name.text, m);
	d.addModule(m);
	}')' ';'
	| 'MUX' name=ID {
	ArrayList<Component> inputs = new ArrayList<>();
	ArrayList<Component> outputs = new ArrayList<>();
	}'in('node=ID{
	Component c = components.get($node.text);
	inputs.add(c);
	}')' 'out('node=ID{
	c = components.get($node.text);
	outputs.add(c);
	} (','node=ID{
	c = components.get($node.text);
	outputs.add(c);
	})+{
	Mux m = new MLSIMux(inputs, outputs);
	modules.put($name.text, m);
	d.addModule(m);
	}')'';'
	;
	
setCoordStat
	: name=ID{
	Component c = components.get($name.text);
	} ('SET X' x=INT){
	c.setX($x.int);
	} ('SET Y' y=INT){
	c.setY($y.int);
	}';'{
	c.updateConnectionPoints();
	}
	;
	
inferMuxStat //wrapper, only executes if done on control layer
	: 'INFER MUX' ';'{
		if(d.getClass()==MLSIDevice.class){
			MLSIDevice md = (MLSIDevice) d;
			if(!md.hasControlMux()){
			md.inferControlMux();
			}
		}
	}
	;

	
ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;
