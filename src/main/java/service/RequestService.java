package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import config.Config;
import domain.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestService.class);


    public void broadCastPrimary(Integer primary, List<Integer> serverList) {
        System.out.println(serverList.toString());
        serverList
                .stream()
                .filter(s -> s != Config.id)
                .forEach(s -> request("808" + s, "newprimary", "update-primary/" + primary.toString()));
    }

    public ResponseVO request(String serverPort, String name, String getParam) {
        log.info(format(Util.getLogMessage() + " -- To:%s, Request name: %s, URL: %s", serverPort, name, getParam));
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(format("http://localhost:%s/%s/%s", serverPort, getParam, Config.id));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            Gson gson = new Gson();
            return gson.fromJson(content.toString(), ResponseVO.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
