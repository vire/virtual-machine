package com.hackathon.vm.domain.nodes;

/**
 * @author Michal Dojcar
 */

public class _embeddedNodes
{
    private Nodes[] nodes;

    public Nodes[] getNodes ()
    {
        return nodes;
    }

    public void setNodes (Nodes[] nodes)
    {
        this.nodes = nodes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nodes = "+nodes+"]";
    }
}