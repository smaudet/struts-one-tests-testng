package servletunit.struts.tests;

import servletunit.HttpServletResponseSimulator;
import servletunit.struts.MockStrutsTestCase;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: deryl
 * Date: May 20, 2003
 * Time: 5:16:57 PM
 * To change this template use Options | File Templates.
 */
public class TestResponseStatus extends MockStrutsTestCase {

  public void setUp() throws Exception {
    super.setUp();
    setServletConfigFile("/WEB-INF/web.xml");
  }

  public void testResponseCode() throws Exception {
    setRequestPathInfo("/badActionPath");
    try {
      actionPerform();
    } catch (Exception afe) {
      int statusCode = ((HttpServletResponseSimulator) getResponse()).getStatusCode();
      // todo: backwards compatible with struts 1.1
      assertTrue("unexpected response code", statusCode == 404 || statusCode == 400);
      return;
    }
    fail("expected some error code!");

  }


}
