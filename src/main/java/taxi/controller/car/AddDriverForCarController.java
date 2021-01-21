package taxi.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.service.CarService;
import taxi.service.DriverService;

public class AddDriverForCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/car/addDriverForCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String carId = req.getParameter("car_id");
        String driverId = req.getParameter("driver_id");
        if (!carId.equals("") && !driverId.equals("")) {
            DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
            Driver getDriver = driverService.get(Long.valueOf(driverId));
            CarService carService = (CarService) injector.getInstance(CarService.class);
            Car getCar = carService.get(Long.valueOf(carId));
            carService.addDriverToCar(getDriver, getCar);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Car id and driver id can't be empty.");
            req.getRequestDispatcher("//WEB-INF/views/car/addDriverForCar.jsp").forward(req, resp);
        }
    }
}
