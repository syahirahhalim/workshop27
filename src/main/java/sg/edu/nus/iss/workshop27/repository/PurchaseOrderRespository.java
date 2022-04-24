package sg.edu.nus.iss.workshop27.repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop27.models.PurchaseOrder;
import static sg.edu.nus.iss.workshop27.repository.SQL.*;

@Repository
public class PurchaseOrderRespository {

    @Autowired
    private JdbcTemplate template;

    public Integer insertPurchaseOrder(final PurchaseOrder po) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(SQL_PURCHASE_ORDER_TOTAL,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, po.getName());
            ps.setString(1, po.getEmail());
            return ps;
        }, keyholder);

        BigInteger bigint = (BigInteger) keyholder.getKey();
        return bigint.intValue();

    }
}
