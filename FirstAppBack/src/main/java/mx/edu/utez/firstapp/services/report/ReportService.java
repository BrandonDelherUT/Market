package mx.edu.utez.firstapp.services.report;

import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private DataSource dataSource;

   /* public JasperPrint exportReport(long id) throws FileNotFoundException, JRException, SQLException {
        try(Connection conn = dataSource.getConnection()){
            File plantilla = ResourceUtils.getFile("classpath:static/ReporteInsidencia.jrxml");
            File logoUtez = ResourceUtils.getFile("classpath:assets/Logo-utez.png");
            File footer = ResourceUtils.getFile("classpath:assets/footerTerritorio.png");
            File logoRec = ResourceUtils.getFile("classpath:assets/logoRec.jpg");
            JasperReport jasperReport = JasperCompileManager.compileReport( plantilla.getAbsolutePath());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
            parameters.put("sga", new FileInputStream(logoRec));
            parameters.put("utez", new FileInputStream(logoUtez));
            parameters.put("footer", new FileInputStream(footer));
            return JasperFillManager.fillReport(jasperReport, parameters, conn);
        }
    }*/
}
