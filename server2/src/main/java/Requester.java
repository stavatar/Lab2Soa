import JsonDTO.HumanDTO;
import JsonDTO.UpdateRespDTO;
import jdk.nashorn.internal.runtime.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.ConnectException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Logger
public class Requester {
    private static final String URI_HUMAN
            = "https://localhost:8181/LABSOA1_war/humanBeings";

    private static final String URI_HUMAN1
            = "https://localhost:8181/hello";
    Client client;
    public Requester()  {
         client= SessionFactoryKt.getHttpClient();
               //  ClientBuilder.newClient();
    //S
    }

    //Client client = ClientBuilder.newClient();



    public String updateHuman(Long id_human, String newValue,String nameField){
        log.println("CERTCERTCERTCERTCERTCERTCERT");

        log.println(
                "\n ssl_cert= " + System.getProperty("ssl_cert")+" " +
                "\n ss; psw = " + System.getProperty("ssl_pass").toCharArray()+""
              );
        UpdateRespDTO updateRespDTO =new UpdateRespDTO(id_human,nameField,newValue);
        log.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Response response=null;
        log.println(URI_HUMAN);
        response = client.target(URI_HUMAN).request(MediaType.APPLICATION_JSON).put(Entity.entity(updateRespDTO, "application/json"));



        return "\n Status="+ String.valueOf(response.getStatus())+"" +
                "\n Status INFO" + response.getStatusInfo() +
               "\n ssl_cert= " + System.getProperty("ssl_cert")+" " +
                "\n ss; psw = " + System.getProperty("ssl_pass").toCharArray()+"" +
                "\n fff" + response.readEntity(String.class);
    }

    public String getHuman(Long id){
        Response response=client.target(URI_HUMAN+"/"+id).request().get();
        return  response.readEntity(String.class);
    }
}
