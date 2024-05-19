package com.example.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class contractgenerator {
    public static String readTemplate(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static String fillTemplate(String template, Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            template = template.replace(entry.getKey(), entry.getValue());
        }
        return template;
    }

    public static void writeContract(String content, String outputPath) throws IOException {
        Files.write(Paths.get(outputPath), content.getBytes());
    }

    public static void generateRentalContract(String proprietor, String tenant, String address, String startDate, String endDate, String monthlyRent) {
        try {
            String locationTemplate = readTemplate("location_template.txt");
            Map<String, String> locationData = new HashMap<>();
            locationData.put("[NOM_PROPRIETAIRE]", proprietor);
            locationData.put("[NOM_LOCATAIRE]", tenant);
            locationData.put("[ADRESSE_BIEN]", address);
            locationData.put("[DATE_DEBUT]", startDate);
            locationData.put("[DATE_FIN]", endDate);
            locationData.put("[LOYER_MENSUEL]", monthlyRent);
            String filledLocationContract = fillTemplate(locationTemplate, locationData);
            writeContract(filledLocationContract, "contrat_location.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateSaleContract(String seller, String buyer, String address, String salePrice) {
        try {
            String saleTemplate = readTemplate("vente_template.txt");
            Map<String, String> saleData = new HashMap<>();
            saleData.put("[NOM_VENDEUR]", seller);
            saleData.put("[NOM_ACHETEUR]", buyer);
            saleData.put("[ADRESSE_BIEN]", address);
            saleData.put("[PRIX_VENTE]", salePrice);
            String filledSaleContract = fillTemplate(saleTemplate, saleData);
            writeContract(filledSaleContract, "contrat_vente.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
