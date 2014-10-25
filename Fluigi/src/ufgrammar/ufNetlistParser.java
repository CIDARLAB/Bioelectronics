// $ANTLR 3.5 /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g 2014-10-19 23:00:34

package ufgrammar;
import netlist.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ufNetlistParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "WS", "')'", "','", 
		"';'", "'CHANNEL'", "'DEVICE'", "'END LAYER;'", "'INFER MUX'", "'LAYER'", 
		"'LONG CELL TRAP'", "'MLSI'", "'MUX'", "'N'", "'NODE'", "'Other'", "'P'", 
		"'PORT'", "'SET X'", "'SET Y'", "'SQUARE CELL TRAP'", "'VALVE'", "'chamberLength='", 
		"'chamberSpacing='", "'chamberWidth='", "'channelWidth='", "'from'", "'in('", 
		"'l='", "'numChambers='", "'of'", "'on'", "'orientation='", "'out('", 
		"'r='", "'to'", "'w='"
	};
	public static final int EOF=-1;
	public static final int T__7=7;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int ID=4;
	public static final int INT=5;
	public static final int WS=6;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public ufNetlistParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public ufNetlistParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return ufNetlistParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g"; }


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
		




	// $ANTLR start "netlist"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:40:1: netlist : header ( block )+ EOF ;
	public final void netlist() throws RecognitionException {
		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:40:9: ( header ( block )+ EOF )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:40:11: header ( block )+ EOF
			{
			pushFollow(FOLLOW_header_in_netlist22);
			header();
			state._fsp--;

			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:40:18: ( block )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==14) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:40:19: block
					{
					pushFollow(FOLLOW_block_in_netlist25);
					block();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			match(input,EOF,FOLLOW_EOF_in_netlist30); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "netlist"



	// $ANTLR start "header"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:44:1: header : 'DEVICE' name= ID 'of' tech ';' ;
	public final void header() throws RecognitionException {
		Token name=null;
		ParserRuleReturnScope tech1 =null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:44:9: ( 'DEVICE' name= ID 'of' tech ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:44:11: 'DEVICE' name= ID 'of' tech ';'
			{
			match(input,11,FOLLOW_11_in_header41); 
			name=(Token)match(input,ID,FOLLOW_ID_in_header45); 
			match(input,35,FOLLOW_35_in_header47); 
			pushFollow(FOLLOW_tech_in_header49);
			tech1=tech();
			state._fsp--;

			match(input,9,FOLLOW_9_in_header51); 

			            techType = (tech1!=null?input.toString(tech1.start,tech1.stop):null);
			            if (techType.equals("MLSI")){
			            	d = new MLSIDevice((name!=null?name.getText():null));
			            } else {
			            //throw some sort of 'this isn'st suppoerted error
			            }
			            
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "header"


	public static class tech_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tech"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:57:1: tech : ( 'MLSI' | 'Other' );
	public final ufNetlistParser.tech_return tech() throws RecognitionException {
		ufNetlistParser.tech_return retval = new ufNetlistParser.tech_return();
		retval.start = input.LT(1);

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:57:7: ( 'MLSI' | 'Other' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:
			{
			if ( input.LA(1)==16||input.LA(1)==20 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tech"



	// $ANTLR start "block"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:61:1: block : layerStat (s= stat )+ 'END LAYER;' ;
	public final void block() throws RecognitionException {
		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:61:8: ( layerStat (s= stat )+ 'END LAYER;' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:61:10: layerStat (s= stat )+ 'END LAYER;'
			{
			pushFollow(FOLLOW_layerStat_in_block82);
			layerStat();
			state._fsp--;

			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:61:20: (s= stat )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==ID||LA2_0==10||LA2_0==13||LA2_0==15||LA2_0==17||LA2_0==19||LA2_0==22||(LA2_0 >= 25 && LA2_0 <= 26)) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:61:21: s= stat
					{
					pushFollow(FOLLOW_stat_in_block87);
					stat();
					state._fsp--;

					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			match(input,12,FOLLOW_12_in_block91); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "block"



	// $ANTLR start "stat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:63:1: stat : ( portStat | channelStat | muxStat | nodeStat | cellTrapStat | valveStat | setCoordStat | inferMuxStat );
	public final void stat() throws RecognitionException {
		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:63:7: ( portStat | channelStat | muxStat | nodeStat | cellTrapStat | valveStat | setCoordStat | inferMuxStat )
			int alt3=8;
			switch ( input.LA(1) ) {
			case 22:
				{
				alt3=1;
				}
				break;
			case 10:
				{
				alt3=2;
				}
				break;
			case 17:
				{
				alt3=3;
				}
				break;
			case 19:
				{
				alt3=4;
				}
				break;
			case 15:
			case 25:
				{
				alt3=5;
				}
				break;
			case 26:
				{
				alt3=6;
				}
				break;
			case ID:
				{
				alt3=7;
				}
				break;
			case 13:
				{
				alt3=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}
			switch (alt3) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:63:9: portStat
					{
					pushFollow(FOLLOW_portStat_in_stat100);
					portStat();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:64:4: channelStat
					{
					pushFollow(FOLLOW_channelStat_in_stat105);
					channelStat();
					state._fsp--;

					}
					break;
				case 3 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:65:4: muxStat
					{
					pushFollow(FOLLOW_muxStat_in_stat110);
					muxStat();
					state._fsp--;

					}
					break;
				case 4 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:66:4: nodeStat
					{
					pushFollow(FOLLOW_nodeStat_in_stat115);
					nodeStat();
					state._fsp--;

					}
					break;
				case 5 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:67:4: cellTrapStat
					{
					pushFollow(FOLLOW_cellTrapStat_in_stat120);
					cellTrapStat();
					state._fsp--;

					}
					break;
				case 6 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:68:4: valveStat
					{
					pushFollow(FOLLOW_valveStat_in_stat125);
					valveStat();
					state._fsp--;

					}
					break;
				case 7 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:69:4: setCoordStat
					{
					pushFollow(FOLLOW_setCoordStat_in_stat130);
					setCoordStat();
					state._fsp--;

					}
					break;
				case 8 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:70:4: inferMuxStat
					{
					pushFollow(FOLLOW_inferMuxStat_in_stat135);
					inferMuxStat();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "stat"



	// $ANTLR start "layerStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:73:1: layerStat : 'LAYER' layerName= ID 'of' photoresist ';' ;
	public final void layerStat() throws RecognitionException {
		Token layerName=null;
		ParserRuleReturnScope photoresist2 =null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:74:2: ( 'LAYER' layerName= ID 'of' photoresist ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:74:4: 'LAYER' layerName= ID 'of' photoresist ';'
			{
			match(input,14,FOLLOW_14_in_layerStat147); 
			layerName=(Token)match(input,ID,FOLLOW_ID_in_layerStat151); 
			match(input,35,FOLLOW_35_in_layerStat154); 
			pushFollow(FOLLOW_photoresist_in_layerStat156);
			photoresist2=photoresist();
			state._fsp--;

			match(input,9,FOLLOW_9_in_layerStat157); 

					curLayer = (layerName!=null?layerName.getText():null);
					if (!d.getLayers().containsKey(curLayer)){
					d.addLayer(curLayer, (photoresist2!=null?input.toString(photoresist2.start,photoresist2.stop):null));
					}
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "layerStat"


	public static class photoresist_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "photoresist"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:82:1: photoresist : ( 'P' | 'N' );
	public final ufNetlistParser.photoresist_return photoresist() throws RecognitionException {
		ufNetlistParser.photoresist_return retval = new ufNetlistParser.photoresist_return();
		retval.start = input.LT(1);

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:83:2: ( 'P' | 'N' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:
			{
			if ( input.LA(1)==18||input.LA(1)==21 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "photoresist"



	// $ANTLR start "portStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:87:1: portStat : 'PORT' name= ID ( ',' name= ID )* ( 'r=' r= INT )? ';' ;
	public final void portStat() throws RecognitionException {
		Token name=null;
		Token r=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:87:9: ( 'PORT' name= ID ( ',' name= ID )* ( 'r=' r= INT )? ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:87:11: 'PORT' name= ID ( ',' name= ID )* ( 'r=' r= INT )? ';'
			{
			match(input,22,FOLLOW_22_in_portStat187); 
			name=(Token)match(input,ID,FOLLOW_ID_in_portStat191); 

				MLSIPort p = new MLSIPort();
				components.put((name!=null?name.getText():null), p);
				d.addComponent(p, curLayer);
				tempComponents.add(p);
				
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:92:3: ( ',' name= ID )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==8) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:92:4: ',' name= ID
					{
					match(input,8,FOLLOW_8_in_portStat195); 
					name=(Token)match(input,ID,FOLLOW_ID_in_portStat199); 

						p = new MLSIPort();
						components.put((name!=null?name.getText():null), p);
						d.addComponent(p, curLayer);
						tempComponents.add(p);	
						
					}
					break;

				default :
					break loop4;
				}
			}

			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:97:5: ( 'r=' r= INT )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==39) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:97:6: 'r=' r= INT
					{
					match(input,39,FOLLOW_39_in_portStat205); 
					r=(Token)match(input,INT,FOLLOW_INT_in_portStat208); 
					}
					break;

			}

			match(input,9,FOLLOW_9_in_portStat211); 

				for(Component c: tempComponents){
					if(c.getClass()==MLSIPort.class){
					p = (MLSIPort) c;
					p.setWidth(2*(r!=null?Integer.valueOf(r.getText()):0));
					}
				}
				tempComponents.clear();
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "portStat"



	// $ANTLR start "channelStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:108:1: channelStat : 'CHANNEL' name= ID 'from' component1= ID port1= INT 'to' component2= ID port2= INT ( 'w=' w= INT )? ';' ;
	public final void channelStat() throws RecognitionException {
		Token name=null;
		Token component1=null;
		Token port1=null;
		Token component2=null;
		Token port2=null;
		Token w=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:109:2: ( 'CHANNEL' name= ID 'from' component1= ID port1= INT 'to' component2= ID port2= INT ( 'w=' w= INT )? ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:109:5: 'CHANNEL' name= ID 'from' component1= ID port1= INT 'to' component2= ID port2= INT ( 'w=' w= INT )? ';'
			{
			match(input,10,FOLLOW_10_in_channelStat225); 
			name=(Token)match(input,ID,FOLLOW_ID_in_channelStat229); 
			match(input,31,FOLLOW_31_in_channelStat231); 
			component1=(Token)match(input,ID,FOLLOW_ID_in_channelStat235); 
			port1=(Token)match(input,INT,FOLLOW_INT_in_channelStat239); 
			match(input,40,FOLLOW_40_in_channelStat241); 
			component2=(Token)match(input,ID,FOLLOW_ID_in_channelStat245); 
			port2=(Token)match(input,INT,FOLLOW_INT_in_channelStat249); 
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:109:83: ( 'w=' w= INT )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==41) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:109:84: 'w=' w= INT
					{
					match(input,41,FOLLOW_41_in_channelStat252); 
					w=(Token)match(input,INT,FOLLOW_INT_in_channelStat255); 
					}
					break;

			}

			match(input,9,FOLLOW_9_in_channelStat258); 

				// fetch component1 and 2 from the hashmaps.  on correct layer, make
					Component c1 = components.get((component1!=null?component1.getText():null));
					Terminal cp1 = c1.getConnectionPoints().get((port1!=null?Integer.valueOf(port1.getText()):0)-1);
					Component c2 = components.get((component2!=null?component2.getText():null));
					Terminal cp2 = c2.getConnectionPoints().get((port2!=null?Integer.valueOf(port2.getText()):0)-1);
					Channel c = d.addChannel(c1, c2, curLayer);
					c.setChannelWidth((w!=null?Integer.valueOf(w.getText()):0));
					c.setSourcePoint(cp1);
					c.setTargetPoint(cp2);
					c1.setConnection(cp1, c);
					c2.setConnection(cp2, c);
					channels.put((name!=null?name.getText():null), c);	
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "channelStat"



	// $ANTLR start "nodeStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:125:1: nodeStat : 'NODE' name= ID ( ',' name= ID )* ';' ;
	public final void nodeStat() throws RecognitionException {
		Token name=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:125:9: ( 'NODE' name= ID ( ',' name= ID )* ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:125:11: 'NODE' name= ID ( ',' name= ID )* ';'
			{
			match(input,19,FOLLOW_19_in_nodeStat268); 
			name=(Token)match(input,ID,FOLLOW_ID_in_nodeStat272); 

				MLSINode n = new MLSINode();
				components.put((name!=null?name.getText():null), n);
				d.addComponent(n, curLayer);
				
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:129:3: ( ',' name= ID )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==8) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:129:4: ',' name= ID
					{
					match(input,8,FOLLOW_8_in_nodeStat275); 
					name=(Token)match(input,ID,FOLLOW_ID_in_nodeStat279); 

						n = new MLSINode();
						components.put((name!=null?name.getText():null), n);
						d.addComponent(n, curLayer);
						
					}
					break;

				default :
					break loop7;
				}
			}

			match(input,9,FOLLOW_9_in_nodeStat283); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "nodeStat"



	// $ANTLR start "cellTrapStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:136:1: cellTrapStat : ( 'SQUARE CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? ';' | 'LONG CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'numChambers=' i4= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? 'orientation=' b= ID ';' );
	public final void cellTrapStat() throws RecognitionException {
		Token name=null;
		Token i0=null;
		Token i1=null;
		Token i2=null;
		Token i3=null;
		Token i4=null;
		Token b=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:137:2: ( 'SQUARE CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? ';' | 'LONG CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'numChambers=' i4= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? 'orientation=' b= ID ';' )
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==25) ) {
				alt19=1;
			}
			else if ( (LA19_0==15) ) {
				alt19=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:137:4: 'SQUARE CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? ';'
					{
					match(input,25,FOLLOW_25_in_cellTrapStat294); 
					name=(Token)match(input,ID,FOLLOW_ID_in_cellTrapStat298); 

						MLSICellTrapS ct = new MLSICellTrapS();
						components.put((name!=null?name.getText():null), ct);
						d.addComponent(ct, curLayer);
						tempComponents.add(ct);
						
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:142:4: ( ',' name= ID )*
					loop8:
					while (true) {
						int alt8=2;
						int LA8_0 = input.LA(1);
						if ( (LA8_0==8) ) {
							alt8=1;
						}

						switch (alt8) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:142:5: ',' name= ID
							{
							match(input,8,FOLLOW_8_in_cellTrapStat303); 
							name=(Token)match(input,ID,FOLLOW_ID_in_cellTrapStat307); 

								ct = new MLSICellTrapS();
								components.put((name!=null?name.getText():null), ct);
								d.addComponent(ct, curLayer);
								tempComponents.add(ct);
								
							}
							break;

						default :
							break loop8;
						}
					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:6: ( 'channelWidth=' i0= INT )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==30) ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:7: 'channelWidth=' i0= INT
							{
							match(input,30,FOLLOW_30_in_cellTrapStat314); 
							i0=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat317); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:31: ( 'chamberLength=' i1= INT )?
					int alt10=2;
					int LA10_0 = input.LA(1);
					if ( (LA10_0==27) ) {
						alt10=1;
					}
					switch (alt10) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:32: 'chamberLength=' i1= INT
							{
							match(input,27,FOLLOW_27_in_cellTrapStat322); 
							i1=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat325); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:57: ( 'chamberWidth=' i2= INT )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( (LA11_0==29) ) {
						alt11=1;
					}
					switch (alt11) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:58: 'chamberWidth=' i2= INT
							{
							match(input,29,FOLLOW_29_in_cellTrapStat330); 
							i2=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat333); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:82: ( 'chamberSpacing=' i3= INT )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==28) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:147:83: 'chamberSpacing=' i3= INT
							{
							match(input,28,FOLLOW_28_in_cellTrapStat338); 
							i3=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat341); 
							}
							break;

					}

					match(input,9,FOLLOW_9_in_cellTrapStat344); 

						for(Component c: tempComponents){
							if(c.getClass()==MLSICellTrapS.class){
							ct = (MLSICellTrapS) c;
							ct.setFeedingChannelWidth((i0!=null?Integer.valueOf(i0.getText()):0));
							ct.setChamberLength((i1!=null?Integer.valueOf(i1.getText()):0));
							ct.setChamberWidth((i2!=null?Integer.valueOf(i2.getText()):0));
							ct.setChamberSpacing((i3!=null?Integer.valueOf(i3.getText()):0));
							ct.updateConnectionPoints();
							}
						}
						tempComponents.clear();
						
						
					}
					break;
				case 2 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:161:4: 'LONG CELL TRAP' name= ID ( ',' name= ID )* ( 'channelWidth=' i0= INT )? ( 'numChambers=' i4= INT )? ( 'chamberLength=' i1= INT )? ( 'chamberWidth=' i2= INT )? ( 'chamberSpacing=' i3= INT )? 'orientation=' b= ID ';'
					{
					match(input,15,FOLLOW_15_in_cellTrapStat352); 
					name=(Token)match(input,ID,FOLLOW_ID_in_cellTrapStat356); 

						CellTrap ct = new MLSICellTrapL();
						components.put((name!=null?name.getText():null), ct);
						d.addComponent(ct, curLayer);
						tempComponents.add(ct);
						
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:166:4: ( ',' name= ID )*
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==8) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:166:5: ',' name= ID
							{
							match(input,8,FOLLOW_8_in_cellTrapStat361); 
							name=(Token)match(input,ID,FOLLOW_ID_in_cellTrapStat365); 

								ct = new MLSICellTrapL();
								components.put((name!=null?name.getText():null), ct);
								d.addComponent(ct, curLayer);
								tempComponents.add(ct);
								
							}
							break;

						default :
							break loop13;
						}
					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:6: ( 'channelWidth=' i0= INT )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==30) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:7: 'channelWidth=' i0= INT
							{
							match(input,30,FOLLOW_30_in_cellTrapStat372); 
							i0=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat375); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:31: ( 'numChambers=' i4= INT )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==34) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:32: 'numChambers=' i4= INT
							{
							match(input,34,FOLLOW_34_in_cellTrapStat380); 
							i4=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat383); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:55: ( 'chamberLength=' i1= INT )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0==27) ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:56: 'chamberLength=' i1= INT
							{
							match(input,27,FOLLOW_27_in_cellTrapStat388); 
							i1=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat391); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:81: ( 'chamberWidth=' i2= INT )?
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0==29) ) {
						alt17=1;
					}
					switch (alt17) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:82: 'chamberWidth=' i2= INT
							{
							match(input,29,FOLLOW_29_in_cellTrapStat396); 
							i2=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat399); 
							}
							break;

					}

					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:106: ( 'chamberSpacing=' i3= INT )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==28) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:171:107: 'chamberSpacing=' i3= INT
							{
							match(input,28,FOLLOW_28_in_cellTrapStat404); 
							i3=(Token)match(input,INT,FOLLOW_INT_in_cellTrapStat407); 
							}
							break;

					}

					match(input,37,FOLLOW_37_in_cellTrapStat411); 
					b=(Token)match(input,ID,FOLLOW_ID_in_cellTrapStat414); 
					match(input,9,FOLLOW_9_in_cellTrapStat415); 

						for(Component c: tempComponents){
							if(c.getClass()==MLSICellTrapL.class){
							ct = (CellTrap) c;
							ct.setFeedingChannelWidth((i0!=null?Integer.valueOf(i0.getText()):0));
							ct.setChamberLength((i1!=null?Integer.valueOf(i1.getText()):0));
							ct.setChamberWidth((i2!=null?Integer.valueOf(i2.getText()):0));
							ct.setChamberSpacing((i3!=null?Integer.valueOf(i3.getText()):0));
							ct.setNumChambers((i4!=null?Integer.valueOf(i4.getText()):0));
							if((b!=null?b.getText():null).equals("v")){
								ct.setOrientation(true);
							} else if ((b!=null?b.getText():null).equals("h")){
								ct.setOrientation(false);
							}
							}
						}
						tempComponents.clear();
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cellTrapStat"



	// $ANTLR start "valveStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:192:1: valveStat : 'VALVE' name= ID 'on' channel= ID ( 'w=' w= INT )? ( 'l=' l= INT )? ';' ;
	public final void valveStat() throws RecognitionException {
		Token name=null;
		Token channel=null;
		Token w=null;
		Token l=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:2: ( 'VALVE' name= ID 'on' channel= ID ( 'w=' w= INT )? ( 'l=' l= INT )? ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:4: 'VALVE' name= ID 'on' channel= ID ( 'w=' w= INT )? ( 'l=' l= INT )? ';'
			{
			match(input,26,FOLLOW_26_in_valveStat431); 
			name=(Token)match(input,ID,FOLLOW_ID_in_valveStat435); 
			match(input,36,FOLLOW_36_in_valveStat437); 
			channel=(Token)match(input,ID,FOLLOW_ID_in_valveStat441); 
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:36: ( 'w=' w= INT )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==41) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:37: 'w=' w= INT
					{
					match(input,41,FOLLOW_41_in_valveStat444); 
					w=(Token)match(input,INT,FOLLOW_INT_in_valveStat447); 
					}
					break;

			}

			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:49: ( 'l=' l= INT )?
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==33) ) {
				alt21=1;
			}
			switch (alt21) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:193:50: 'l=' l= INT
					{
					match(input,33,FOLLOW_33_in_valveStat452); 
					l=(Token)match(input,INT,FOLLOW_INT_in_valveStat455); 
					}
					break;

			}

			match(input,9,FOLLOW_9_in_valveStat458); 

				MLSIValve v = new MLSIValve();
				v.setChannel(channels.get((channel!=null?channel.getText():null)));
				v.setWidth((w!=null?Integer.valueOf(w.getText()):0));
				v.setLength((l!=null?Integer.valueOf(l.getText()):0));
				d.addComponent(v, curLayer);
				components.put((name!=null?name.getText():null), v);
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "valveStat"



	// $ANTLR start "muxStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:203:1: muxStat : ( 'MUX' name= ID 'in(' node= ID ( ',' node= ID )+ ')' 'out(' node= ID ')' ';' | 'MUX' name= ID 'in(' node= ID ')' 'out(' node= ID ( ',' node= ID )+ ')' ';' );
	public final void muxStat() throws RecognitionException {
		Token name=null;
		Token node=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:203:9: ( 'MUX' name= ID 'in(' node= ID ( ',' node= ID )+ ')' 'out(' node= ID ')' ';' | 'MUX' name= ID 'in(' node= ID ')' 'out(' node= ID ( ',' node= ID )+ ')' ';' )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==17) ) {
				int LA24_1 = input.LA(2);
				if ( (LA24_1==ID) ) {
					int LA24_2 = input.LA(3);
					if ( (LA24_2==32) ) {
						int LA24_3 = input.LA(4);
						if ( (LA24_3==ID) ) {
							int LA24_4 = input.LA(5);
							if ( (LA24_4==8) ) {
								alt24=1;
							}
							else if ( (LA24_4==7) ) {
								alt24=2;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 24, 4, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 24, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 24, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:203:11: 'MUX' name= ID 'in(' node= ID ( ',' node= ID )+ ')' 'out(' node= ID ')' ';'
					{
					match(input,17,FOLLOW_17_in_muxStat470); 
					name=(Token)match(input,ID,FOLLOW_ID_in_muxStat474); 

						ArrayList<Component> inputs = new ArrayList<>();
						ArrayList<Component> outputs = new ArrayList<>();
						
					match(input,32,FOLLOW_32_in_muxStat478); 
					node=(Token)match(input,ID,FOLLOW_ID_in_muxStat481); 

						Component c = components.get((node!=null?node.getText():null));
						inputs.add(c);
						
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:209:3: ( ',' node= ID )+
					int cnt22=0;
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( (LA22_0==8) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:209:4: ',' node= ID
							{
							match(input,8,FOLLOW_8_in_muxStat486); 
							node=(Token)match(input,ID,FOLLOW_ID_in_muxStat490); 

								c = components.get((node!=null?node.getText():null));
								inputs.add(c);
								
							}
							break;

						default :
							if ( cnt22 >= 1 ) break loop22;
							EarlyExitException eee = new EarlyExitException(22, input);
							throw eee;
						}
						cnt22++;
					}

					match(input,7,FOLLOW_7_in_muxStat494); 
					match(input,38,FOLLOW_38_in_muxStat496); 
					node=(Token)match(input,ID,FOLLOW_ID_in_muxStat499); 

						c = components.get((node!=null?node.getText():null));
						outputs.add(c);
						Mux m = new MLSIMux(inputs, outputs);
						modules.put((name!=null?name.getText():null), m);
						d.addModule(m);
						
					match(input,7,FOLLOW_7_in_muxStat501); 
					match(input,9,FOLLOW_9_in_muxStat503); 
					}
					break;
				case 2 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:219:4: 'MUX' name= ID 'in(' node= ID ')' 'out(' node= ID ( ',' node= ID )+ ')' ';'
					{
					match(input,17,FOLLOW_17_in_muxStat508); 
					name=(Token)match(input,ID,FOLLOW_ID_in_muxStat512); 

						ArrayList<Component> inputs = new ArrayList<>();
						ArrayList<Component> outputs = new ArrayList<>();
						
					match(input,32,FOLLOW_32_in_muxStat515); 
					node=(Token)match(input,ID,FOLLOW_ID_in_muxStat518); 

						Component c = components.get((node!=null?node.getText():null));
						inputs.add(c);
						
					match(input,7,FOLLOW_7_in_muxStat520); 
					match(input,38,FOLLOW_38_in_muxStat522); 
					node=(Token)match(input,ID,FOLLOW_ID_in_muxStat525); 

						c = components.get((node!=null?node.getText():null));
						outputs.add(c);
						
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:228:4: ( ',' node= ID )+
					int cnt23=0;
					loop23:
					while (true) {
						int alt23=2;
						int LA23_0 = input.LA(1);
						if ( (LA23_0==8) ) {
							alt23=1;
						}

						switch (alt23) {
						case 1 :
							// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:228:5: ',' node= ID
							{
							match(input,8,FOLLOW_8_in_muxStat529); 
							node=(Token)match(input,ID,FOLLOW_ID_in_muxStat532); 

								c = components.get((node!=null?node.getText():null));
								outputs.add(c);
								
							}
							break;

						default :
							if ( cnt23 >= 1 ) break loop23;
							EarlyExitException eee = new EarlyExitException(23, input);
							throw eee;
						}
						cnt23++;
					}


						Mux m = new MLSIMux(inputs, outputs);
						modules.put((name!=null?name.getText():null), m);
						d.addModule(m);
						
					match(input,7,FOLLOW_7_in_muxStat537); 
					match(input,9,FOLLOW_9_in_muxStat538); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "muxStat"



	// $ANTLR start "setCoordStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:238:1: setCoordStat : name= ID ( 'SET X' x= INT ) ( 'SET Y' y= INT ) ';' ;
	public final void setCoordStat() throws RecognitionException {
		Token name=null;
		Token x=null;
		Token y=null;

		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:239:2: (name= ID ( 'SET X' x= INT ) ( 'SET Y' y= INT ) ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:239:4: name= ID ( 'SET X' x= INT ) ( 'SET Y' y= INT ) ';'
			{
			name=(Token)match(input,ID,FOLLOW_ID_in_setCoordStat552); 

				Component c = components.get((name!=null?name.getText():null));
				
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:241:4: ( 'SET X' x= INT )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:241:5: 'SET X' x= INT
			{
			match(input,23,FOLLOW_23_in_setCoordStat556); 
			x=(Token)match(input,INT,FOLLOW_INT_in_setCoordStat560); 
			}


				c.setX((x!=null?Integer.valueOf(x.getText()):0));
				
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:243:4: ( 'SET Y' y= INT )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:243:5: 'SET Y' y= INT
			{
			match(input,24,FOLLOW_24_in_setCoordStat565); 
			y=(Token)match(input,INT,FOLLOW_INT_in_setCoordStat569); 
			}


				c.setY((y!=null?Integer.valueOf(y.getText()):0));
				
			match(input,9,FOLLOW_9_in_setCoordStat572); 

				c.updateConnectionPoints();
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "setCoordStat"



	// $ANTLR start "inferMuxStat"
	// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:250:1: inferMuxStat : 'INFER MUX' ';' ;
	public final void inferMuxStat() throws RecognitionException {
		try {
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:251:2: ( 'INFER MUX' ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:251:4: 'INFER MUX' ';'
			{
			match(input,13,FOLLOW_13_in_inferMuxStat586); 
			match(input,9,FOLLOW_9_in_inferMuxStat588); 

					if(d.getClass()==MLSIDevice.class){
						MLSIDevice md = (MLSIDevice) d;
						if(!md.hasControlMux()){
						md.inferControlMux();
						}
					}
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "inferMuxStat"

	// Delegated rules



	public static final BitSet FOLLOW_header_in_netlist22 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_block_in_netlist25 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_EOF_in_netlist30 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_header41 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_header45 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_header47 = new BitSet(new long[]{0x0000000000110000L});
	public static final BitSet FOLLOW_tech_in_header49 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_header51 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_layerStat_in_block82 = new BitSet(new long[]{0x00000000064AA410L});
	public static final BitSet FOLLOW_stat_in_block87 = new BitSet(new long[]{0x00000000064AB410L});
	public static final BitSet FOLLOW_12_in_block91 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_portStat_in_stat100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_channelStat_in_stat105 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_muxStat_in_stat110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nodeStat_in_stat115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cellTrapStat_in_stat120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_valveStat_in_stat125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_setCoordStat_in_stat130 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_inferMuxStat_in_stat135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_layerStat147 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_layerStat151 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_layerStat154 = new BitSet(new long[]{0x0000000000240000L});
	public static final BitSet FOLLOW_photoresist_in_layerStat156 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_layerStat157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_portStat187 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_portStat191 = new BitSet(new long[]{0x0000008000000300L});
	public static final BitSet FOLLOW_8_in_portStat195 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_portStat199 = new BitSet(new long[]{0x0000008000000300L});
	public static final BitSet FOLLOW_39_in_portStat205 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_portStat208 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_portStat211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_channelStat225 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_channelStat229 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_channelStat231 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_channelStat235 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_channelStat239 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_channelStat241 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_channelStat245 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_channelStat249 = new BitSet(new long[]{0x0000020000000200L});
	public static final BitSet FOLLOW_41_in_channelStat252 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_channelStat255 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_channelStat258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_nodeStat268 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_nodeStat272 = new BitSet(new long[]{0x0000000000000300L});
	public static final BitSet FOLLOW_8_in_nodeStat275 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_nodeStat279 = new BitSet(new long[]{0x0000000000000300L});
	public static final BitSet FOLLOW_9_in_nodeStat283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_cellTrapStat294 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_cellTrapStat298 = new BitSet(new long[]{0x0000000078000300L});
	public static final BitSet FOLLOW_8_in_cellTrapStat303 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_cellTrapStat307 = new BitSet(new long[]{0x0000000078000300L});
	public static final BitSet FOLLOW_30_in_cellTrapStat314 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat317 = new BitSet(new long[]{0x0000000038000200L});
	public static final BitSet FOLLOW_27_in_cellTrapStat322 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat325 = new BitSet(new long[]{0x0000000030000200L});
	public static final BitSet FOLLOW_29_in_cellTrapStat330 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat333 = new BitSet(new long[]{0x0000000010000200L});
	public static final BitSet FOLLOW_28_in_cellTrapStat338 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat341 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_cellTrapStat344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_cellTrapStat352 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_cellTrapStat356 = new BitSet(new long[]{0x0000002478000100L});
	public static final BitSet FOLLOW_8_in_cellTrapStat361 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_cellTrapStat365 = new BitSet(new long[]{0x0000002478000100L});
	public static final BitSet FOLLOW_30_in_cellTrapStat372 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat375 = new BitSet(new long[]{0x0000002438000000L});
	public static final BitSet FOLLOW_34_in_cellTrapStat380 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat383 = new BitSet(new long[]{0x0000002038000000L});
	public static final BitSet FOLLOW_27_in_cellTrapStat388 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat391 = new BitSet(new long[]{0x0000002030000000L});
	public static final BitSet FOLLOW_29_in_cellTrapStat396 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat399 = new BitSet(new long[]{0x0000002010000000L});
	public static final BitSet FOLLOW_28_in_cellTrapStat404 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_cellTrapStat407 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_37_in_cellTrapStat411 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_cellTrapStat414 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_cellTrapStat415 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_valveStat431 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_valveStat435 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_valveStat437 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_valveStat441 = new BitSet(new long[]{0x0000020200000200L});
	public static final BitSet FOLLOW_41_in_valveStat444 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_valveStat447 = new BitSet(new long[]{0x0000000200000200L});
	public static final BitSet FOLLOW_33_in_valveStat452 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_valveStat455 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_valveStat458 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_muxStat470 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat474 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_muxStat478 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat481 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_muxStat486 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat490 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_7_in_muxStat494 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_muxStat496 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat499 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_muxStat501 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_muxStat503 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_muxStat508 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat512 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_32_in_muxStat515 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat518 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_7_in_muxStat520 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_38_in_muxStat522 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat525 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_muxStat529 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ID_in_muxStat532 = new BitSet(new long[]{0x0000000000000180L});
	public static final BitSet FOLLOW_7_in_muxStat537 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_muxStat538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_setCoordStat552 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_setCoordStat556 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_setCoordStat560 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_setCoordStat565 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_INT_in_setCoordStat569 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_setCoordStat572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_inferMuxStat586 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_inferMuxStat588 = new BitSet(new long[]{0x0000000000000002L});
}
