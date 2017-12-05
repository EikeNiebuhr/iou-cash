package iou.resources;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class DebtResource {

    public Response createDebt()
    {
        return Response.ok().build();
    }

}
