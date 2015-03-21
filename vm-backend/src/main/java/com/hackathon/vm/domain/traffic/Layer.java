package com.hackathon.vm.domain.traffic;

/**
 * @author Michal Dojcar
 */
public class Layer
{
    private String current_robustness;

    public String getCurrent_robustness ()
    {
        return current_robustness;
    }

    public void setCurrent_robustness (String current_robustness)
    {
        this.current_robustness = current_robustness;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [current_robustness = "+current_robustness+"]";
    }
}
