package ozamkovyi.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
/**
 * Custom tag for next page and previous page button
 *
 * @author O.Zamkovyi
 */
public class PaginationTeg extends TagSupport {

    /**
     * Number of possible entries for current page
     */
    public static int count;
    /**
     * Number of current page
     */
    public static int pageNumber;
    private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    /**
     * @return "" if next or previous pages are possible. And "disabled" if next or previous pages are not possible
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (btn.equals("nextPage")) {
                if (count > pageNumber * 10) {
                    out.print("");
                } else {
                    out.print("disabled");
                }
            }else{
                if (pageNumber > 1) {
                    out.print("");
                } else {
                    out.print("disabled");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return SKIP_BODY;
    }
}
