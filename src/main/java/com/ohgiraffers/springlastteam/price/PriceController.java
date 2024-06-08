package com.ohgiraffers.springlastteam.price;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PriceController {

    String apiurl = "https://www.kamis.or.kr/service/price/xml.do?action=periodProductList";
    String p_cert_key = "848cb63fRH-8ee8-4c1e-b6b0-f9cd7d33d4cf";
    String p_cert_id = "4475";


    @GetMapping("/price")
    public String getPrice(Model model,@RequestParam(value = "itemName", required = false) String itemName) {

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String startDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<PriceDTO> prices = new ArrayList<>();
        Set<String> itemNames = new HashSet<>();

        String[][] itemCodes = {
                {"111", "112", "141", "142", "143", "144", "151", "152", "161", "113", "162", "163", "164", "114"},
                {"211", "212", "279", "280", "213", "214", "215", "216", "221", "222", "223", "224", "225", "226",
                        "231", "232", "233", "241", "242", "243", "244", "258", "259", "245", "246", "247", "248",
                        "251", "252", "253", "254", "255", "256", "257", "261", "262", "263", "264", "265", "422",
                        "217", "218", "266"},
                {"312", "313", "314", "315", "316", "317", "318", "319", "321", "322"},
                {"411", "412", "413", "414", "415", "416", "418", "419", "420", "421", "423", "424", "425", "426",
                        "427", "428", "430", "429"}
        };

        String[] itemCategoryCodes = {"100", "200", "300", "400"};

        for (int i = 0; i < itemCategoryCodes.length; i++) {
            String itemCategoryCode = itemCategoryCodes[i];
            String[] itemCodeArray = itemCodes[i];

            for (String itemCode : itemCodeArray) {
                try {
                    String url = apiurl +
                            "&p_cert_key=" + URLEncoder.encode(p_cert_key, StandardCharsets.UTF_8) +
                            "&p_cert_id=" + URLEncoder.encode(p_cert_id, StandardCharsets.UTF_8) +
                            "&p_returntype=json" +
                            "&p_startday=" + URLEncoder.encode(startDate, StandardCharsets.UTF_8) +
                            "&p_endday=" + URLEncoder.encode(endDate, StandardCharsets.UTF_8) +
                            "&p_convert_kg_yn=Y" +
                            "&p_productclscode=02" +
                            "&p_itemcategorycode=" + itemCategoryCode +
                            "&p_itemcode=" + itemCode;

                    URL requestUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");

                    BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bf.readLine()) != null) {
                        result.append(line);
                    }
                    bf.close();
                    conn.disconnect();

                    JSONObject jsonObject = new JSONObject(result.toString());
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray itemArray = dataObject.getJSONArray("item");

                    for (int j = 0; j < itemArray.length(); j++) {
                        JSONObject item = itemArray.getJSONObject(j);
                        String currentItemName = item.optString("itemname", "");
                        String marketName = item.optString("marketname", "");
                        String price = item.optString("price", "");

                        if (!currentItemName.isEmpty() && !marketName.isEmpty() && !price.isEmpty()) {
                            if (!currentItemName.equals("[]") && !marketName.equals("[]") && !price.equals("[]")) {
                                if (itemName == null || itemName.isEmpty() || currentItemName.contains(itemName)) {
                                    if (!itemNames.contains(currentItemName)) {
                                        PriceDTO priceDto = new PriceDTO(currentItemName, marketName, price);
                                        prices.add(priceDto);
                                        itemNames.add(currentItemName);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        model.addAttribute("prices", prices);

        return "price";
    }
}