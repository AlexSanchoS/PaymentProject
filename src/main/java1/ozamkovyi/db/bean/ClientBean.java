package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Client;

import java.util.ResourceBundle;

/**
 * Provide records for:
 * Client list
 *
 *
 * @author O.Zamkovyi
 */

public class ClientBean extends Client {
    private int accountCount;
    private int creditCardCount;


    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }


    public int getCreditCardCount() {
        return creditCardCount;
    }

    public void setCreditCardCount(int creditCardCount) {
        this.creditCardCount = creditCardCount;
    }

    /**
     * Returns content for block/unblock button in jsp
     *
     * @param bundle Current bundle
     * @return content for button
     */

    public String getBlocButton(ResourceBundle bundle) {
        if (status.equals(Fields.CLIENT_STATUS__BLOCK)) {
            return bundle.getString("allUsers_jsp.button.unblock");
        } else {
            return bundle.getString("allUsers_jsp.button.block");
        }
    }





}
