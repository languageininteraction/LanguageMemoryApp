package nl.ru.languageininteraction.synaesthesia.registration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("submit")
public class RegistrationResource {

    @GET
    @Produces("text/plain")
    public String submit() {
        return "submitted";
    }
}
