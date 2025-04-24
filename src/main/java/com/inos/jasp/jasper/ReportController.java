package com.inos.jasp.jasper;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final com.inos.jasp.jasper.JasperReportService jasperReportService;

    @GetMapping("/{format}")
    public ResponseEntity<Void> generateReport(
            @PathVariable String format
    ) throws JRException, FileNotFoundException {
        jasperReportService.exportReport(format);
        return ResponseEntity.ok().build();
    }
}