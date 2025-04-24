package com.inos.jasp.excel;

import com.inos.jasp.product.Product;
import com.inos.jasp.product.Produit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static byte[] generateExcel(List<Produit> produits) {

        try(Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Products");

            // En-tete
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nom", "Description", "Quantite", "En stock"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Contenu

            int rowNum = 1;
            for (Produit p : produits) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getNom());
                row.createCell(2).setCellValue(p.getDescription());
                row.createCell(3).setCellValue(p.getQuantite());

                // En stock : 1 ou 0
                row.createCell(4).setCellValue(p.isEnStock() ? 1 : 0);
            }

            // Après avoir ajouté les données, ajoute la validation pour la colonne "enStock" (colonne 4, indexée à 0)
            DataValidationHelper validationHelper = sheet.getDataValidationHelper();
            DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(new String[]{"0", "1"});

            // Plage de lignes où s’applique la validation (ici de la ligne 2 à 100, à adapter selon ton besoin)
            CellRangeAddressList addressList = new CellRangeAddressList(1, 100, 4, 4); // colonne E (index 4)

            DataValidation validation = validationHelper.createValidation(constraint, addressList);

            // Certaines versions d'Excel ont besoin de cette ligne pour bien valider
            if (validation instanceof org.apache.poi.xssf.usermodel.XSSFDataValidation) {
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
            }

            sheet.addValidationData(validation);

            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
