package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


    public class CredentialPage {

        @FindBy(id = "nav-credentials-tab")
        private WebElement credentialsTabField;

        @FindBy(id = "addCredentialButton")
        private WebElement addCredential;

        @FindBy(id = "credential-url")
        private WebElement credentialUrlField;

        @FindBy(id = "table-credentialUrl")
        private WebElement tableCredentialUrl;

        @FindBy(id = "credential-table-body")
        private WebElement credentialsTable;

        @FindBy(id = "credential-username")
        private WebElement credentialUsernameField;

        @FindBy(id = "credential-password")
        private WebElement credentialPasswordField;

        @FindBy(id = "credentialSubmit")
        private WebElement saveCredential;

        @FindBy(id = "editCredentialButton")
        private WebElement editCredential;

        @FindBy(id = "deleteCredentialButton")
        private WebElement deleteCredential;

        private final JavascriptExecutor js;
        private final WebDriverWait wait;

        @FindBy(id = "logoutButton")
        private WebElement logoutButton;

        public CredentialPage(WebDriver webDriver) {
            PageFactory.initElements(webDriver, this);
            wait = new WebDriverWait(webDriver, 1000);
            js = (JavascriptExecutor) webDriver;
        }

        public void openCredentialTab() {
            js.executeScript("arguments[0].click();", credentialsTabField);
        }

        public void openCredentialModal() {
            js.executeScript("arguments[0].click();", addCredential);
        }

        public void createCredential(String url, String username, String password) {
            js.executeScript("arguments[0].value='" + url + "';", credentialUrlField);
            js.executeScript("arguments[0].value='" + username + "';", credentialUsernameField);
            js.executeScript("arguments[0].value='" + password + "';", credentialPasswordField);
        }

        public void userLogout() {
            js.executeScript("arguments[0].click();", logoutButton);
        }
        public void saveCredential() {
            js.executeScript("arguments[0].click();", saveCredential);
        }

        public void editCredential() {
            js.executeScript("arguments[0].click();", editCredential);
        }

        public void deleteCredential() {
            js.executeScript("arguments[0].click();", deleteCredential);
        }

        public String getTableCredentialUrl() {
            return tableCredentialUrl.getAttribute("innerHTML");
        }

        public Boolean hasRows() {
            List<WebElement> credentialsTableElements = credentialsTable.findElements(By.tagName("td"));
            if(credentialsTableElements.size() > 0)
                return true;
            else
                return false;
        }
    }


