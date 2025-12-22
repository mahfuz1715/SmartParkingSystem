package main.service;

import main.dao.PaymentDao;
import main.model.Ticket;
import main.util.Constants;

interface PricingStrategy {
    double calculate(long hours);
}

class StandardPricing implements PricingStrategy {
    public double calculate(long hours) { return hours * 10.0; }
}

class WeekendPricing implements PricingStrategy {
    public double calculate(long hours) { return hours * 7.0; }
}

public class PaymentService {
    private PaymentDao dao = new PaymentDao();

    public boolean openTicket(Ticket t) {
        return dao.insertTicket(t);
    }

    public boolean closeTicket(Ticket t) {
        double charge = t.calculateCharge(Constants.RATE_PER_HOUR);
        return dao.closeTicket(t.getId(), t.getExitTime(), charge);
    }

    private PricingStrategy strategy;

    public void setPricingStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double processPayment(long hours) {
        return strategy.calculate(hours);
    }
}
