import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Helper class to store check-in data
    private static class CheckInInfo {
        String stationName;
        int time;

        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    // Helper class to store route metrics (total time & trip count)
    private static class RouteInfo {
        double totalTime;
        int count;

        RouteInfo(double totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    private final Map<Integer, CheckInInfo> checkInMap;
    private final Map<String, RouteInfo> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkIn = checkInMap.remove(id);
        String routeKey = checkIn.stationName + "->" + stationName;
        int duration = t - checkIn.time;

        RouteInfo route = routeMap.computeIfAbsent(routeKey, k -> new RouteInfo(0, 0));
        route.totalTime += duration;
        route.count++;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteInfo route = routeMap.get(routeKey);
        return route.totalTime / route.count;
    }
}
