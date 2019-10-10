package kan10.service;

import kan10.communModels.MallGraph;
import kan10.communModels.StoreNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PathServiceTest {

    /**
     * Here we build a simple store graph, with 3 nodes. If we start at point A, it's logical that
     * the generated optimized path will be A -> B -> C. So we test if the generatedPath method returns a
     * good result
     */
    @Test
    public void generatePathShouldBeOk(){
        PathService pathService = new PathService();
        StoreNode storeA = new StoreNode("A");
        StoreNode storeB = new StoreNode("B");
        StoreNode storeC = new StoreNode("C");

        storeA.addAdjacentStore(storeB, 10);
        storeA.addAdjacentStore(storeC, 15);

        storeB.addAdjacentStore(storeC, 5);

        MallGraph graph = new MallGraph();

        graph.addNode(storeA);
        graph.addNode(storeB);
        graph.addNode(storeC);
        List<String> toBeVisited = new ArrayList<>();
        toBeVisited.add("A");
        toBeVisited.add("B");
        toBeVisited.add("C");

        Map<Integer, String> generatedMap= pathService.generateCustomPath(toBeVisited,storeA,graph);
        String firstValue = generatedMap.get(1);
        String secondValue = generatedMap.get(2);
        if(generatedMap.entrySet().size() == 3 && firstValue.equals("A") && secondValue.equalsIgnoreCase("B")){
            assert(true);
        } else {
            assert(false);
        }
    }
}
