package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() throws InterruptedException {
		if (this.driver != null) {
			Thread.sleep(5000);
			driver.quit();
		}
	}
	public void setUp(){
		String username = "admin";
		String password = "admin";
		driver.get("http://localhost:" + port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("aditya", "tyagi", username, password);
		driver.get("http://localhost:" + port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@Test
	public void getSignupPage() {
		this.driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", this.driver.getTitle());
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthenticatedRedirectTestHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthenticatedRedirectTestRandomPage() {
		driver.get("http://localhost:" + this.port + "/some-page");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	public void createNote(NotePage notePage, String title, String description) {
		notePage.openNoteTabJS();
		notePage.openModalJS();
		notePage.createNoteJS(title, description);
		notePage.saveNoteJS();
	}
	@Test
	public void createNoteTest() {
		setUp();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New Note added successfully!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals("Note title", notePage.getTableNoteTitle());
		notePage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}
	@Test
	public void editNoteTest() {
		setUp();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New Note added successfully!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		notePage.editNoteJS();
		notePage.createNoteJS("Edited note title", "Edited note description");
		notePage.saveNoteJS();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Note updated successfully", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals("Edited note title", notePage.getTableNoteTitle());
		notePage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	@Test
	public void deleteNoteTest() {
		setUp();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New Note added successfully!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		notePage.deleteNoteJS();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Note deleted!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals(false, notePage.hasRows());
		notePage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	public void createCredentials(CredentialPage credentialPage, String url, String username, String password) {
		credentialPage.openCredentialTab();
		credentialPage.openCredentialModal();
		credentialPage.createCredential(url, username, password);
		credentialPage.saveCredential();
	}

	@Test
	public void createCredentialTest() {
		setUp();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredentials(credentialPage, "Crendetial url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New Credentails are  added successfully!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals("Crendetial url", credentialPage.getTableCredentialUrl());
		credentialPage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	@Test
	public void editCredentialTest() {
		setUp();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredentials(credentialPage, "Crendetial url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		credentialPage.editCredential();
		credentialPage.createCredential("Edited crendetial url", "Edited credential username", "Edited credential password");
		credentialPage.saveCredential();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Credential updated successfully", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals("Edited crendetial url", credentialPage.getTableCredentialUrl());
		credentialPage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

	@Test
	public void deleteCredentialTest() {
		setUp();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredentials(credentialPage, "Crendetial url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New Credentails are  added successfully!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		credentialPage.deleteCredential();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Credentials deleted!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals(false, credentialPage.hasRows());
		credentialPage.userLogout();
		Assertions.assertEquals("Login",driver.getTitle());
	}

}
