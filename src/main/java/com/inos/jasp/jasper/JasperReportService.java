package com.inos.jasp.jasper;

import com.inos.jasp.product.ProductMapper;
import com.inos.jasp.product.ProductRepository;
import com.inos.jasp.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JasperReportService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    @Value("${application.file.uploads.jasper-reports-path}")
    String destinationPath;

    public void exportReport(String reportFormat) throws FileNotFoundException, JRException {

        List<ProductResponse> products = repository.findAll()
                .stream()
                .map(mapper::fromProduct)
                .toList();

        // load file and compile it
        File file = ResourceUtils.getFile("classpath:report/ecom.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Inno");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, destinationPath+"/product.html");
        }

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, destinationPath+"/product.pdf");
        }

    }

}