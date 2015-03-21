package com.hackathon.vm.domain.common;

import com.hackathon.vm.domain.traffic.Action;
import com.hackathon.vm.domain.traffic.Actor;

/**
 * @author Michal Dojcar
 */
public class Event {

    private String happened_at;

    private String event_id;

    private Action action;

    private Actor actor;

    public Event() {
    }

    public Event(String happened_at, String event_id, Action action, Actor actor) {
        this.happened_at = happened_at;
        this.event_id = event_id;
        this.action = action;
        this.actor = actor;
    }

    public String getHappened_at() {
        return happened_at;
    }

    public void setHappened_at(String happened_at) {
        this.happened_at = happened_at;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
