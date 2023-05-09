package server.servants;

import SmartHome.Sensor;
import SmartHome.WorkingState;
import com.zeroc.Ice.Current;

public class SensorI extends DeviceI implements Sensor {
    private int battery = 75;

    public SensorI(String name) {
        super(name);
    }

    @Override
    public int getChargeState(Current current) {
        System.out.println("Getting sensor (" + current.id.name + " " + current.id.category + ") battery");
        battery -= 1;
        if (battery == 0)
            super.setWorkingState(WorkingState.Off, current);
        return battery;
    }
}
