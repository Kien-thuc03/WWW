package edu.iuh.fit.demo.api;

import edu.iuh.fit.demo.business.BaseProcess;
import edu.iuh.fit.demo.models.Student;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
public class StudentResource {
    @Inject
    private BaseProcess baseProcess;
    @GET
    public Response getAll() {
        Response.ResponseBuilder response = Response.ok();
        response.entity(baseProcess.getAll());
        return response.build();
    }

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") String id) {
        Response.ResponseBuilder response = Response.ok();
        response.entity(baseProcess.getId(id));
        return response.build();
    }

    @POST
    public Response add(Student student) {
        Response.ResponseBuilder response = Response.ok();
        response.entity(baseProcess.persist(student));
        return response.build();
    }
}
