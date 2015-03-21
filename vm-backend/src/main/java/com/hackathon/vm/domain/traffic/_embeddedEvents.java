package com.hackathon.vm.domain.traffic;

/**
 * @author Michal Dojcar
 */
public class _embeddedEvents
{
    private Events[] events;

    public Events[] getEvents ()
    {
        return events;
    }

    public void setEvents (Events[] events)
    {
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [events = "+events+"]";
    }
}
