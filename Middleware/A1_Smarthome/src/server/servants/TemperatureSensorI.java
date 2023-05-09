package server.servants;

import SmartHome.DataRetrievingError;
import SmartHome.TemperatureSensor;
import SmartHome.WorkingState;
import com.zeroc.Ice.Current;

public class TemperatureSensorI extends SensorI implements TemperatureSensor {
    private double temperature;

    public TemperatureSensorI(String name, double temperature) {
        super(name);
        this.temperature = temperature;
    }

    @Override
    public double getTemperature(Current current) throws DataRetrievingError {
        System.out.println("Getting temperature (" + current.id.name + " " + current.id.category + ")");
        if (super.getWorkingState(current) != WorkingState.On)
            throw new DataRetrievingError();
        return temperature;
    }
}
