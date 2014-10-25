package ufgrammar;

// $ANTLR 3.5 /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g 2014-10-19 23:00:34

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ufNetlistLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public ufNetlistLexer() {} 
	public ufNetlistLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public ufNetlistLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g"; }

	// $ANTLR start "T__7"
	public final void mT__7() throws RecognitionException {
		try {
			int _type = T__7;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:2:6: ( ')' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:2:8: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__7"

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:3:6: ( ',' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:3:8: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:4:6: ( ';' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:4:8: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:5:7: ( 'CHANNEL' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:5:9: 'CHANNEL'
			{
			match("CHANNEL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:6:7: ( 'DEVICE' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:6:9: 'DEVICE'
			{
			match("DEVICE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:7:7: ( 'END LAYER;' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:7:9: 'END LAYER;'
			{
			match("END LAYER;"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:8:7: ( 'INFER MUX' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:8:9: 'INFER MUX'
			{
			match("INFER MUX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:9:7: ( 'LAYER' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:9:9: 'LAYER'
			{
			match("LAYER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:10:7: ( 'LONG CELL TRAP' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:10:9: 'LONG CELL TRAP'
			{
			match("LONG CELL TRAP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:11:7: ( 'MLSI' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:11:9: 'MLSI'
			{
			match("MLSI"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:12:7: ( 'MUX' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:12:9: 'MUX'
			{
			match("MUX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:13:7: ( 'N' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:13:9: 'N'
			{
			match('N'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:14:7: ( 'NODE' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:14:9: 'NODE'
			{
			match("NODE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:15:7: ( 'Other' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:15:9: 'Other'
			{
			match("Other"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:16:7: ( 'P' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:16:9: 'P'
			{
			match('P'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:17:7: ( 'PORT' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:17:9: 'PORT'
			{
			match("PORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:18:7: ( 'SET X' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:18:9: 'SET X'
			{
			match("SET X"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:19:7: ( 'SET Y' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:19:9: 'SET Y'
			{
			match("SET Y"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:20:7: ( 'SQUARE CELL TRAP' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:20:9: 'SQUARE CELL TRAP'
			{
			match("SQUARE CELL TRAP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:21:7: ( 'VALVE' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:21:9: 'VALVE'
			{
			match("VALVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:22:7: ( 'chamberLength=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:22:9: 'chamberLength='
			{
			match("chamberLength="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:23:7: ( 'chamberSpacing=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:23:9: 'chamberSpacing='
			{
			match("chamberSpacing="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:24:7: ( 'chamberWidth=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:24:9: 'chamberWidth='
			{
			match("chamberWidth="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:25:7: ( 'channelWidth=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:25:9: 'channelWidth='
			{
			match("channelWidth="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:26:7: ( 'from' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:26:9: 'from'
			{
			match("from"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:27:7: ( 'in(' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:27:9: 'in('
			{
			match("in("); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:28:7: ( 'l=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:28:9: 'l='
			{
			match("l="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:29:7: ( 'numChambers=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:29:9: 'numChambers='
			{
			match("numChambers="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:30:7: ( 'of' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:30:9: 'of'
			{
			match("of"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:31:7: ( 'on' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:31:9: 'on'
			{
			match("on"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:32:7: ( 'orientation=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:32:9: 'orientation='
			{
			match("orientation="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:33:7: ( 'out(' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:33:9: 'out('
			{
			match("out("); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:34:7: ( 'r=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:34:9: 'r='
			{
			match("r="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:35:7: ( 'to' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:35:9: 'to'
			{
			match("to"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:36:7: ( 'w=' )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:36:9: 'w='
			{
			match("w="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:262:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:262:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:262:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:265:5: ( ( '0' .. '9' )+ )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:265:7: ( '0' .. '9' )+
			{
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:265:7: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:268:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:268:9: ( ' ' | '\\t' | '\\r' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:8: ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | ID | INT | WS )
		int alt3=38;
		alt3 = dfa3.predict(input);
		switch (alt3) {
			case 1 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:10: T__7
				{
				mT__7(); 

				}
				break;
			case 2 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:15: T__8
				{
				mT__8(); 

				}
				break;
			case 3 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:20: T__9
				{
				mT__9(); 

				}
				break;
			case 4 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:25: T__10
				{
				mT__10(); 

				}
				break;
			case 5 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:31: T__11
				{
				mT__11(); 

				}
				break;
			case 6 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:37: T__12
				{
				mT__12(); 

				}
				break;
			case 7 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:43: T__13
				{
				mT__13(); 

				}
				break;
			case 8 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:49: T__14
				{
				mT__14(); 

				}
				break;
			case 9 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:55: T__15
				{
				mT__15(); 

				}
				break;
			case 10 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:61: T__16
				{
				mT__16(); 

				}
				break;
			case 11 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:67: T__17
				{
				mT__17(); 

				}
				break;
			case 12 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:73: T__18
				{
				mT__18(); 

				}
				break;
			case 13 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:79: T__19
				{
				mT__19(); 

				}
				break;
			case 14 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:85: T__20
				{
				mT__20(); 

				}
				break;
			case 15 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:91: T__21
				{
				mT__21(); 

				}
				break;
			case 16 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:97: T__22
				{
				mT__22(); 

				}
				break;
			case 17 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:103: T__23
				{
				mT__23(); 

				}
				break;
			case 18 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:109: T__24
				{
				mT__24(); 

				}
				break;
			case 19 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:115: T__25
				{
				mT__25(); 

				}
				break;
			case 20 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:121: T__26
				{
				mT__26(); 

				}
				break;
			case 21 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:127: T__27
				{
				mT__27(); 

				}
				break;
			case 22 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:133: T__28
				{
				mT__28(); 

				}
				break;
			case 23 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:139: T__29
				{
				mT__29(); 

				}
				break;
			case 24 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:145: T__30
				{
				mT__30(); 

				}
				break;
			case 25 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:151: T__31
				{
				mT__31(); 

				}
				break;
			case 26 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:157: T__32
				{
				mT__32(); 

				}
				break;
			case 27 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:163: T__33
				{
				mT__33(); 

				}
				break;
			case 28 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:169: T__34
				{
				mT__34(); 

				}
				break;
			case 29 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:175: T__35
				{
				mT__35(); 

				}
				break;
			case 30 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:181: T__36
				{
				mT__36(); 

				}
				break;
			case 31 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:187: T__37
				{
				mT__37(); 

				}
				break;
			case 32 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:193: T__38
				{
				mT__38(); 

				}
				break;
			case 33 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:199: T__39
				{
				mT__39(); 

				}
				break;
			case 34 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:205: T__40
				{
				mT__40(); 

				}
				break;
			case 35 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:211: T__41
				{
				mT__41(); 

				}
				break;
			case 36 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:217: ID
				{
				mID(); 

				}
				break;
			case 37 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:220: INT
				{
				mINT(); 

				}
				break;
			case 38 :
				// /Users/cassie/NetBeansProjects/Fluigi/src/ufgrammar/ufNetlist.g:1:224: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA3 dfa3 = new DFA3(this);
	static final String DFA3_eotS =
		"\4\uffff\6\30\1\44\1\30\1\47\13\30\3\uffff\11\30\1\uffff\2\30\1\uffff"+
		"\6\30\1\uffff\1\30\1\111\1\112\2\30\1\uffff\1\115\1\uffff\7\30\1\125\10"+
		"\30\1\uffff\1\30\2\uffff\2\30\1\uffff\2\30\1\uffff\3\30\1\147\1\uffff"+
		"\1\150\1\30\1\152\1\uffff\4\30\1\161\2\30\1\uffff\3\30\1\167\3\uffff\1"+
		"\170\3\uffff\1\30\1\172\2\30\1\uffff\3\30\1\u0080\3\uffff\1\30\1\uffff"+
		"\4\30\1\u0086\2\uffff\4\30\1\uffff\34\30\2\uffff\2\30\3\uffff\1\30\1\uffff";
	static final String DFA3_eofS =
		"\u00ac\uffff";
	static final String DFA3_minS =
		"\1\11\3\uffff\1\110\1\105\2\116\1\101\1\114\1\60\1\164\1\60\1\105\1\101"+
		"\1\150\1\162\1\156\1\75\1\165\1\146\1\75\1\157\1\75\3\uffff\1\101\1\126"+
		"\1\104\1\106\1\131\1\116\1\123\1\130\1\104\1\uffff\1\150\1\122\1\uffff"+
		"\1\124\1\125\1\114\1\141\1\157\1\50\1\uffff\1\155\2\60\1\151\1\164\1\uffff"+
		"\1\60\1\uffff\1\116\1\111\1\40\2\105\1\107\1\111\1\60\1\105\1\145\1\124"+
		"\1\40\1\101\1\126\2\155\1\uffff\1\103\2\uffff\1\145\1\50\1\uffff\1\116"+
		"\1\103\1\uffff\2\122\1\40\1\60\1\uffff\1\60\1\162\1\60\1\130\1\122\1\105"+
		"\1\142\1\156\1\60\1\150\1\156\1\uffff\2\105\1\40\1\60\3\uffff\1\60\3\uffff"+
		"\1\105\1\60\2\145\1\uffff\1\141\1\164\1\114\1\60\3\uffff\1\40\1\uffff"+
		"\1\162\1\154\1\155\1\141\1\60\2\uffff\1\114\1\127\1\142\1\164\1\uffff"+
		"\1\145\1\160\2\151\1\145\1\151\1\156\1\141\2\144\1\162\1\157\1\147\1\143"+
		"\2\164\1\163\1\156\1\164\1\151\2\150\2\75\1\150\1\156\2\75\2\uffff\1\75"+
		"\1\147\3\uffff\1\75\1\uffff";
	static final String DFA3_maxS =
		"\1\172\3\uffff\1\110\1\105\2\116\1\117\1\125\1\172\1\164\1\172\1\121\1"+
		"\101\1\150\1\162\1\156\1\75\2\165\1\75\1\157\1\75\3\uffff\1\101\1\126"+
		"\1\104\1\106\1\131\1\116\1\123\1\130\1\104\1\uffff\1\150\1\122\1\uffff"+
		"\1\124\1\125\1\114\1\141\1\157\1\50\1\uffff\1\155\2\172\1\151\1\164\1"+
		"\uffff\1\172\1\uffff\1\116\1\111\1\40\2\105\1\107\1\111\1\172\1\105\1"+
		"\145\1\124\1\40\1\101\1\126\1\156\1\155\1\uffff\1\103\2\uffff\1\145\1"+
		"\50\1\uffff\1\116\1\103\1\uffff\2\122\1\40\1\172\1\uffff\1\172\1\162\1"+
		"\172\1\131\1\122\1\105\1\142\1\156\1\172\1\150\1\156\1\uffff\2\105\1\40"+
		"\1\172\3\uffff\1\172\3\uffff\1\105\1\172\2\145\1\uffff\1\141\1\164\1\114"+
		"\1\172\3\uffff\1\40\1\uffff\1\162\1\154\1\155\1\141\1\172\2\uffff\2\127"+
		"\1\142\1\164\1\uffff\1\145\1\160\2\151\1\145\1\151\1\156\1\141\2\144\1"+
		"\162\1\157\1\147\1\143\2\164\1\163\1\156\1\164\1\151\2\150\2\75\1\150"+
		"\1\156\2\75\2\uffff\1\75\1\147\3\uffff\1\75\1\uffff";
	static final String DFA3_acceptS =
		"\1\uffff\1\1\1\2\1\3\24\uffff\1\44\1\45\1\46\11\uffff\1\14\2\uffff\1\17"+
		"\6\uffff\1\33\5\uffff\1\41\1\uffff\1\43\20\uffff\1\32\1\uffff\1\35\1\36"+
		"\2\uffff\1\42\2\uffff\1\6\4\uffff\1\13\13\uffff\1\40\4\uffff\1\11\1\12"+
		"\1\15\1\uffff\1\20\1\21\1\22\4\uffff\1\31\4\uffff\1\7\1\10\1\16\1\uffff"+
		"\1\24\5\uffff\1\5\1\23\4\uffff\1\4\34\uffff\1\34\1\37\2\uffff\1\27\1\30"+
		"\1\25\1\uffff\1\26";
	static final String DFA3_specialS =
		"\u00ac\uffff}>";
	static final String[] DFA3_transitionS = {
			"\2\32\2\uffff\1\32\22\uffff\1\32\10\uffff\1\1\2\uffff\1\2\3\uffff\12"+
			"\31\1\uffff\1\3\5\uffff\2\30\1\4\1\5\1\6\3\30\1\7\2\30\1\10\1\11\1\12"+
			"\1\13\1\14\2\30\1\15\2\30\1\16\4\30\4\uffff\1\30\1\uffff\2\30\1\17\2"+
			"\30\1\20\2\30\1\21\2\30\1\22\1\30\1\23\1\24\2\30\1\25\1\30\1\26\2\30"+
			"\1\27\3\30",
			"",
			"",
			"",
			"\1\33",
			"\1\34",
			"\1\35",
			"\1\36",
			"\1\37\15\uffff\1\40",
			"\1\41\10\uffff\1\42",
			"\12\30\7\uffff\16\30\1\43\13\30\4\uffff\1\30\1\uffff\32\30",
			"\1\45",
			"\12\30\7\uffff\16\30\1\46\13\30\4\uffff\1\30\1\uffff\32\30",
			"\1\50\13\uffff\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\1\57",
			"\1\60\7\uffff\1\61\3\uffff\1\62\2\uffff\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"",
			"",
			"",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"",
			"\1\100",
			"\1\101",
			"",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"",
			"\1\110",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\113",
			"\1\114",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134\1\135",
			"\1\136",
			"",
			"\1\137",
			"",
			"",
			"\1\140",
			"\1\141",
			"",
			"\1\142",
			"\1\143",
			"",
			"\1\144",
			"\1\145",
			"\1\146",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\151",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\153\1\154",
			"\1\155",
			"\1\156",
			"\1\157",
			"\1\160",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\162",
			"\1\163",
			"",
			"\1\164",
			"\1\165",
			"\1\166",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"",
			"",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"",
			"",
			"\1\171",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"\1\173",
			"\1\174",
			"",
			"\1\175",
			"\1\176",
			"\1\177",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"",
			"",
			"\1\u0081",
			"",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
			"",
			"",
			"\1\u0087\6\uffff\1\u0088\3\uffff\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"",
			"",
			"\1\u00a9",
			"\1\u00aa",
			"",
			"",
			"",
			"\1\u00ab",
			""
	};

	static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
	static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
	static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
	static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
	static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
	static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
	static final short[][] DFA3_transition;

	static {
		int numStates = DFA3_transitionS.length;
		DFA3_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
		}
	}

	protected class DFA3 extends DFA {

		public DFA3(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 3;
			this.eot = DFA3_eot;
			this.eof = DFA3_eof;
			this.min = DFA3_min;
			this.max = DFA3_max;
			this.accept = DFA3_accept;
			this.special = DFA3_special;
			this.transition = DFA3_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | ID | INT | WS );";
		}
	}

}
