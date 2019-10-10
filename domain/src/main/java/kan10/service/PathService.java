package kan10.service;

import kan10.communModels.MallGraph;
import kan10.communModels.StoreNode;
import kan10.dao.AdjacentStoreRepository;
import kan10.entities.*;
import kan10.interfaces.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author IliesFaddaoui
 * @version 1.0
 * This is the service class for all kind of Path.
 */
@Service
public class PathService {

    @Autowired
    private AdjacentStoreRepository adjacentStoreRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ClientService clientService;

    /**
     * This method recovers client's profile passed in parameter and returns stores's label maches whith his profile
     * @param selectedClient
     * @return stores's label maches whith his profile
     */
    public List<String> getStoresByProfile(Client selectedClient) {
        Set<ProductCategory> clientProductCategories = new HashSet<>();
        List<String> storeHashedToString = new ArrayList<>();
        List<String> storesToVisit = new ArrayList<>();
        List<Store> stores = storeService.getAllStores();
        stores = stores.stream().limit(10).collect(Collectors.toList());

        clientProductCategories.addAll(selectedClient.getMinor().getProductCategories());
        clientProductCategories.addAll(selectedClient.getMajor().getProductCategories());

        for (Store store : stores) {
            storeLoop:
            for (ProductCategory category : store.getProductCategories()) {
                for (ProductCategory clientCategory : clientProductCategories) {
                    if(category.getName().equals(clientCategory.getName())) {
                        storesToVisit.add(store.getLabel());
                        break storeLoop;
                    }
                }
            }

        }
        return storesToVisit;

    }


    public Map<Integer, String> returnCustomerPath(List<String> selectedStore, String startingPoint) {
        MallGraph currentMallGraph = generateMallGraph(storeService.getAllStores());
        List<Store> existingStore = storeService.getAllStores();
        StoreNode startingNode = null;
        if (currentMallGraph != null) {
            for (StoreNode storeNode : currentMallGraph.getStoreNodes()) {
                if (storeNode.getStoreName() != null && storeNode.getStoreName().equalsIgnoreCase(startingPoint)) {
                    startingNode = storeNode;
                }
            }
            if (existingStore != null) {
                List<Store> concernedStores = new ArrayList<>();
                for (Store store : existingStore) {
                    for (String storeName : selectedStore) {
                        if (store.getLabel() != null && store.getLabel().equalsIgnoreCase(storeName)) {
                            concernedStores.add(store);
                        }
                    }
                }
                List<String> toBeVisitedStoreNameList = new ArrayList<>();
                for (Store store : concernedStores) {
                    toBeVisitedStoreNameList.add(store.getLabel());
                }
                Map<Integer, String> optimumPath = generateCustomPath(toBeVisitedStoreNameList, startingNode, currentMallGraph);
                return optimumPath;
            }
        }
        return null;
    }

    private MallGraph generateMallGraph(List<Store> stores) {
        List<AdjacentStore> adjacentStoreList = adjacentStoreRepository.findAll();
        MallGraph mallGraph = new MallGraph();
        for (Store store : stores) {
            StoreNode storeNode = new StoreNode(store.getLabel());
            mallGraph.addNode(storeNode);
        }
        for (StoreNode storeNode : mallGraph.getStoreNodes()) {
            for (StoreNode storeNode1 : mallGraph.getStoreNodes()) {
                for (AdjacentStore adjacentStore : adjacentStoreList) {
                    if (adjacentStore.getConcernedStore().getLabel().equalsIgnoreCase(storeNode.getStoreName())
                            && adjacentStore.getAdjacentStore().getLabel().equalsIgnoreCase(storeNode1.getStoreName())) {
                        storeNode.addAdjacentStore(storeNode1, adjacentStore.getDistance());
                    }
                }
            }
        }
        return mallGraph;
    }

    public Map<Integer, String> generateCustomPath(List<String> toBeVisitedStoreList, StoreNode startingPoint, MallGraph mallGraph) {
        Map<Integer, String> magasinOrder = new HashMap<>();
        List<String> disorderStore = toBeVisitedStoreList;
        int i = 1;
        while (!disorderStore.isEmpty()) {
            MallGraph graph1 = getShortestPathFromSource(mallGraph, startingPoint);
            MallGraph graph2 = new MallGraph();
            for (StoreNode storeNode : graph1.getStoreNodes()) {
                if (disorderStore.contains(storeNode.getStoreName())) {
                    graph2.addNode(storeNode);
                }
            }
            StoreNode closestNode = getClosestStoreNode(graph2.getStoreNodes());
            if (closestNode != null) {
                disorderStore.remove(closestNode.getStoreName());
                magasinOrder.put(i, closestNode.getStoreName());
                graph1.getStoreNodes().remove(closestNode);
                startingPoint = closestNode;
                i++;
            }
        }
        return magasinOrder;
    }

    private MallGraph getShortestPathFromSource(MallGraph mallGraph, StoreNode startingStore) {
        startingStore.setDistance(0f);
        Set<StoreNode> sortedNode = new HashSet<>();
        Set<StoreNode> unsortedNode = new HashSet<>();
        unsortedNode.add(startingStore);
        while (unsortedNode.size() != 0) {
            StoreNode currentNode = getClosestStoreNode(unsortedNode);
            unsortedNode.remove(currentNode);
            for (Map.Entry<StoreNode, Float> adjacentPair : currentNode.getAdjacentNodes().entrySet()) {
                StoreNode adjacentNode = adjacentPair.getKey();
                Float edgeWeight = adjacentPair.getValue();
                if (!sortedNode.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsortedNode.add(adjacentNode);
                }
            }
            sortedNode.add(currentNode);
        }
        return mallGraph;
    }

    private StoreNode getClosestStoreNode(Set<StoreNode> unsortedNodes) {
        StoreNode closestStore = null;
        float lowestDistance = Float.MAX_VALUE;
        for (StoreNode store : unsortedNodes) {
            float nodeDistance = store.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                closestStore = store;
            }
        }
        return closestStore;
    }

    private void calculateMinimumDistance(StoreNode storeNode, Float distance, StoreNode sourceNode) {
        Float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + distance < storeNode.getDistance()) {
            storeNode.setDistance(sourceDistance + distance);
            LinkedList<StoreNode> shortestPath = new LinkedList(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            storeNode.setShortestPath(shortestPath);
        }
    }
}
