import Ice
import sys

import SmartHome


def check_device(device):
    if not device:
        raise RuntimeError("Invalid proxy")


if __name__ == "__main__":
    with Ice.initialize(sys.argv) as communicator:
        temp1_base = communicator.propertyToProxy("InsideTemperatureSensor.Proxy")
        inside_temperature_sensor = SmartHome.TemperatureSensorPrx.checkedCast(temp1_base)
        check_device(inside_temperature_sensor)

        temp2_base = communicator.propertyToProxy("OutsideTemperatureSensor.Proxy")
        outside_temperature_sensor = SmartHome.TemperatureSensorPrx.checkedCast(temp2_base)
        check_device(outside_temperature_sensor)

        hum1_base = communicator.propertyToProxy("GardenHumiditySensor.Proxy")
        garden_humidity_sensor = SmartHome.HumiditySensorPrx.checkedCast(hum1_base)
        check_device(garden_humidity_sensor)

        hum2_base = communicator.propertyToProxy("PotHumiditySensor.Proxy")
        pot_humidity_sensor = SmartHome.HumiditySensorPrx.checkedCast(hum2_base)
        check_device(pot_humidity_sensor)

        garden_sprinkler_base = communicator.propertyToProxy("GardenSprinkler2.Proxy")
        garden_sprinkler = SmartHome.SprinklerPrx.checkedCast(garden_sprinkler_base)
        check_device(garden_sprinkler)

        kitchen_light_base = communicator.propertyToProxy("KitchenLight2.Proxy")
        kitchen_light = SmartHome.LightPrx.checkedCast(kitchen_light_base)
        check_device(kitchen_light)

        bedroom_light_base = communicator.propertyToProxy("BedroomLight2.Proxy")
        bedroom_light = SmartHome.LightPrx.checkedCast(bedroom_light_base)
        check_device(bedroom_light)

        connected_devices_all = []

        server_devices_base = communicator.propertyToProxy("ConnectedDevices.Proxy")
        server_devices = SmartHome.ConnectedDevicesPrx.checkedCast(server_devices_base)
        connected_devices_all.append(server_devices)

        server_devices_2_base = communicator.propertyToProxy("ConnectedDevices2.Proxy")
        server_devices_2 = SmartHome.ConnectedDevicesPrx.checkedCast(server_devices_2_base)
        connected_devices_all.append(server_devices)

        while True:
            command = input("> ")
            command_args = command.lower().split(" ")
            if command == "q":
                print(inside_temperature_sensor)
            if command_args[0] == "t1":
                if len(command_args) > 1:
                    if command_args[1] == "get":
                        try:
                            temperature = inside_temperature_sensor.getTemperature()
                            print(f"INSIDE_TEMP: {temperature}")
                        except SmartHome.DataRetrievingError:
                            print("Sensor is not switched on")
                    elif command_args[1] == "state":
                        state = inside_temperature_sensor.getWorkingState()
                        print(f"INSIDE_TEMP_STATE: {state}")
                    elif command_args[1] == "toggle":
                        inside_temperature_sensor.toogleWorkingState()
                        state = inside_temperature_sensor.getWorkingState()
                        print(f"INSIDE_TEMP_STATE: {state}")
                    elif command_args[1] == "battery":
                        battery = inside_temperature_sensor.getChargeState()
                        print(f"INSIDE_TEMP_BATTERY: {battery}")
                    else:
                        print("Unknown command")
                else:
                    print("Need more parameters")
            elif command_args[0] == "h1":
                if len(command_args) > 1:
                    if command_args[1] == "get":
                        try:
                            humidity = garden_humidity_sensor.getHumidity()
                            print(f"GARDEN_HUM: {humidity}")
                        except SmartHome.DataRetrievingError:
                            print("Sensor is not switched on")
                    elif command_args[1] == "state":
                        state = garden_humidity_sensor.getWorkingState()
                        print(f"GARDEN_HUM_STATE: {state}")
                    elif command_args[1] == "toggle":
                        garden_humidity_sensor.toogleWorkingState()
                        state = garden_humidity_sensor.getWorkingState()
                        print(f"GARDEN_HUM_STATE: {state}")
                    elif command_args[1] == "battery":
                        battery = garden_humidity_sensor.getChargeState()
                        print(f"GARDEN_HUM_BATTERY: {battery}")
                    else:
                        print("Unknown command")
                else:
                    print("Need more parameters")
            elif command_args[0] == "sp":
                if len(command_args) > 1:
                    if command_args[1] == "state":
                        state = garden_sprinkler.getWorkingState()
                        print(f"SPRINKLER_STATE: {state}")
                    elif command_args[1] == "toggle":
                        garden_sprinkler.toogleWorkingState()
                        state = garden_sprinkler.getWorkingState()
                        print(f"SPRINKLER_STATE: {state}")
                    elif command_args[1] == "settings":
                        settings = garden_sprinkler.getSettings()
                        print(f"SPRINKLER_SETTINGS: {settings}")
                    elif command_args[1] == "time":
                        bespatteringTime = garden_sprinkler.getBespatteringTime()
                        print(f"SPRINKLER_BESPATTERING_TIME: {bespatteringTime}")
                    elif command_args[1] == "intensity":
                        intensity = garden_sprinkler.getIntensity()
                        print(f"SPRINKLER_INTESITY: {intensity}")
                    elif command_args[1] == "set":
                        if len(command_args) > 3:
                            try:
                                intensity = int(command_args[2])
                                bespatteringTime = float(command_args[3])
                                intensity = SmartHome.Intensity.valueOf(intensity)
                                if intensity is not None:
                                    garden_sprinkler.setSettingsAndWorkingState(
                                        SmartHome.SprinklerSettings(intensity, bespatteringTime),
                                        SmartHome.WorkingState.On)
                                    settings = garden_sprinkler.getSettings()
                                    print(f"SPRINKLER_SETTINGS: {settings}")
                                else:
                                    raise SmartHome.IntesityError()
                            except SmartHome.BespatteringTimeError:
                                print("Bespattering time out of range (should be [1, 10] in minutes)")
                            except SmartHome.IntesityError:
                                print("Intensity out of range (should be {0, 1, 2})")
                            except:
                                print("Something wrong")
                        else:
                            print("Need more parameters")
                    else:
                        print("Unknown command")
                else:
                    print("Need more parameters")
            elif command_args[0] == "l1":
                if len(command_args) > 1:
                    if command_args[1] == "state":
                        workingState = kitchen_light.getWorkingState()
                        print(f"KITCHEN LIGHT STATE: {workingState}")
                    elif command_args[1] == "toggle":
                        kitchen_light.toogleWorkingState()
                        workingState = kitchen_light.getWorkingState()
                        print(f"KITCHEN LIGHT STATE: {workingState}")
                    else:
                        print("Unknown command")
                else:
                    print("Need more parameters")
            elif command == "list":
                for i, connected_devices in enumerate(connected_devices_all):
                    print(f"Server (#{i}) devices")
                    devices = connected_devices.getConnectedDevices()
                    for device in devices:
                        print(device)
            else:
                print("Unknown command")
