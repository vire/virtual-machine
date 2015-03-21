package com.hackathon.vm.service;

import com.hackathon.vm.domain.actions.ActionsResult;
import com.hackathon.vm.domain.nodes.NodesResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Michal Dojcar
 */
@Service
public class DefaultReadingService implements ReadingService {

    @Override
    public ActionsResult readActions() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/actions.json", ActionsResult.class);
    }

    @Override
    public NodesResult readNodes() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://csob-hackathon.herokuapp.com:80/api/v1/nodes.json", NodesResult.class);
    }
}
