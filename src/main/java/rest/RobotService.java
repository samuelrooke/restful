package rest;

import java.util.List;

import data.Robot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
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
        EntityManager em = EMF.createEntityManager();
        try {
            System.out.println("Received robot speeds: " + robot.getSpeeda() + ", " + robot.getSpeedb());
            em.getTransaction().begin();
            em.persist(robot);
            em.getTransaction().commit();
            return robot;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Robot> readAllRobots() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Robot r", Robot.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Path("/getvalues")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getValues() {
        EntityManager em=EMF.createEntityManager();
        em.getTransaction().begin();
        Query q=em.createQuery("select s from Robot s order by s.id desc").setMaxResults(1);
        List<Robot> list=q.getResultList();
        em.getTransaction().commit();
        Robot Robot=list.get(0);
        return Robot.getId()+"#"+Robot.getSpeeda()+"#"+Robot.getSpeedb();
    }


    @GET
    @Path("/instructions")
    @Produces(MediaType.TEXT_PLAIN)
    public String info() {
        return "Use POST with JSON to control robot speeds.";
    }

    @GET
    @Path("/command")
    @Produces(MediaType.APPLICATION_JSON)
    public Robot getLatestCommand() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Robot r ORDER BY r.id DESC", Robot.class)
                     .setMaxResults(1)
                     .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
