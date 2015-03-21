package com.hackathon.vm.domain.nodes;

/**
 * @author Michal Dojcar
 */
public class _embedded
{
    private Layers[] layers;

    public Layers[] getLayers ()
    {
        return layers;
    }

    public void setLayers (Layers[] layers)
    {
        this.layers = layers;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [layers = "+layers+"]";
    }
}
