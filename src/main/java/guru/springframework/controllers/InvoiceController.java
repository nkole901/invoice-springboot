package guru.springframework.controllers;

import guru.springframework.model.Invoice;
import guru.springframework.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

    InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET,produces = "application/json")
    public List<Invoice> getInvoices(Model model){
        return invoiceService.getInvoiceList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    public Invoice getInvoice(@PathVariable Long id,Model model){
        return invoiceService.getInvoice(id);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public Invoice saveInvoice(@RequestBody Invoice invoice){
        return invoiceService.save(invoice);
    }


}
