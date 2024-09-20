package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.resources;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Order;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.OrderService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
public class OrderResources {

    private final OrderService orderServices = new OrderService();

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getOrder(@PathParam("id") long id) {
        return orderServices.findById(id)
                .map(order -> Response.ok(order).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Produces("application/json")
    public Response getAllOrders() {
        List<Order> orders = orderServices.getAll();
        return Response.ok(orders).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createOrder(Order order) {
        orderServices.insertOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateOrder(@PathParam("id") long id, Order order) {
        order.setId(id); // Assuming order has a method to set its ID
        if (orderServices.updateOrder(order)) {
            return Response.ok(order).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") long id) {
        if (orderServices.deleteOrder(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/stats/daily/{date}")
    @Produces("application/json")
    public Response getOrdersByDate(@PathParam("date") String date) {
        List<Order> orders = orderServices.getOrdersByDate(date);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/stats/range")
    @Produces("application/json")
    public Response getOrdersByDateRange(@QueryParam("start") String startDate, @QueryParam("end") String endDate) {
        List<Order> orders = orderServices.getOrdersByDateRange(startDate, endDate);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/stats/employee/{empId}")
    @Produces("application/json")
    public Response getOrdersByEmployee(@PathParam("empId") long empId, @QueryParam("start") String startDate, @QueryParam("end") String endDate) {
        List<Order> orders = orderServices.getOrdersByEmployee(empId, startDate, endDate);
        return Response.ok(orders).build();
    }
}
