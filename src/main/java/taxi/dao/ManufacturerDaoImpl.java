package taxi.dao;

import java.util.List;
import java.util.Optional;
import taxi.lib.Dao;
import taxi.model.Manufacturer;
import taxi.storage.Storage;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacture(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.getManufacturers().stream()
                .filter(manufacturer -> manufacturer.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.getManufacturers();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.getManufacturers().size(); i++) {
            if (Storage.getManufacturers().get(i).getId().equals(manufacturer.getId())) {
                Storage.getManufacturers().set(i, manufacturer);
                break;
            }
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getManufacturers().removeIf(manufacturer -> manufacturer.getId().equals(id));
    }
}
