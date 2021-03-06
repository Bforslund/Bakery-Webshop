package individual.project.resources;


import individual.project.controllers.OrderController;
import individual.project.model.*;
import individual.project.repository.HibernateOrdersRepository;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/orders")
public class OrderResources {
    @Context
    private UriInfo uriInfo;
    public OrderController orderController = new OrderController(new HibernateOrdersRepository());
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        List<Order> OrderList;
        OrderList = orderController.showAllOrders();

        GenericEntity<List<Order>> entity = new GenericEntity<>(OrderList) {  };
        return Response.ok(entity).build();
    }
    @GET
    @PermitAll
    @Path("order/{orderNumber}/orderitems") // Get orderItems from an order
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrderItems(@PathParam("orderNumber") int orderNr) {
        List<OrderItem> OrderItemList;
        OrderItemList = orderController.showAllOrderItems(orderNr);

        GenericEntity<List<OrderItem>> entity = new GenericEntity<>(OrderItemList) {  };
        return Response.ok(entity).build();
    }
    @GET
    @PermitAll
    @Path("order/{id}") // Get one order
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id) {
        Order o = orderController.getOrderById(id);//studentsRepository.get(stNr);
        if (o == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id.").build();
        } else {
            return Response.ok(o).build();
        }
    }
    @GET
    @PermitAll
    @Path("order/{id}/user") // Get one order
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByOrderId(@PathParam("id") int id) {

        User u = orderController.getUserByOrder(id);
        if (u == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid user id.").build();
        } else {
            return Response.ok(u).build();
        }
    }
    @POST //POST at http://localhost:XXXX/items/
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        Order o = orderController.addOrder(order);
        if (o == null){
            String entity =  "Order with ordernumber " + order.getOrderNumber() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {

            return Response.ok(o).build();
        }
    }
    @PUT //PUT at http://localhost:XXXX/orders/id
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(Order order) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (orderController.updateOrder(order)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid id.").build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/orders/3 works
    @RolesAllowed({"ADMIN"})
    @Path("deleteAll")
    public Response deleteAllOrders() {
        orderController.deleteAll();
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }
    @GET
    @Path("user/{id}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrdersOfUser(@PathParam("id") int id) {
        List<Order> OrderList;
        OrderList = orderController.showAllOrdersOfOneUser(id);

        GenericEntity<List<Order>> entity = new GenericEntity<>(OrderList) {  };
        return Response.ok(entity).build();
    }
}

