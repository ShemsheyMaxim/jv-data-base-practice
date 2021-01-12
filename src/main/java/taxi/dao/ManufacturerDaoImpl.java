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
        Storage.getManufacturers().add(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        for (Manufacturer manufacturer : Storage.getManufacturers()) {
            if (manufacturer.getId().equals(id)) {
                return Optional.of(manufacturer);
            }
        }
        return Optional.empty();
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
            }
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        for (Manufacturer manufacturer : Storage.getManufacturers()) {
            if (manufacturer.getId().equals(id)) {
                Storage.getManufacturers().remove(manufacturer);
                return true;
            }
        }
        return false;
    }
}
