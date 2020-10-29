package ozamkovyi.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom tag for localization button
 *
 * @author O.Zamkovyi
 */

public class LocalizationTag extends TagSupport {

    /**
     * Current locale for user
     */
    public static String locale;
    private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }


    /**
     * @return "disabled" if localization button equals current locale
     * or "" if localization button not equals current locale
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if (btn.equals(locale)) {
                out.print("disabled");
            } else {
                out.print("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
