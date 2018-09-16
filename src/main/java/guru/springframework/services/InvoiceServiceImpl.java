package guru.springframework.services;

import guru.springframework.model.Invoice;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Invoice> getInvoiceList() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("from Invoice",Invoice.class).getResultList();
    }

    @Override
    public Invoice getInvoice(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Invoice.class,id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.flush();
        Invoice savedInvoice = em.merge(invoice);
        em.getTransaction().commit();
        return savedInvoice;
    }
}
