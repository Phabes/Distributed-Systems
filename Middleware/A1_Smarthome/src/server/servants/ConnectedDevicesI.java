package server.servants;

import SmartHome.ConnectedDevices;
import com.zeroc.Ice.Current;

import java.util.List;

public class ConnectedDevicesI implements ConnectedDevices {
    public List<String> devices;

    public ConnectedDevicesI(List<String> devices) {
        this.devices = devices;
    }

    @Override
    public String[] getConnectedDevices(Current current) {
        System.out.println("Getting connected devices");
        String[] devicesArray = new String[devices.size()];
        for (int i = 0; i < devices.size(); i++) {
            devicesArray[i] = devices.get(i);
        }
        return devicesArray;
    }
}
