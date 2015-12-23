package mypackage;
 
import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.UiApplication; 
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager; 
/**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 */
public class MyApp extends UiApplication
{
    /**
     * Entry point for application
     * @param args Command line arguments (not used)
     */ 
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        MyApp theApp = new MyApp();       
        theApp.enterEventDispatcher();
    }
    

    /**
     * Creates a new MyApp object
     */
    public MyApp()
    {        
        // Push a screen onto the UI stack for rendering.
        pushScreen(new MikvahWebView());
    }    
    
}
class MikvahWebView extends MainScreen
{
	BrowserField browserField;
    public MikvahWebView()
    {
    	try{
    		VerticalFieldManager parentVfm = new VerticalFieldManager(); 
    		HorizontalFieldManager mainSite = new HorizontalFieldManager(); 
    		
    	    BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
    	    myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_POINTER);
    	      browserField = new BrowserField(myBrowserFieldConfig);
    	   
    	    mainSite.add(browserField);
    	     
	   	    synchronized (Application.getEventLock()) 
			{
			    UiApplication.getUiApplication().invokeLater(new Runnable() 
			    {
			        public void run() 
			        { 
			        	Status.show("Loading, Please Wait..", Bitmap.getPredefinedBitmap(Bitmap.INFORMATION), 2000); 
			        	browserField.requestContent("https://www.mikvahcalendar.com/m/login.php");
				   		
			        }
			    });
			} 
	   	
	   parentVfm.add(mainSite);
    	    add(parentVfm);
     	
    	}catch(final Exception e){
    		synchronized (Application.getEventLock()) 
			{
			    UiApplication.getUiApplication().invokeLater(new Runnable() 
			    {
			        public void run() 
			        {
			        	Status.show(e.getMessage(), Bitmap.getPredefinedBitmap(Bitmap.INFORMATION), 1000);
 			        	 
			        }
			    });
			} 
    	}
    }
}