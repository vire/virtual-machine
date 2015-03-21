package com.hackathon.vm.domain.nodes;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Michal Dojcar
 */
public class NodesResult
{
    private _links _links;

    @JsonProperty("_embedded")
    private _embeddedNodes _embedded;

    public _links get_links ()
    {
        return _links;
    }

    public void set_links (_links _links)
    {
        this._links = _links;
    }

    @JsonProperty("_embedded")
    public _embeddedNodes get_embedded ()
    {
        return _embedded;
    }

    @JsonProperty("_embedded")
    public void set_embedded (_embeddedNodes _embedded)
    {
        this._embedded = _embedded;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_links = "+_links+", _embedded = "+_embedded+"]";
    }
}