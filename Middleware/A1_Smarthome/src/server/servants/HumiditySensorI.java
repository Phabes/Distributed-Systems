package server.servants;

import SmartHome.DataRetrievingError;
import SmartHome.HumiditySensor;
import SmartHome.WorkingState;
import com.zeroc.Ice.Current;

public class HumiditySensorI extends SensorI implements HumiditySensor {
    private int humidity;

    public HumiditySensorI(String name, int humidity) {
        super(name);
        this.humidity = humidity;
    }

    @Override
    public int getHumidity(Current current) throws DataRetrievingError {
        System.out.println("Getting humidity (" + current.id.name + " " + current.id.category + ")");
        if (super.getWorkingState(current) != WorkingState.On)
            throw new DataRetrievingError();
        return humidity;
    }
}
