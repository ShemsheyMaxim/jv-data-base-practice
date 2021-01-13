package taxi.service;

import java.util.ArrayList;
import java.util.List;
import taxi.dao.CarDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Car;
import taxi.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).orElseThrow(() ->
                new RuntimeException("Id don't found " + id));
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        if (!get(car.getId()).getDrivers().contains(driver)) {
            get(car.getId()).getDrivers().add(driver);
        }
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        get(car.getId()).getDrivers().removeIf(currentDriver ->
                currentDriver.getId().equals(driver.getId()));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> result = new ArrayList<>();
        for (Car car : carDao.getAll()) {
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
