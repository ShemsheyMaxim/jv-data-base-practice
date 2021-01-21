package taxi.controller.create;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taxi.lib.Injector;
import taxi.model.Manufacturer;
import taxi.service.ManufacturerService;

public class CreateManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("manufacturer_name");
        String country = req.getParameter("country");
        if (!name.equals("") && !country.equals("")) {
            Manufacturer newManufacturer = new Manufacturer(name, country);
            ManufacturerService manufacturerService = (ManufacturerService)
                    injector.getInstance(ManufacturerService.class);
            manufacturerService.create(newManufacturer);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Manufacturer name and country can't be empty.");
            req.getRequestDispatcher("/WEB-INF/views/manufacturers/registration.jsp").forward(req, resp);
        }
    }
}
