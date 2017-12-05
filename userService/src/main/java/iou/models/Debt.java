package iou.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Debt")
@NamedQuery(name = "Debt.findAll", query = "SELECT debt FROM Debt debt")
public class Debt implements Serializable {

    private static final long serialVersionUID = 5917879569351288576L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "creditor_id", referencedColumnName = "id")
    private Person creditor;
    @ManyToOne(optional = false)
    @JoinColumn(name = "debitor_id", referencedColumnName = "id")
    private Person debitor;
    private double amount;
    private Date date;
    private boolean isPayed = false;

    public Debt(Person creditor, Person debitor, double amount) {
        assert creditor != null && debitor != null && amount > 0;
        this.creditor = creditor;
        this.debitor = debitor;
        this.amount = amount;
        date = new Date(System.currentTimeMillis());
    }

    public void pay() {
        isPayed = true;
    }

	@JsonProperty
    public double getAmount() {
        return amount;
    }

	@JsonProperty
    public Date getDate() {
        return date;
    }

	@JsonProperty
    public boolean isPayed() {
        return isPayed;
    }

	@JsonProperty
    public Person getDebitor() {
        return debitor;
    }

	@JsonProperty
    public void setDebitor(Person debitor) {
        this.debitor = debitor;
    }

	@JsonProperty
    public Person getCreditor() {
        return creditor;
    }

	@JsonProperty
    public void setCreditor(Person creditor) {
        this.creditor = creditor;
    }
}
