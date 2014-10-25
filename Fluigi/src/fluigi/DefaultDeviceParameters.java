/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluigi;

/**
 *
 * @author cassie
 */
public class DefaultDeviceParameters {
    public static final double DEFAULT_PT_TO_UM = 0.00283464567;
    public static final double DEFAULT_UM_TO_PT = 351.45;
    public static final int DEFAULT_LAMBDA = 10; //microns
    public static final int DEFAULT_PORT_RADIUS = DEFAULT_LAMBDA*10;
    public static final int DEFAULT_FLOW_CHANNEL_WIDTH = DEFAULT_LAMBDA * 10;
    public static final int DEFAULT_CONTROL_CHANNEL_WIDTH = DEFAULT_LAMBDA * 5;
    public static final int DEFAULT_VALVE_WIDTH = DEFAULT_FLOW_CHANNEL_WIDTH;
    public static final int DEFAULT_VALVE_LENGTH = DEFAULT_FLOW_CHANNEL_WIDTH*2;
}
