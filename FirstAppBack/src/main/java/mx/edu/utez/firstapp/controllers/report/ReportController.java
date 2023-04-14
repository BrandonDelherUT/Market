package mx.edu.utez.firstapp.controllers.report;

import mx.edu.utez.firstapp.models.category.CategoryRepository;
import mx.edu.utez.firstapp.services.report.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@RestController
@RequestMapping("/api-market/report")
public class ReportController {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ReportService service;
    @GetMapping("/{id}")
    @ResponseBody
    public void generateReport(HttpServletResponse response, @PathVariable long id) throws JRException, IOException, SQLException {
        JasperPrint jasperPrint = service.exportReport(id);
        response.setContentType("application/x-pdf");
        String name;
        response.setHeader("Content-Disposition", "attachment; filename=ReporteIncidencia.pdf");
        OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        response.setStatus(200);
    }
}
