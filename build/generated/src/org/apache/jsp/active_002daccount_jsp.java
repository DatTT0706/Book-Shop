package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class active_002daccount_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("        <title>Login</title>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap CSS -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\">\n");
      out.write("\n");
      out.write("        <!-- Custom CSS -->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/form.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"assets/css/animation.css\">\n");
      out.write("\n");
      out.write("        <script src=\"https://kit.fontawesome.com/9507fb7f76.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <!-- particles.js container -->\n");
      out.write("        <div id=\"particles-js\"></div>      \n");
      out.write("        <!-- particles.js lib - https://github.com/VincentGarreau/particles.js -->\n");
      out.write("        <script src=\"http://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js\"></script> \n");
      out.write("        <!-- stats.js lib -->\n");
      out.write("        <script src=\"http://threejs.org/examples/js/libs/stats.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"intro\">\n");
      out.write("            <div class=\"middle signin\">\n");
      out.write("                <div class=\"login-panel\">\n");
      out.write("                    <div class=\"logo text-center\">\n");
      out.write("                        <a href=\"Products\">Golden Book Shop</a>\n");
      out.write("                    </div>\n");
      out.write("                    <p class=\"notification\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&nbsp; </p>\n");
      out.write("                    <form action=\"login\" method=\"post\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" name=\"active-code\" class=\"form-control\" placeholder=\"Your Active Code\" >\n");
      out.write("                        </div>                       \n");
      out.write("                        \n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"col\">\n");
      out.write("                                <button type=\"submit\" class=\"btn btn-sm\">\n");
      out.write("                                    <i class=\"fas fa-sign-in-alt mr-1\"></i> Submit\n");
      out.write("                                </button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>                       \n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- jQuery -->\n");
      out.write("        <script src=\"assets/js/jquery/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap Core JavaScript -->\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"></script>\n");
      out.write("        <script src=\"assets/js/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"assets/js/animation.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
