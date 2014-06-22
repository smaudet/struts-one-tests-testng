package servletunit.struts.tests;

import servletunit.HttpServletRequestSimulator;
import servletunit.HttpServletResponseSimulator;
import servletunit.struts.MockStrutsTestCase;

/**
 * Created by IntelliJ IDEA.
 * User: deryl
 * Date: May 21, 2003
 * Time: 2:25:53 PM
 * To change this template use Options | File Templates.
 */
public class TestGetMockObjects extends MockStrutsTestCase {

  public void testGetMockObjects() {
    HttpServletRequestSimulator request = this.getMockRequest();
    request.setUserRole("foo");
    HttpServletResponseSimulator response = this.getMockResponse();
    response.getStatusCode();
  }
}
