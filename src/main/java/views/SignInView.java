package views;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignInView {

    public AppiumDriver driver;
    WebDriverWait wait;
    MyProfileView myProfileView;
    SettingsView settingsView;

    public SignInView(AppiumDriver<WebElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        wait = new WebDriverWait(driver, 60);

        myProfileView=new MyProfileView(driver);
        settingsView =new SettingsView(driver);
    }


    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    public WebElement emailAddress;

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]")
    public WebElement passWord;


    @FindBy(name = "SIGN-IN")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
    public WebElement signInButton;

    @FindBy(name = "fb button")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[3]")
    public WebElement signInWithFacebookButton;


    @FindBy(name = "twitter button")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[4]")
    public WebElement signInWithTwitterButton;


    @FindBy(name = "Guest button")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[6]")
    public WebElement continueAsGuestButton;


    @FindBy(name = "OK")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")
    public WebElement facebookOKButton;


    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]")
    public WebElement errorMessageAlertWindow;

    @iOSFindBy(xpath = " //UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[1]/UIAButton[1]")
    public WebElement errorMessageAlertOKButton;

    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIAScrollView[1]/UIAStaticText[2]")
    public WebElement errorMessageAlertText;


    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[5]")
    public WebElement createAnAccountButton;


    //Forgot password
    @FindBy(name = "FORGOT PASSWORD?")
    @iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")
    public WebElement forgotPasswordButton;


    public void signInEmailAndPassword(String email, String password){

        wait.until(ExpectedConditions.visibilityOf(emailAddress));

        if(email!=""){
            //Enter username
            emailAddress.clear();
            emailAddress.sendKeys(email);
        }

        if (password!=""){
            //Enter password
            passWord.clear();
            passWord.sendKeys(password);
        }

        signInButton.click();

    }

    public void signInWithFacebook() {
        WorkoutsView workoutsView;
        workoutsView=new WorkoutsView(driver);
        wait.until(ExpectedConditions.visibilityOf(signInWithFacebookButton));
        signInWithFacebookButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAWebView[1]/UIAButton[2]")));
        facebookOKButton.click();
        wait.until(ExpectedConditions.visibilityOf(workoutsView.workoutsViewTitle));
    }


    public void signInWithTwitter() {
        wait.until(ExpectedConditions.visibilityOf(signInWithTwitterButton));
        signInWithTwitterButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]"),"Authenticate"));

    }

    public void signInAsGuestUser(){
        wait.until(ExpectedConditions.visibilityOf(continueAsGuestButton));
        continueAsGuestButton.click();
    }

    public String getAlertErrorMessage(){
        return errorMessageAlertText.getText();
    }


    public void navigateToSignUpView(){
        wait.until(ExpectedConditions.visibilityOf(createAnAccountButton));
        createAnAccountButton.click();
    }

    public void navigateToForgotPasswordView(){
        wait.until(ExpectedConditions.visibilityOf(forgotPasswordButton));
        forgotPasswordButton.click();
    }


    public String guestUserSessionExpiration() {
        signInAsGuestUser();
        wait.until(ExpectedConditions.visibilityOf(errorMessageAlertWindow));
        String message = errorMessageAlertText.getText();
        errorMessageAlertOKButton.click();
        return message;
    }


}
