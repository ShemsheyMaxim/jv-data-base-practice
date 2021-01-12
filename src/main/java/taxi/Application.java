package taxi;

import taxi.lib.Injector;
import taxi.model.Manufacturer;
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
    }
}
