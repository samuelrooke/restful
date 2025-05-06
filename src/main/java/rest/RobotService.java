package rest;

import java.util.List;

import data.Robot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/lego")
public class RobotService {

    private static final EntityManagerFactory EMF =
        Persistence.createEntityManagerFactory("restful");

    @POST
    @Path("/addRobot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Robot updateRobotSpeed(Robot robot) {
        System.out.println("Received robot speeds: " + robot.getSpeeda() + ", " + robot.getSpeedb());
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        em.persist(robot);
        em.getTransaction().commit();
        em.close();
        return robot;
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Robot> readAllPrey() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jagare");
        EntityManager em = emf.createEntityManager();
		List<Robot> list = em.createQuery("SELECT a FROM Prey a", Robot.class).getResultList();
        
        return list;
    }


    @GET
    @Path("/addRobot1")
    @Produces(MediaType.TEXT_PLAIN)
    public String info() {
        return "Use POST with JSON to control robot speeds.";
    }
}
