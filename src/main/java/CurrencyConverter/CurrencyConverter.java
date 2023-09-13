package CurrencyConverter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the currency to convert from : ");
        String convertFrom=sc.nextLine();
        System.out.print("Enter the currency to convert to : ");
        String covertTo=sc.nextLine();
        System.out.print("Enter the quantity to convert : ");
        BigDecimal quantity = sc.nextBigDecimal();

        String urlstring= "https://api.exchangerate.host/latest?base=" + convertFrom.toUpperCase();


        OkHttpClient client= new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlstring)
                .get()
                .build();


        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject rateobject = jsonObject.getJSONObject("rates");
        BigDecimal rate = rateobject.getBigDecimal(covertTo.toUpperCase());


        BigDecimal result= rate.multiply(quantity);
        System.out.println("Answer :- " + result);
    }
}
