package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import config.Config;
import domain.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static spark.Spark.get;

public class ServerController {

    private static final Logger log = LoggerFactory.getLogger(ServerController.class);
    private static ObjectMapper om = new ObjectMapper();


    public ServerController() {

        // GET - Give me user with this id
        get("/update-primary/:primary/:from", (request, response) -> {
            Integer primary = Integer.parseInt(request.params(":primary"));
            Integer from = Integer.parseInt(request.params(":from"));
            log.info(format("New request from %s, to update primary from %s to %s", from, Config.primary, primary));
            ResponseVO responseVO = new ResponseVO(format("New request from %s, to update primary from %s to %s", from, Config.primary, primary));
            Gson gson = new Gson();
            return gson.toJson(responseVO);
        });
    }
}
