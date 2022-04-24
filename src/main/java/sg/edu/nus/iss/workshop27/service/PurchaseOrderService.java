package sg.edu.nus.iss.workshop27.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.workshop27.exception.OrderTooLargeException;
import sg.edu.nus.iss.workshop27.models.LineItem;
import sg.edu.nus.iss.workshop27.models.PurchaseOrder;
import sg.edu.nus.iss.workshop27.repository.LineItemRepository;
import sg.edu.nus.iss.workshop27.repository.PurchaseOrderRespository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRespository poRepo;

    @Autowired
    private LineItemRepository lineItemRepo;

    @Transactional(rollbackFor = OrderTooLargeException.class)
    public Integer createPurchaseOrder(final PurchaseOrder po) throws OrderTooLargeException {
        final Integer orderId = poRepo.insertPurchaseOrder(po);
        double totalUnitPrice = 0d;
        for (LineItem li : po.getLineItems()) {
            totalUnitPrice = li.getQuantity() * li.getUnitPrice();
            if (totalUnitPrice > 200) {
                System.out.println(totalUnitPrice);
                OrderTooLargeException ex = new OrderTooLargeException(
                        "Order exceed SGD200: %,.2f".formatted(totalUnitPrice));
                ex.setPo(po);
                throw ex;
            }
        }

        lineItemRepo.addLineItem(orderId, po.getLineItems());
        return orderId;
    }

}
