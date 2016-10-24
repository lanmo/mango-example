package com.yn.mango.xml;

import org.simpleframework.xml.Element;

/**
 * Created by yangnan on 16/10/24.
 */
public class TestEnvCofig {

    @Element(name = "fullfilSystem")
    private FullfilSystem fullfilSystem;
    @Element(name = "procurementSystem")
    private ProcurementSystem procurementSystem;

    public FullfilSystem getFullfilSystem() {
        return fullfilSystem;
    }

    public void setFullfilSystem(FullfilSystem fullfilSystem) {
        this.fullfilSystem = fullfilSystem;
    }

    public ProcurementSystem getProcurementSystem() {
        return procurementSystem;
    }

    public void setProcurementSystem(ProcurementSystem procurementSystem) {
        this.procurementSystem = procurementSystem;
    }
}
