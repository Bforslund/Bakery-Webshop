package individual.project.resources;

import individual.project.model.User;
import individual.project.repository.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/users")
public class UserResources {
    @Context
    private UriInfo uriInfo;
    public static final FakeDataStore fakeDataStore = new FakeDataStore();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@QueryParam("users") String users) {
        List<User> UserList;
        UserList = fakeDataStore.getUserList();

        GenericEntity<List<User>> entity = new GenericEntity<>(UserList) {  };
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        User user = fakeDataStore.getUser(id);//studentsRepository.get(stNr);
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id.").build();
        } else {
            return Response.ok(user).build();
        }
    }
    @POST //POST at http://localhost:XXXX/users/
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        if (!fakeDataStore.addUser(user)){
            String entity =  "user with email " + user.getEmail() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + user.getId(); // url of the created item
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }
    @PUT //Update user from admin
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.updateUser(user)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/items/
    @Path("user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.updateUser(user, id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
        }
    }


    @DELETE //DELETE at http://localhost:XXXX/students/3 works
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        fakeDataStore.deleteUser(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }
}
