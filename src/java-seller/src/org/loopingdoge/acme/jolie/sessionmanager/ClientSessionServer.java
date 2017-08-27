
package org.loopingdoge.acme.jolie.sessionmanager;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ClientSessionServer", targetNamespace = "org.loopingdoge.acme.jolie.sessionmanager.wsdl")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ClientSessionServer {


    /**
     * 
     * @param message
     * @param sessions
     * @param username
     */
    @WebMethod(action = "getSessions")
    @RequestWrapper(localName = "getSessions", targetNamespace = "org.loopingdoge.acme.jolie.sessionmanager.xsd", className = "org.loopingdoge.acme.jolie.sessionmanager.GetSessions")
    @ResponseWrapper(localName = "getSessionsResponse", targetNamespace = "org.loopingdoge.acme.jolie.sessionmanager.xsd", className = "org.loopingdoge.acme.jolie.sessionmanager.GetSessionsResponse")
    public void getSessions(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "message", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> message,
        @WebParam(name = "sessions", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<List<SessionType>> sessions);

}