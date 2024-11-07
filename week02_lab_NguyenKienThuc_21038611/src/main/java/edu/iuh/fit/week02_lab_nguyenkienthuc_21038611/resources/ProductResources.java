package edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.resources;

import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Employee;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.EmployeeServices;
import edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.services.ProductServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Path("/products")
public class ProductResources {
    private final ProductServices productServices;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductResources() {
        productServices = new ProductServices();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getProduct(@PathParam("id") long eid) {
        Optional<Product> pdOpt = productServices.findById(eid);
        if (pdOpt.isPresent()) {
            return Response.ok(pdOpt.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
