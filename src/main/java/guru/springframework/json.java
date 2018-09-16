package guru.springframework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import guru.springframework.model.Invoice;
import guru.springframework.services.InvoiceServiceImpl;

import java.util.List;

public class json {

    public static void main(String[] args) throws JsonProcessingException {


        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();

        List<Invoice> invoices = invoiceService.getInvoiceList();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(invoices);
        System.out.println(json);

    }
}
