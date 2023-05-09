package server.servants;

import SmartHome.Device;
import SmartHome.WorkingState;
import com.zeroc.Ice.Current;

public class DeviceI implements Device {
    private final String name;
    private WorkingState workingState = WorkingState.On;

    public DeviceI(String name) {
        this.name = name;
    }

    @Override
    public String getName(Current current) {
        return name;
    }

    @Override
    public WorkingState getWorkingState(Current current) {
        System.out.println("Getting device (" + current.id.name + " " + current.id.category + ") working state");
        return workingState;
    }

    @Override
    public void toogleWorkingState(Current current) {
        System.out.println("Changing device (" + current.id.name + " " + current.id.category + ") working state");
        if (workingState == WorkingState.On)
            workingState = WorkingState.Off;
        else
            workingState = WorkingState.On;
    }

    @Override
    public void setWorkingState(WorkingState workingState, Current current) {
        System.out.println("Changing device (" + current.id.name + " " + current.id.category + ") working state");
        this.workingState = workingState;
    }
}
