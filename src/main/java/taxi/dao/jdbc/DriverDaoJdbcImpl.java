package taxi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxi.dao.DriverDao;
import taxi.exception.DataProcessingException;
import taxi.lib.Dao;
import taxi.model.Driver;
import taxi.util.ConnectionUtil;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (driver_name, licence_number, login, password) "
                + "VALUES (?,?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, driver.getName());
            prepareStatement.setString(2, driver.getLicenceNumber());
            prepareStatement.setString(3, driver.getLogin());
            prepareStatement.setString(4, driver.getPassword());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert driver to table" + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT * FROM drivers WHERE driver_id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = getDriver(resultSet);
            }
            return Optional.ofNullable(driver);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get driver for id " + id, e);
        }
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                drivers.add(getDriver(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all drivers from table.", e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET driver_name = ?, licence_number = ?, "
                + "login = ?, password = ?"
                + "WHERE driver_id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenceNumber());
            preparedStatement.setString(3, driver.getLogin());
            preparedStatement.setString(4, driver.getPassword());
            preparedStatement.setLong(5, driver.getId());
            preparedStatement.executeUpdate();
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Driver for id " + driver.getId()
                    + " can't be update", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET is_deleted = true WHERE driver_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Deleted driver with id " + id
                    + " is failed.", e);
        }
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Long driverId = resultSet.getObject("driver_id", Long.class);
        String name = resultSet.getObject("driver_name", String.class);
        String licenceNumber = resultSet.getObject("licence_number", String.class);
        String login = resultSet.getObject("login", String.class);
        String password = resultSet.getObject("password", String.class);
        Driver driver = new Driver(name, licenceNumber, login, password);
        driver.setId(driverId);
        return driver;
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        String query = "SELECT * FROM drivers WHERE login = ? AND is_deleted = false";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                driver = getDriver(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Driver with login " + login + " don't find", e);
        }
        return Optional.ofNullable(driver);
    }
}
