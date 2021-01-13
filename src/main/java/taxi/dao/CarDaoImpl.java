package taxi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxi.lib.Dao;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.storage.Storage;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        return Storage.getCars().stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.getCars();
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.getCars().size(); i++) {
            if (Storage.getCars().get(i).getId().equals(car.getId())) {
                Storage.getCars().set(i, car);
                break;
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getCars().removeIf(car -> car.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> result = new ArrayList<>();
        for (Car car : Storage.getCars()) {
            for (Driver driver : car.getDrivers()) {
                if (driver.getId().equals(driverId)) {
                    result.add(car);
                    break;
                }
            }
        }
        return result;
    }
}
