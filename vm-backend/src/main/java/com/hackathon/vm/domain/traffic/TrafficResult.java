package com.hackathon.vm.domain.traffic;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Michal Dojcar
 */
public class TrafficResult
{
    private _links _links;

    @JsonProperty("_embedded")
    private _embeddedEvents _embedded;

    public _links get_links ()
    {
        return _links;
    }

    public void set_links (_links _links)
    {
        this._links = _links;
    }

    @JsonProperty("_embedded")
    public _embeddedEvents get_embedded ()
    {
        return _embedded;
    }

    @JsonProperty("_embedded")
    public void set_embeddedEvents (_embeddedEvents _embedded)
    {
        this._embedded = _embedded;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_links = "+_links+", _embedded = "+_embedded+"]";
    }
}
