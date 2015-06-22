/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;

/**
 *
 * @author Kamil
 */
public class FacebookConnector {
    private final String pageAccessToken = "CAACEdEose0cBAKUimgUGzULAuaelr82WaNzRUlRUV8VzzVE1WcEJk5YNJfAUFkHibudyM7U8yDRIEwhyJMxX6Wq27qDambdePYG8kpZA9kP06Xh0CtKmwPAko2tnBZC9k6QfrOEZBZCxZC2Cu4ZB9ANX1NiP3RsJWXZAtB370IZAyoj1v8jxbyPteiEspbk812PSh7D3cZAskHYwK1mGKugn8T3ECXm68USEZD";
    private final String pageID = "10204616555317571";
    private FacebookClient fbClient;

    public FacebookConnector() {
        try {
            fbClient = new DefaultFacebookClient(pageAccessToken);
        } catch (FacebookException ex) {
            ex.printStackTrace(System.err);  
        }
    }

    public void makePost(String message){
        fbClient.publish(pageID + "/feed", FacebookType.class, Parameter.with("message", message));
    }
}