package taxi.controller.car;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.service.CarService;

public class DisplayAllCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final String DRIVER_ID = "driver_id";
    private final CarService carService =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long driverId = (Long) req.getSession().getAttribute(DRIVER_ID);
        List<Car> allCars = carService.getAllByDriver(driverId);
        req.setAttribute("cars", allCars);
        req.getRequestDispatcher("/WEB-INF/views/car/displayAll.jsp").forward(req, resp);
    }
}
