package com.exchange.exchange;

import com.exchange.exchange.models.ExchangeModel;
import com.exchange.exchange.models.SmallerExchangeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@Controller
public class ExchangeApplication {
    static RestClient restClient = RestClient.create();
    ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        try {
            model.addAttribute("form", new Form());
            model.addAttribute("currencies", Variables.currencies);
            model.addAttribute("data",
                    objectMapper
                            .readValue(getData("GBP", 100L, "EUR"), ExchangeModel.class)
                            .toSmallerExchangeModel()
            );
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

        return "index";
    }

    @PostMapping("/")
    public String submitForm(@ModelAttribute Form form, Model model) {
        try {
            model.addAttribute("form", form);
            model.addAttribute("currencies", Variables.currencies);
            model.addAttribute("data",
                    objectMapper
                            .readValue(getData(form.getFromCurrency(), form.getFromAmount(), form.getToCurrency()), ExchangeModel.class)
                            .toSmallerExchangeModel()
            );
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return "index";
    }

    public static String getData(String from, Long amount, String to) {
        return restClient
                .get()
                .uri(Variables.baseURL + "amount=" + amount + "&country=GB&fromCurrency=" + from + "&isRecipientAmount=false&toCurrency=" + to)
                .header("Accept-Language", "cs")
                .retrieve()
                .body(String.class);
    }

}

@Data
class Form {
    private String fromCurrency;
    private Long fromAmount;
    private String toCurrency;
}