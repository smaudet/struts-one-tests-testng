package servletunit.struts;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;
import org.apache.struts.util.RequestUtils;
import servletunit.HttpServletRequestSimulator;
import servletunit.ServletConfigSimulator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

//IGNORE THIS COMMENT, KEPT FOR POSSIBLE FUTURE ENHANCEMENT ONLY
//FIXME this block needs to be internalized inside test setup methods
//    HttpServletRequest request;
//    if(this.requestWrapper != null)
//      request = this.requestWrapper;
//    else
//      request = this.request;
//TODO why the double init? - seems to be used to setup the following
// functions before we use them
//    servlet.init(config);
//    servlet.init();
//TODO what does this do?
//    request.setAttribute(Globals.MODULE_KEY,servlet.getModuleConfig());
//FIXME change to addMock - how? mockito already mocks everything...

public class MockableActionServlet extends ActionServlet{

  private ModuleConfig pModuleConfig;

  //TODO shouldn't have to set this up manually at all
  public MockableActionServlet(ServletConfig config) throws ServletException {
    this.init(config);
    this.init();
  }

  @Override
  public void init() throws ServletException {
    super.init();
    pModuleConfig = initModuleConfig("",this.config);
    //Magic side-effect function
    getProcessorForModule(pModuleConfig);
    getServletContext().setAttribute(Globals.MODULE_KEY,pModuleConfig);
  }

//  public ModuleConfig getModuleConfig(){
//    return pModuleConfig;
//  }

  @Override
  protected ModuleConfig initModuleConfig(String prefix, String paths) throws ServletException {
    return super.initModuleConfig(prefix, paths);
  }

  @Override
  protected ModuleConfig getModuleConfig
    (HttpServletRequest request) {

    ModuleConfig config = (ModuleConfig)
      request.getAttribute(Globals.MODULE_KEY);
    if (config == null) {
      ServletContext ctx = getServletContext();
      config = (ModuleConfig) ctx.getAttribute(Globals.MODULE_KEY);
    }
    return (config);

  }

  private RequestProcessor getProcessorForModule(ModuleConfig config) {
    String key = Globals.REQUEST_PROCESSOR_KEY + config.getPrefix();
    return (RequestProcessor) getServletContext().getAttribute(key);
  }

  /**
   * Code identical to the base class, allows for debugging.
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    RequestUtils.selectModule(request, getServletContext());
    RequestProcessor requestProcessor = getRequestProcessor(getModuleConfig(request));
    requestProcessor.process(request, response);
  }

  /**
   * Set the action for a class
   * @param clazzName
   * @param testAction
   * @throws ServletException
   */
  public void setTestAction(String clazzName, Action testAction) throws
    ServletException {
    RequestProcessor processor = getProcessorForModule(pModuleConfig);
    if (processor == null) {
      processor = getRequestProcessor(pModuleConfig);
    }
    try {
      Field actions = Class.forName(RequestProcessor.class.getName()).getDeclaredField("actions");
      if(!actions.isAccessible())
        actions.setAccessible(true);
      HashMap actionMap = (HashMap) actions.get(processor);
      actionMap.put(clazzName,testAction);
    } catch (NoSuchFieldException e) {
      log.error(e);
    } catch (ClassNotFoundException e) {
      log.error(e);
    } catch (IllegalAccessException e) {
      log.error(e);
    } catch (ClassCastException e) {
      log.error(e);
    }
  }
}
