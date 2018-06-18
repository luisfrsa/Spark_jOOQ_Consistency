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

        get("/update-primary/:primary/:from", (request, response) -> {
            Integer primary = Integer.parseInt(request.params(":primary"));
            Integer from = Integer.parseInt(request.params(":from"));
            log.info(format("New request from %s, to update primary from %s to %s", from, Config.primary, primary));
            ResponseVO responseVO = new ResponseVO(format("New request from %s, to update primary from %s to %s", from, Config.primary, primary));
            Gson gson = new Gson();
            return gson.toJson(responseVO);
        });

    }


//    public static void aaa(){
//        options("/*",
//                (request, response) -> {
//
//                    String accessControlRequestHeaders = request
//                            .headers("Access-Control-Request-Headers");
//                    if (accessControlRequestHeaders != null) {
//                        response.header("Access-Control-Allow-Headers",
//                                accessControlRequestHeaders);
//                    }
//
//                    String accessControlRequestMethod = request
//                            .headers("Access-Control-Request-Method");
//                    if (accessControlRequestMethod != null) {
//                        response.header("Access-Control-Allow-Methods",
//                                accessControlRequestMethod);
//                    }
//
//                    return "OK";
//                });
//
//        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
//    }
}
