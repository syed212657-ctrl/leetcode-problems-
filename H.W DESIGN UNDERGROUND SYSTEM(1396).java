import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Helper class to store check-in details for a customer
    private static class CheckInInfo {
        String stationName;
        int time;

        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    // Helper class to accumulate total travel time and trip count for a route
    private static class RouteData {
        double totalTime;
        int tripCount;

        RouteData(double totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }
    }

    // Maps customer id -> CheckInInfo
    private Map<Integer, CheckInInfo> checkInMap;
    // Maps "startStation->endStation" -> RouteData
    private Map<String, RouteData> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        // Retrieve and remove customer's check-in info
        CheckInInfo checkIn = checkInMap.remove(id);
        
        String routeKey = checkIn.stationName + "->" + stationName;
        int travelTime = t - checkIn.time;

        // Update the total time and count for this route
        RouteData route = routeMap.getOrDefault(routeKey, new RouteData(0, 0));
        route.totalTime += travelTime;
        route.tripCount += 1;
        routeMap.put(routeKey, route);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteData route = routeMap.get(routeKey);
        return route.totalTime / route.tripCount;
    }
}
