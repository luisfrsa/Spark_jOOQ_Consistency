import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import config.Config;
import config.CorsFilter;
import controller.ServerController;
import controller.UserController;
import org.slf4j.LoggerFactory;
import service.ConsistencyService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static spark.Spark.port;

public class SDConsistency {


    public static void main(String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);


        Config.id = Integer.parseInt(args[0]);
        Config.port = Integer.parseInt(args[1]);
        Config.numServer = Integer.parseInt(args[2]) + 1;
        Config.primary = Config.id == 0;

        port(Config.port);

        new CorsFilter().apply();


        root.info(format(".:Server {id:%s,port:%s,numServer:%s,primary:%s} Just Started:.", Config.id, Config.port, Config.numServer, Config.primary));
        new UserController();
        new ServerController();
        try {
            setController();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initialize() throws InterruptedException {
        List<Integer> servers = IntStream.range(0, Config.numServer).boxed().collect(Collectors.toList());
        ConsistencyService consistencyService = new ConsistencyService(Config.id, 0, servers);
        Thread.sleep(3000 + 2000 * Config.id);
        System.out.println(servers.toString());
        consistencyService.informNewPrimary();
    }

    private static void setController() {

    }

    private static void setconfig(String[] args) {


    }


}