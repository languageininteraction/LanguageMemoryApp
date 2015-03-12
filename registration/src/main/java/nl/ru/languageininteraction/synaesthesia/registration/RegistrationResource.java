package nl.ru.languageininteraction.synaesthesia.registration;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("submit")
public class RegistrationResource {

    private static final Logger logger = Logger.getLogger(RegistrationResource.class.getName());

    private static final String SAMPLE_JSON_RESPONSE = "[{\"scores\":[\n"
            + "    {\"player\":\"John\", \"highscore\":\"149\"}, \n"
            + "    {\"player\":\"Anna\", \"highscore\":\"34\"},\n"
            + "    {\"player\":\"Tim\", \"highscore\":\"14\"},\n"
            + "    {\"player\":\"Tom\", \"highscore\":\"11\"},\n"
            + "    {\"player\":\"Bill the one with a very long name\", \"highscore\":\"3\"}\n"
            + "]}]";

    public RegistrationResource() {
        logger.setLevel(Level.ALL);
    }

    @GET
    @Produces("text/plain")
    public String getSubmit() {
        logger.info("getSubmit");
        return SAMPLE_JSON_RESPONSE;
    }

    @POST
    @Produces("text/html")
    public Response postSubmit(MultivaluedMap<String, String> formParams) {
        StringBuilder stringBuilder = new StringBuilder();
        logger.info("postSubmit");
        stringBuilder.append(SAMPLE_JSON_RESPONSE);
//        stringBuilder.append("postSubmit");
//        for (String key : formParams.keySet()) {
//            logger.info("key");
//            stringBuilder.append("<br>key: ");
//            logger.info(key);
//            stringBuilder.append(key);
//            for (String value : formParams.get(key)) {
//                logger.info("value");
//                stringBuilder.append("<br>value: ");
//                logger.info(value);
//                stringBuilder.append(value);
//            }
//        }
        return Response.ok(stringBuilder.toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}
