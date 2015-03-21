package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.Layers;
import com.hackathon.vm.domain.nodes.Nodes;
import com.hackathon.vm.domain.nodes.NodesResult;
import com.hackathon.vm.domain.systems.SystemsResult;
import com.hackathon.vm.domain.systems._embedded;
import com.hackathon.vm.domain.traffic.TrafficResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Michal Dojcar
 */
@Service
public class DefaultReadingService implements ReadingService {

    private static final Integer RELEVANT_HOURS = 12;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH'%3A'mm'%3A'ss.SSS");

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

    @Override
    public TrafficResult readTraffic(int page) {
        return restTemplate.getForObject(
                "http://csob-hackathon.herokuapp.com:80/api/v1/traffic.json?page=" + page + "&per_page=50&happened_after=2015-03-21T07%3A00%3A00.000%2B01%3A00",
                TrafficResult.class);
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
