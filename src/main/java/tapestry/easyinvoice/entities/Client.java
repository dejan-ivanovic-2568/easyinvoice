package tapestry.easyinvoice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Dejan Ivanovic divanovic3d@gmail.com
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "clientId")
    private Integer clientId;

    @Column(name = "clientCompany")
    private String clientCompany;

    @Column(name = "clientTaxId")
    private Integer clientTaxId;

    @Column(name = "clientContact")
    private String clientContact;

    @Column(name = "clientPhone")
    private String clientPhone;

    @Column(name = "clientEmail")
    private String clientEmail;

    @Column(name = "clientWebsite")
    private String clientWebsite;
    
    @Column(name="clientCreationDate")
    private Date clientCreationDate;
    

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Registration registration;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    @Inject
    public Client() {
        this.invoices = new HashSet<>();
    }

    public Client(String clientCompany, String clientEmail) {
        this.clientCompany = clientCompany;
        this.clientEmail = clientEmail;
        this.invoices = new HashSet<>();
    }

    public Client(String clientCompany, String clientContact, String clientPhone, String clientEmail, String clientWebsite) {
        this.clientCompany = clientCompany;
        this.clientContact = clientContact;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientWebsite = clientWebsite;
        this.invoices = new HashSet<>();
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany;
    }

    public Integer getClientTaxId() {
        return clientTaxId;
    }

    public void setClientTaxId(Integer clientTaxId) {
        this.clientTaxId = clientTaxId;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientWebsite() {
        return clientWebsite;
    }

    public void setClientWebsite(String clientWebsite) {
        this.clientWebsite = clientWebsite;
    }

    public Date getClientCreationDate() {
        return clientCreationDate;
    }

    public void setClientCreationDate(Date clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return clientCompany; //To change body of generated methods, choose Tools | Templates.
    }

}
