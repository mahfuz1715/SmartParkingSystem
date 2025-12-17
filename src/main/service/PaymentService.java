package main.service;

import main.dao.PaymentDao;
import main.model.Ticket;
import main.util.Constants;

public class PaymentService {
    private PaymentDao dao = new PaymentDao();

    public boolean openTicket(Ticket t) {
        return dao.insertTicket(t);
    }

    public boolean closeTicket(Ticket t) {
        double charge = t.calculateCharge(Constants.RATE_PER_HOUR);
        return dao.closeTicket(t.getId(), t.getExitTime(), charge);
    }
}
