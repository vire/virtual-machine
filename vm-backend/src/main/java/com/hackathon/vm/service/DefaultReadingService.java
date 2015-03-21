package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.Layers;
import com.hackathon.vm.domain.nodes.Nodes;
import com.hackathon.vm.domain.nodes.NodesResult;
import com.hackathon.vm.domain.systems.SystemsResult;
import com.hackathon.vm.domain.systems._embedded;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michal Dojcar
 */
@Service
public class DefaultReadingService implements ReadingService {

    private final RestTemplate restTemplate;

    public DefaultReadingService() {
        restTemplate = new RestTemplate();
    }

    @Override
    public ActionsResult readActions() {
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/actions.json", ActionsResult.class);
    }

    @Override
    public NodesResult readNodes() {
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/nodes.json", NodesResult.class);
    }

    @Override
    public SystemsResult readSystems() {
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/systems.json", SystemsResult.class);
    }

    @Override
    public SystemsResult readSystemsOfNode(String nodeId) {
        Nodes node = getNodeById(nodeId);
        return getSystemsBydNode(node);
    }

    private Nodes getNodeById(String nodeId) {
        NodesResult nodesResult = readNodes();
        for (Nodes node : nodesResult.get_embedded().getNodes())
            if (node.getId().equals(nodeId))
                return node;
        throw new IllegalArgumentException("No such node");
    }

    private SystemsResult getSystemsBydNode(Nodes node) {
        Set<String> systemIds = new HashSet<>();
        for (Layers layer : node.get_embedded().getLayers())
            systemIds.add(layer.getSystem_id());

        SystemsResult systemsResult = readSystems();

        List<_embedded> systems = new ArrayList<>();
        for (_embedded system : systemsResult.get_embedded())
            if (systemIds.contains(system.getId()))
                systems.add(system);

        systemsResult.set_embedded(systems.toArray(new _embedded[systems.size()]));
        return systemsResult;
    }
}
