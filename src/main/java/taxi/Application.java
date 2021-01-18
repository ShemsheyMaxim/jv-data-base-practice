package taxi;

import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.DriverService;
import taxi.service.ManufacturerService;

public class Application {
    private static final Injector injector = Injector.getInstance("taxi");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService)
                injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer1 = new Manufacturer("Toyota", "Japan");
        Manufacturer manufacturer2 = new Manufacturer("Hyundai", "South Korea");
        Manufacturer manufacturer3 = new Manufacturer("Ford", "United States");
        Manufacturer manufacturer4 = new Manufacturer("BMW", "Germany");
        Manufacturer manufacturer5 = new Manufacturer("Renault", "France");

        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        manufacturerService.create(manufacturer4);
        manufacturerService.create(manufacturer5);

        System.out.println(manufacturerService.getAll());

        System.out.println(manufacturerService.get(2L));

        Manufacturer updateManufacturer3 = manufacturerService.get(3L);
        System.out.println(updateManufacturer3);
        updateManufacturer3.setName("Fiat");
        System.out.println(updateManufacturer3);
        manufacturerService.update(updateManufacturer3);
        System.out.println(manufacturerService.getAll());

        manufacturerService.delete(1L);
        manufacturerService.delete(12L);
        System.out.println(manufacturerService.delete(1L));
        System.out.println(manufacturerService.delete(12L));
        System.out.println(manufacturerService.getAll());
        System.out.println("-----------------------------------------------------"
                + "----------------------------------------------------------------");

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
        Driver driver1 = new Driver("Maxim", "000-000-000");
        Driver driver2 = new Driver("Edward", "111-111-111");
        Driver driver3 = new Driver("Ivan", "222-222-222");
        Driver driver4 = new Driver("Vlad", "333-333-333");
        Driver driver5 = new Driver("Alex", "444-444-444");

        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);
        driverService.create(driver4);
        driverService.create(driver5);
        System.out.println(driverService.getAll());

        driverService.delete(3L);
        System.out.println(driverService.getAll());

        Driver updateDriver = driverService.get(5L);
        System.out.println(updateDriver);
        updateDriver.setName("John");
        System.out.println(updateDriver);
        driverService.update(updateDriver);
        System.out.println(driverService.getAll());
        System.out.println("-----------------------------------------------------"
                + "----------------------------------------------------------------");

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car1 = new Car("Mustang", manufacturer3);
        Car car2 = new Car("Subaru", manufacturer1);
        Car car3 = new Car("Sonata", manufacturer2);

        carService.create(car1);
        carService.create(car2);
        carService.create(car3);
        System.out.println(carService.getAll());

        carService.delete(2L);
        System.out.println(carService.getAll());

        carService.addDriverToCar(driver1, car1);
        carService.addDriverToCar(driver1, car3);
        carService.addDriverToCar(driver5, car1);
        System.out.println(carService.getAll());

        System.out.println(carService.getAllByDriver(1L));

        carService.removeDriverFromCar(driver5, car1);
        System.out.println(carService.getAll());

        Car updateCar = carService.get(1L);
        updateCar.setModel("GT");
        carService.update(updateCar);
        System.out.println(carService.getAll());
    }
}
