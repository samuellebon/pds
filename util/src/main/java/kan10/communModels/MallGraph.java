package kan10.communModels;

import java.util.HashSet;
import java.util.Set;

public class MallGraph {

    private Set<StoreNode> storeNodes = new HashSet<>();

    public MallGraph() {
    }

    public MallGraph(Set<StoreNode> storeNodes) {
        this.storeNodes = storeNodes;
    }

    public void addNode(StoreNode storeNode){
        storeNodes.add(storeNode);
    }

    public Set<StoreNode> getStoreNodes() {
        return storeNodes;
    }

    public void setStoreNodes(Set<StoreNode> storeNodes) {
        this.storeNodes = storeNodes;
    }
}
