/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.easyinvoice.data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tapestry.easyinvoice.entities.Invoice;
import tapestry.easyinvoice.entities.Member;
import tapestry.easyinvoice.entities.Service;
import tapestry.easyinvoice.model.InvoiceStatus;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
public class DashboardIMPL implements DashboardDAO {

    @Inject
    private Session dbs;

    @Override
    public void addInvoice(Invoice invoice) {
        dbs.persist(invoice);
    }

    @Override
    public void addService(Service service) {
        dbs.persist(service);
    }

    @Override
    public boolean checkIfInvoiceExists(String clientCompany, String aNumber) {
        List<Invoice> invoices = dbs.createCriteria(Invoice.class).list();
        for (Invoice invoice : invoices) {
            if (invoice.getClient().getClientCompany().equals(clientCompany) && invoice.getInvoiceNumber().equals(aNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteInvoice(Integer id) {
        Invoice invoice = (Invoice) dbs.createCriteria(Invoice.class).add(Restrictions.eq("invoiceId", id)).uniqueResult();
        dbs.delete(invoice);
    }

    
    
    
    @Override
    public void deleteMember(Integer id) {
        Member member = (Member) dbs.createCriteria(Member.class).add(Restrictions.eq("memberId", id)).uniqueResult();
        dbs.delete(member);
    }

    @Override
    public Member findMemberById(Integer id) {
        return (Member) dbs.createCriteria(Member.class).add(Restrictions.eq("memberId", id)).uniqueResult();
    }

    
    
    
    @Override
    public Member findMemberByUsername(String uName) {
        return (Member) dbs.createCriteria(Member.class).add(Restrictions.eq("memberUsername", uName)).uniqueResult();
    }

    @Override
    public Set<Invoice> getAllInvoices() {
        return new HashSet<>(dbs.createCriteria(Invoice.class).list());
    }

    
    
    @Override
    public List<Member> getAllMembers() {
        return dbs.createCriteria(Member.class).list();
    }

    @Override
    public void updateInvoices() {
       Date today = new Date();
       Set<Invoice> invoices = getAllInvoices();
       for(Invoice invoice:invoices){
           if(invoice.getInvoiceDueDate().before(today)){
               invoice.setInvoiceStatus(InvoiceStatus.Overdue);
           }
       }
    }

    
    
    
    @Override
    public void updateMember(Member member) {
        dbs.merge(member);
    }

    @Override
    public boolean validateMember(String uName, String pWord) {
        Member member = (Member) dbs.createCriteria(Member.class)
                .add(Restrictions.eq("memberUsername", uName))
                .add(Restrictions.eq("memberPassword", pWord))
                .uniqueResult();
        return member != null;
    }

}
