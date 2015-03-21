package com.hackathon.vm.domain.systems;

/**
 * @author Michal Dojcar
 */
public class _links
{
    private Self self;

    public Self getSelf ()
    {
        return self;
    }

    public void setSelf (Self self)
    {
        this.self = self;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [self = "+self+"]";
    }
}