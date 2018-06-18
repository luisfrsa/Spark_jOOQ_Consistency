//package DAO;
//
//
//import org.boon.json.JsonFactory;
//import org.boon.json.ObjectMapper;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Controller {
//
//    private DAO dao;
//
//    ObjectMapper mapper = JsonFactory.create(); // 1
//
//    public Controller(DAO dao) {
//
//        super();
//
//        this.dao = dao;
//
//    }
//
//    public String addPerson(String json) {
//
//        Map<String, Object> data = mapper.readValue(json, Map.class); // 2
//
//        if (dao.addUser(data)) { // 3
//
//            return "{\"message\":\"Added a person!\"}";
//        }
//
//        return "{\"message\":\"Failed to add a person\"}";
//
//    }
//
//}