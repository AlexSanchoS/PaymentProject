package ozamkovyi.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTeg extends TagSupport {

    public static int count;
    public static int pageNumber;
    private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

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
