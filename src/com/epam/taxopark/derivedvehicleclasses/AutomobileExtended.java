package com.epam.taxopark.derivedvehicleclasses;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

/**
 * Created by Yahor_Faliazhynski on 12/6/2015.
 */
public class AutomobileExtended extends Automobile {

    private int autoId;
    private String autoType;

    public int getAutoID() {
        return this.autoId;
    }

    public void setAutoID(int autoId) {
        this.autoId = autoId;
    }

    public String getAutoType() {
        return this.autoType;
    }

    public void setAutoType(String autoType) {
        this.autoType = autoType;
    }

    @Override
    public String vehicleInfo() {
        return "Automobile id: " + this.autoId + "; Automobile type: " + this.autoType + "; " + super.vehicleInfo();
    }

}
