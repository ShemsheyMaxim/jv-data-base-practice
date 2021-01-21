package taxi.controller.create;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.ManufacturerService;

public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String model = req.getParameter("model");
        String manufacturerId = req.getParameter("manufacturer_id");
        if (!model.equals("") && !manufacturerId.equals("")) {
            ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
            Manufacturer newManufacturer = manufacturerService.get(Long.valueOf(manufacturerId));
            Car newCar = new Car(model, newManufacturer);
            CarService carService = (CarService) injector.getInstance(CarService.class);
            carService.create(newCar);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Car model, manufacturer name "
                    + "and manufacturer country can't be empty.");
            req.getRequestDispatcher("/WEB-INF/views/cars/registration.jsp").forward(req, resp);
        }
    }
}
