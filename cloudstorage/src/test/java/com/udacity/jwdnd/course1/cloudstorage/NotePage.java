package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabField;

    @FindBy(id = "addNoteButton")
    private WebElement addNote;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "table-noteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "notes-table-body")
    private WebElement notesTable;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "noteSubmit")
    private WebElement saveNote;

    @FindBy(id = "editNoteButton")
    private WebElement editNote;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNote;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;
    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 1000);
        js = (JavascriptExecutor) webDriver;
    }
    public void openNoteTabJS() {
        js.executeScript("arguments[0].click();", notesTabField);
    }

    public void openModalJS() {
        js.executeScript("arguments[0].click();", addNote);
    }

    public void createNoteJS(String title, String description) {
        js.executeScript("arguments[0].value='" + title + "';", noteTitleField);
        js.executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
    }

    public void saveNoteJS() {
        js.executeScript("arguments[0].click();", saveNote);
    }

    public void editNoteJS() {
        js.executeScript("arguments[0].click();", editNote);
    }

    public void deleteNoteJS() {
        js.executeScript("arguments[0].click();", deleteNote);
    }

    public String getTableNoteTitle() {
        return tableNoteTitle.getAttribute("innerHTML");
    }

    public void userLogout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    public Boolean hasRows() {
        List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
        if(notesList.size() >0)
            return true;
        else
            return false;
    }
}
