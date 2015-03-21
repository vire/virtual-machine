package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.NodesResult;

/**
 * @author Michal Dojcar
 */
public interface ReadingService {

    ActionsResult readActions();

    NodesResult readNodes();
}
