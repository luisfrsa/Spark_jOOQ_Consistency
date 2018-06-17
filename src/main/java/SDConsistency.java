import config.Config;
import controller.ServerController;
import controller.UserController;
import service.ConsistencyService;
import service.RequestService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.port;

public class SDConsistency {

    private static final Logger log = LoggerFactory.getLogger(RequestService.class);


    public static void main(String[] args) {
        Config.id = Integer.parseInt(args[0]);
        Config.port = Integer.parseInt(args[1]);
        Config.numServer = Integer.parseInt(args[2]) + 1;
        Config.primary = Config.id == 0;

        port(Config.port);
        new UserController(new UserService());
        new ServerController();
        List<Integer> servers = IntStream.range(0, Config.numServer).boxed().collect(Collectors.toList());
        ConsistencyService consistencyService = new ConsistencyService(Config.id, 0, servers);
        if (Config.id == 0) {
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(servers.toString());

            consistencyService.informNewPrimary();

        }
    }

}