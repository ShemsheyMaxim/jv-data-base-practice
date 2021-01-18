package taxi.dao.impl;

import java.util.List;
import java.util.Optional;
import taxi.dao.DriverDao;
import taxi.model.Driver;
import taxi.storage.Storage;

public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.getDrivers().stream()
                .filter(driver -> driver.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.getDrivers();
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.getDrivers().size(); i++) {
            if (Storage.getDrivers().get(i).getId().equals(driver.getId())) {
                Storage.getDrivers().set(i, driver);
                break;
            }
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getDrivers().removeIf(driver -> driver.getId().equals(id));
    }
}
