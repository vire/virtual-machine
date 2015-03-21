package com.hackathon.vm.domain.traffic;

/**
 * @author Michal Dojcar
 */
public class _embedded
{
    private System system;

    private Node node;

    private Layer layer;

    private Action action;

    private Actor actor;

    public System getSystem ()
    {
        return system;
    }

    public void setSystem (System system)
    {
        this.system = system;
    }

    public Node getNode ()
    {
        return node;
    }

    public void setNode (Node node)
    {
        this.node = node;
    }

    public Layer getLayer ()
    {
        return layer;
    }

    public void setLayer (Layer layer)
    {
        this.layer = layer;
    }

    public Action getAction ()
    {
        return action;
    }

    public void setAction (Action action)
    {
        this.action = action;
    }

    public Actor getActor ()
    {
        return actor;
    }

    public void setActor (Actor actor)
    {
        this.actor = actor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [system = "+system+", node = "+node+", layer = "+layer+", action = "+action+", actor = "+actor+"]";
    }
}

