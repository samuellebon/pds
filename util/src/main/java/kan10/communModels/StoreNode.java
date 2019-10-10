package kan10.communModels;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author IliesFaddaoui
 * @version 1.0
 * This is the StoreNode model. It's use to know which stores are next to a store
 * and how far.
 * It will be used by the Dijkstra algorithm logic to calculate the shortest path
 */
public class StoreNode {

    private String StoreName;
    private Map<StoreNode, Float> adjacentNodes = new HashMap<>();
    private LinkedList<StoreNode> shortestPath = new LinkedList<>();
    private Float distance = Float.MAX_VALUE;

    public StoreNode(String storeName, Map<StoreNode, Float> adjacentNodes) {
        StoreName = storeName;
        this.adjacentNodes = adjacentNodes;
    }

    public StoreNode(String storeName) {
        StoreName = storeName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public Map<StoreNode, Float> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<StoreNode, Float> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public LinkedList<StoreNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<StoreNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void addAdjacentStore(StoreNode storeNode, float distance){
        this.adjacentNodes.put(storeNode, distance);
    }
}
