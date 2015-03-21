package com.hackathon.vm.domain.traffic;

/**
 * @author Michal Dojcar
 */
public class Node
{
    private String venue_long;

    private String id;

    private String ip_address;

    private String venue_address;

    private String venue_name;

    private String lft;

    private String rgt;

    private String active_users;

    private String created_at;

    private String node_type;

    private String venue_lat;

    private String parent_id;

    public String getVenue_long ()
    {
        return venue_long;
    }

    public void setVenue_long (String venue_long)
    {
        this.venue_long = venue_long;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIp_address ()
    {
        return ip_address;
    }

    public void setIp_address (String ip_address)
    {
        this.ip_address = ip_address;
    }

    public String getVenue_address ()
    {
        return venue_address;
    }

    public void setVenue_address (String venue_address)
    {
        this.venue_address = venue_address;
    }

    public String getVenue_name ()
    {
        return venue_name;
    }

    public void setVenue_name (String venue_name)
    {
        this.venue_name = venue_name;
    }

    public String getLft ()
    {
        return lft;
    }

    public void setLft (String lft)
    {
        this.lft = lft;
    }

    public String getRgt ()
    {
        return rgt;
    }

    public void setRgt (String rgt)
    {
        this.rgt = rgt;
    }

    public String getActive_users ()
    {
        return active_users;
    }

    public void setActive_users (String active_users)
    {
        this.active_users = active_users;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getNode_type ()
    {
        return node_type;
    }

    public void setNode_type (String node_type)
    {
        this.node_type = node_type;
    }

    public String getVenue_lat ()
    {
        return venue_lat;
    }

    public void setVenue_lat (String venue_lat)
    {
        this.venue_lat = venue_lat;
    }

    public String getParent_id ()
    {
        return parent_id;
    }

    public void setParent_id (String parent_id)
    {
        this.parent_id = parent_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [venue_long = "+venue_long+", id = "+id+", ip_address = "+ip_address+", venue_address = "+venue_address+", venue_name = "+venue_name+", lft = "+lft+", rgt = "+rgt+", active_users = "+active_users+", created_at = "+created_at+", node_type = "+node_type+", venue_lat = "+venue_lat+", parent_id = "+parent_id+"]";
    }
}
