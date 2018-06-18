package service;

import java.util.List;
import java.util.Random;

public class ConsistencyService {

    public static Integer id;
    public static Integer primary = 0;
    public static RequestService requestService;
    public static List<Integer> serverList;

    public ConsistencyService() {
    }

    public ConsistencyService(Integer id, Integer primary, List<Integer> serverList) {
        ConsistencyService.id = id;
        ConsistencyService.primary = primary;
        ConsistencyService.requestService = new RequestService();
        ConsistencyService.serverList = serverList;
    }

    public Integer getCurrentPrimary() {
        return primary;
    }

    public void setNewPrimary() {
        primary = serverList.get((new Random()).nextInt(serverList.size()));
        informNewPrimary();
    }

    public void informNewPrimary() {
        requestService.broadCastPrimary(primary, serverList);
    }
}
