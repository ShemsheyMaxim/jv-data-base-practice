package taxi.storage;

import java.util.ArrayList;
import java.util.List;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;

public class Storage {
    private static Long manufacturerId = 0L;
    private static Long carId = 0L;
    private static Long driverId = 0L;
    private static final List<Manufacturer> manufacturers = new ArrayList<>();
    private static final List<Car> cars = new ArrayList<>();
    private static final List<Driver> drivers = new ArrayList<>();

    public static void addManufacture(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        Storage.manufacturers.add(manufacturer);
    }

    public static void addCar(Car car) {
        carId++;
        car.setId(carId);
        Storage.cars.add(car);
    }

    public static void addDriver(Driver driver) {
        driverId++;
        driver.setId(driverId);
        Storage.drivers.add(driver);
    }

    public static List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public static List<Car> getCars() {
        return cars;
    }

    public static List<Driver> getDrivers() {
        return drivers;
    }
}
