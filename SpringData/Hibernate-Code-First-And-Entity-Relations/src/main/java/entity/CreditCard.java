package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail {

    private String cardType;
    private LocalDate expirationMonth;
    private LocalDate expirationYear;

    public CreditCard() {
    }

    @Column(name = "card_type")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String type) {
        this.cardType = type;
    }

    @Column(name = "expiration_month")
    public LocalDate getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(LocalDate expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Column(name = "expiration_year")
    public LocalDate getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(LocalDate expirationYear) {
        this.expirationYear = expirationYear;
    }
}
