package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.common.Event;
import com.hackathon.vm.domain.nodes.Layers;
import com.hackathon.vm.domain.nodes.Nodes;
import com.hackathon.vm.domain.nodes.NodesResult;
import com.hackathon.vm.domain.systems.SystemsResult;
import com.hackathon.vm.domain.systems._embedded;
import com.hackathon.vm.domain.traffic.Events;
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

    private static List<Events> ALL_EVENTS;

    private static final RestTemplate restTemplate=new RestTemplate();

    @Override
    public ActionsResult readActions() {
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/actions.json", ActionsResult.class);
    }

    @Override
    public NodesResult readNodes() {
        if (ALL_EVENTS == null) ALL_EVENTS = getAllEvents();
        List<Events> allEvents = ALL_EVENTS;
        NodesResult nodesResult = restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/nodes.json", NodesResult.class);
        for (Nodes nodes : nodesResult.get_embedded().getNodes())
            updateNode(nodes, allEvents);
        return nodesResult;
    }

    private static List<Events> getAllEvents() {
        List<Events> allEvents = new ArrayList<>();
        for (int i=1; i<15; i++)
            allEvents.addAll(Arrays.asList(readTraffic(i).get_embedded().getEvents()));
        return allEvents;
    }

    private void updateNode(Nodes nodes, List<Events> allEvents) {
        List<Event> nodeEvents = new ArrayList<>();
        for (Events e : allEvents) {
            if (e.get_embedded().getNode() != null && nodes.getId().equals(e.get_embedded().getNode().getId())) {
                Event event = new Event();
                event.setEvent_id(e.getEvent_id());
                event.setHappened_at(e.getHappened_at());
                event.setAction(e.get_embedded().getAction());
                event.setActor(e.get_embedded().getActor());
                nodeEvents.add(event);
            }
        }
        nodes.setEvents(nodeEvents.toArray(new Event[nodeEvents.size()]));
    }

    @Override
    public SystemsResult readSystems() {
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/systems.json", SystemsResult.class);
    }

    @Override
    public SystemsResult readSystemsOfNode(String nodeId) {
        Nodes node = getNodeById(nodeId);

        if (ALL_EVENTS == null) ALL_EVENTS = getAllEvents();
        List<Events> allEvents = ALL_EVENTS;
        SystemsResult systemsResult = getSystemsBydNode(node);
        for (_embedded e : systemsResult.get_embedded())
            updateSystem(e, allEvents);
        return systemsResult;
    }

    private void updateSystem(_embedded embed, List<Events> allEvents) {
        List<Event> systemEvents = new ArrayList<>();
        for (Events e : allEvents) {
            if (e.get_embedded().getSystem() != null && embed.getId().equals(e.get_embedded().getSystem().getId())) {
                Event event = new Event();
                event.setEvent_id(e.getEvent_id());
                event.setHappened_at(e.getHappened_at());
                event.setAction(e.get_embedded().getAction());
                event.setActor(e.get_embedded().getActor());
                systemEvents.add(event);
            }
        }
        embed.setEvents(systemEvents.toArray(new Event[systemEvents.size()]));
    }

    public static TrafficResult readTraffic(int page) {
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
