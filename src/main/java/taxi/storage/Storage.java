package taxi.storage;

import java.util.ArrayList;
import java.util.List;
import taxi.model.Manufacturer;

public class Storage {
    private static Long manufacturerId = 0L;
    private static final List<Manufacturer> manufacturers = new ArrayList<>();

    public static void addManufacture(Manufacturer manufacturer) {
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        Storage.manufacturers.add(manufacturer);
    }

    public static List<Manufacturer> getManufacturers() {
        return manufacturers;
    }
}
