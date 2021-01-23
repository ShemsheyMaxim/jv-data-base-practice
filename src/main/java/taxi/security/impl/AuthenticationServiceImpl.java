package taxi.security.impl;

import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;
import taxi.service.DriverService;
import taxi.security.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driverFromDB = driverService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password."));
        if (driverFromDB.getPassword().equals(password)) {
            return driverFromDB;
        }
        throw new AuthenticationException("Incorrect login or password.");
    }
}
