package com.hackathon.vm.domain.systems;

import com.hackathon.vm.domain.common.Event;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Michal Dojcar
 */
public class _embedded
{
    private String id;

    private String level;

    private String user_capacity;

    private String max_robustness;

    private String name;

    private String created_at;

    private String system_type_name;

    private Event[] events;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLevel ()
    {
        return level;
    }

    public void setLevel (String level)
    {
        this.level = level;
    }

    public String getUser_capacity ()
    {
        return user_capacity;
    }

    public void setUser_capacity (String user_capacity)
    {
        this.user_capacity = user_capacity;
    }

    public String getMax_robustness ()
    {
        return max_robustness;
    }

    public void setMax_robustness (String max_robustness)
    {
        this.max_robustness = max_robustness;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getSystem_type_name ()
    {
        return system_type_name;
    }

    public void setSystem_type_name (String system_type_name)
    {
        this.system_type_name = system_type_name;
    }

    public Event[] getEvents() {
        return events;
    }

    @JsonIgnore
    public void setEvents(Event[] events) {
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", level = "+level+", user_capacity = "+user_capacity+", max_robustness = "+max_robustness+", name = "+name+", created_at = "+created_at+", system_type_name = "+system_type_name+"]";
    }
}