package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import iou.api.AssetService;
import iou.api.DebtResponse;
import iou.api.UserService;
import iou.models.Debt;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/assets")
public class AssetResource {

    private UserService userService;
    private AssetService assetService;

    @Inject
    public AssetResource(UserService userService, AssetService assetService)
    {
        this.userService = userService;
        this.assetService = assetService;
    }

    @POST
    @UnitOfWork
    public Response create(Debt asset)
    {
        try {
            assetService.update(asset);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PATCH
    @UnitOfWork
    public Response update(Debt asset)
    {
        try {
            assetService.update(asset);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


    @GET
    @UnitOfWork
    public Set<DebtResponse> list()
    {
        return this.assetService.getAssets(2);
    }

    @DELETE
    @UnitOfWork
    public Response delete(Debt asset)
    {
        try {
            assetService.delete(asset);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
