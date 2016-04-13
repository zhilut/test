
/**
 * @author Yingxue Mei
 */

import com.alice.bean.CookieBean;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoadIFrameServlet extends HttpServlet {


    public static final String TEMPLATE = "To do save cookieSave into cookie <iframe href=\"http://google.com/loadIframe?uid=USER_ID&domain=WEBSITE\">";
    private static final long serialVersionUID = 1L;

    private CookieDAO cookieDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            ConnectionPool connectionPool = new ConnectionPool(jdbcDriverName, jdbcURL);
            cookieDAO = new CookieDAO(connectionPool, "cookie_table");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // google.com/loadIframe?uid=NIKE_USER_ID&domain=nike.com
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String domain = request.getParameter("domain");

        // handle error

        long id = customizeDecode(Long.parseLong(uid));

        CookieBean bean;
        try {
            bean = cookieDAO.readHid(id, domain);

            if (bean == null) {
                bean = new CookieBean();
                bean.setDomain(domain);
                bean.setHid(id);
                cookieDAO.createAutoIncrement(bean);


            }


            String result = TEMPLATE.replaceAll("USER_ID", String.valueOf(bean.getHid()))
                    .replaceAll("WEBSITE", bean.getDomain());
            response.getWriter().println(result);

        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private long customizeDecode(Long id) {
        return id;
    }

    private String getGoodFromThirdPartyAPI(String id) {
        return "good";
    }
}