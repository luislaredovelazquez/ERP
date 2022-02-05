/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paedeias.controladores.facturacion;

/**
 *
 * @author ALL
 */
import com.tsp.interconecta.ws.InterconectaWs;
import com.tsp.interconecta.ws.InterconectaWsService;
import com.tsp.interconecta.ws.WsInsertaClienteResp;

public class agregarContribuyente {

public void agregar()
{
     InterconectaWsService wss = new InterconectaWsService();
     InterconectaWs ws = wss.getInterconectaWsPort();
     
     WsInsertaClienteResp resp = ws.otorgarAccesoContribuyente("CME000515HX7_1", "614227647743163434386083", "CME000515HX7", "COLISION Y MECANICA, S.A. DE C.V.");
     
    
     System.out.println(resp.getNumError());  
     System.out.println(resp.getErrorMessage());  
     System.out.println(resp.isIsError());  
}
    
public static void main(String[] args) {
agregarContribuyente ac = new agregarContribuyente();
ac.agregar();
}    
}
