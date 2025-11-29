package main.service;

import main.dao.PaymentDao;
import main.model.Ticket;
import java.time.Duration;
import java.time.LocalDateTime;

public class PaymentService {
    private PaymentDao dao = new PaymentDao();

    public boolean openTicket(Ticket t) {
        return dao.insertTicket(t);
    }

    public boolean closeTicket(Ticket t) {
        double charge = calculateCharge(t.getEntryTime(), t.getExitTime());
        return dao.closeTicket(t.getId(), t.getExitTime(), charge);
    }

    public double calculateCharge(LocalDateTime in, LocalDateTime out) {
        long hours = Duration.between(in, out).toHours();
        if (hours == 0) hours = 1;
        return hours * 10.0; 
    }
}
