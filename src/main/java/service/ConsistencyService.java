package service;

import java.util.List;

public class ConsistencyService {

    public static Integer id;
    public static Integer primary = 0;
    public static RequestService requestService;
    public static List<Integer> serverList;

    public ConsistencyService() {
    }

    public ConsistencyService(Integer id, Integer primary, List<Integer> serverList) {
        this.id = id;
        this.primary = primary;
        this.requestService = new RequestService();
        this.serverList = serverList;
    }

    public Integer getCurrentPrimary() {
        return primary;
    }

    public void setNewPrimary() {
        primary++;
        informNewPrimary();
    }

    public void informNewPrimary() {
        requestService.broadCastPrimary(primary, serverList);
    }
}
