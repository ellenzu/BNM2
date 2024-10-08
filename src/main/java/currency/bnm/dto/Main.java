package currency.bnm.dto;

import currency.PropertyReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thoughtworks.xstream.XStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClients.createDefault();
        String bnmUrl = PropertyReader.getProperty("bnmUrl");
        String bnmDate = PropertyReader.getProperty("bnmDate");
        String fullURL = bnmUrl + bnmDate;
        HttpGet request = new HttpGet(fullURL);
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));

        String fullResponse = "";
        String line = "";

        while ((line = rd.readLine()) != null) {
            fullResponse += line + "\r\n";
        }

        XStream xstream = new XStream();

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{ValCurs.class, Valute.class});

        xstream.processAnnotations(ValCurs.class);
        xstream.processAnnotations(Valute.class);

        xstream.addImplicitCollection(ValCurs.class, "valutes", Valute.class);
        ValCurs valCurs = (ValCurs) xstream.fromXML(fullResponse);

        // Find and print exchange rate for a specific CharCode
        String charCodeToFind = "EUR";
        String exchangeRateMessage = ExchangeRate.findExchangeRate(valCurs, charCodeToFind);
        System.out.println(exchangeRateMessage);


        //Serialization
//        for (Valute valute : valCurs.getValutes()){
//            System.out.println(valute.getName());
//            System.out.println(xstream.toXML(valCurs)); // XML Serialization

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        System.out.println(objectMapper.writeValueAsString(valCurs)); // JSON Serialization
    }
}
