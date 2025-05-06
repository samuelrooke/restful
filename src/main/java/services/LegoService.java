package services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/lego2")
public class LegoService {

    @GET
    @Path("/LegoService")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLego() {
        return "Lego service";
    }
}
