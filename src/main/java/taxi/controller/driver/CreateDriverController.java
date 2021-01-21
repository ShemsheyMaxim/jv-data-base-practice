package taxi.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

public class CreateDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/driver/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name_driver");
        String licenceNumber = req.getParameter("licence_number");
        if (!name.equals("") && !licenceNumber.equals("")) {
            Driver newDriver = new Driver(name, licenceNumber);
            DriverService driverService = (DriverService) injector.getInstance(DriverService.class);
            driverService.create(newDriver);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your name and licence number can't be empty.");
            req.getRequestDispatcher("/WEB-INF/views/driver/registration.jsp").forward(req, resp);
        }
    }
}
