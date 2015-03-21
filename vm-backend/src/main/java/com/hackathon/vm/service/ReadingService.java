package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.NodesResult;
import com.hackathon.vm.domain.systems.SystemsResult;
import com.hackathon.vm.domain.traffic.TrafficResult;

/**
 * @author Michal Dojcar
 */
public interface ReadingService {

    ActionsResult readActions();

    NodesResult readNodes();

    SystemsResult readSystems();

    SystemsResult readSystemsOfNode(String nodeId);

    TrafficResult readTraffic(int page);

}
