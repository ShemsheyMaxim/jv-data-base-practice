package taxi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxi.dao.CarDao;
import taxi.exception.DataProcessingException;
import taxi.lib.Dao;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import taxi.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (model, manufacturer_id) "
                + "VALUE (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query,
                         Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert car to table " + car, e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.cars_id, c.model AS model, c.manufacturer_id, "
                + "manufacturer_name, country "
                + "FROM cars AS c "
                + "INNER JOIN manufacturers AS m "
                + "ON c.manufacturer_id = m.manufacturer_id "
                + "WHERE c.cars_id = ? AND c.is_deleted = false";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = getCar(resultSet);
            }

        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all cars from table.", e);
        }
        if (car != null) {
            car.setDrivers(getDrivers(car.getId()));
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT c.cars_id, c.model AS model, c.manufacturer_id, "
                + "manufacturer_name, country "
                + "FROM cars AS c "
                + "INNER JOIN manufacturers AS m "
                + "ON c.manufacturer_id = m.manufacturer_id "
                + "WHERE c.is_deleted = false";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(getCar(resultSet));
            }

        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all cars from table.", e);
        }
        for (Car car : cars) {
            car.setDrivers(getDrivers(car.getId()));
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String updateQuery = "UPDATE cars SET model = ?, manufacturer_id = ? "
                + "WHERE cars_id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement updatePreparedStatement =
                         connection.prepareStatement(updateQuery)) {
            updatePreparedStatement.setString(1, car.getModel());
            updatePreparedStatement.setLong(2, car.getManufacturer().getId());
            updatePreparedStatement.setLong(3, car.getId());
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Car for id " + car.getId() + " can't be update.", e);
        }
        deleteCarDrivers(car.getId());
        insertCarDrivers(car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET is_deleted = true WHERE cars_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Deleted car with id " + id
                    + " is failed.", e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT c.cars_id, c.model AS model, c.manufacturer_id, "
                + "manufacturer_name, country FROM cars AS c "
                + "INNER JOIN manufacturers AS m ON c.manufacturer_id = m.manufacturer_id "
                + "INNER JOIN cars_drivers cd ON c.cars_id = cd.car_id "
                + "INNER JOIN drivers d ON cd.driver_id = d.driver_id "
                + "WHERE c.is_deleted = false AND cd.driver_id = ? AND d.is_deleted = false";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(getCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all cars from table.", e);
        }
        for (Car car : cars) {
            car.setDrivers(getDrivers(car.getId()));
        }
        return cars;
    }

    private Manufacturer getManufacturer(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("manufacturer_id", Long.class);
        String name = resultSet.getObject("manufacturer_name", String.class);
        String country = resultSet.getObject("country", String.class);
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("driver_id", Long.class);
        String name = resultSet.getObject("driver_name", String.class);
        String licenceNumber = resultSet.getObject("licence_number", String.class);
        Driver driver = new Driver(name, licenceNumber);
        driver.setId(id);
        return driver;
    }

    private List<Driver> getDrivers(Long carId) {
        String query = "SELECT * FROM cars_drivers cd "
                + "INNER JOIN drivers d ON cd.driver_id = d.driver_id"
                + " WHERE car_id = ? AND d.is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(getDriver(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get drivers for car with id " + carId, e);
        }
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("cars_id", Long.class);
        String model = resultSet.getObject("model", String.class);
        Manufacturer manufacturer = getManufacturer(resultSet);
        Car car = new Car(model, manufacturer);
        car.setId(id);
        return car;
    }

    private void deleteCarDrivers(Long carId) {
        String deleteCarQuery = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement deletePreparedStatement =
                         connection.prepareStatement(deleteCarQuery)) {
            deletePreparedStatement.setLong(1, carId);
            deletePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete car for id " + carId, e);
        }
    }

    private void insertCarDrivers(Car car) {
        String insertCarQuery = "INSERT INTO cars_drivers (car_id, driver_id) "
                + "VALUE (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement insertPreparedStatement =
                         connection.prepareStatement(insertCarQuery)) {
            insertPreparedStatement.setLong(1, car.getId());
            List<Driver> drivers = car.getDrivers();
            for (Driver driver : drivers) {
                insertPreparedStatement.setLong(2, driver.getId());
                insertPreparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert car " + car + " to table.", e);
        }
    }
}
