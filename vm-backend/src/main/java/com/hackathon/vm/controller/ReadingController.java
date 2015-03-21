package com.hackathon.vm.controller;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.NodesResult;
import com.hackathon.vm.domain.systems.SystemsResult;
import com.hackathon.vm.domain.traffic.TrafficResult;
import com.hackathon.vm.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Michal Dojcar
 */
@Controller
@RequestMapping("/read")
public class ReadingController {

    private ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @RequestMapping(value = "actions", method = RequestMethod.GET)
    public @ResponseBody String readActions() {
        ActionsResult actionsResult = readingService.readActions();
        return actionsResult.toString();
    }

    @RequestMapping(value = "nodes", method = RequestMethod.GET)
    public @ResponseBody NodesResult readNodes() {
        return readingService.readNodes();
    }

    @RequestMapping(value = "systems", method = RequestMethod.GET)
    public @ResponseBody SystemsResult readSystems() {
        return readingService.readSystems();
    }

    @RequestMapping(value = "nodes/{nodeId}/systems", method = RequestMethod.GET)
    public @ResponseBody SystemsResult readSystemsOfNode(@PathVariable String nodeId) {
        return readingService.readSystemsOfNode(nodeId);

    }
}
