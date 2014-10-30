package nl.ru.languageininteraction.synaesthesia.registration;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

@Path("submit")
public class RegistrationResource {

    private static final Logger logger = Logger.getLogger(RegistrationResource.class.getName());

    public RegistrationResource() {
        logger.setLevel(Level.ALL);
    }

    @GET
    @Produces("text/plain")
    public String getSubmit() {
        logger.info("getSubmit");
        return "submitted";
    }

    @POST
    @Produces("text/plain")
    public String postSubmit(MultivaluedMap<String, String> formParams) {
        logger.info("postSubmit");
        for (String key : formParams.keySet()) {
            logger.info("key");
            logger.info(key);
            for (String value : formParams.get(key)) {
                logger.info("value");
                logger.info(value);
            }
        }
        return "submitted";
    }
}
