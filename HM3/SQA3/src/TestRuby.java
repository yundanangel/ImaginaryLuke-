

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestRuby {
static WebDriver driver = new HtmlUnitDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	// User story 1: 
	// As a user 
	// I want tokenize function
	// So I can see the process of tokenize
	
	// Scenario 1:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// Then I should be able to see a button with name "Tokenize"
	@Test
	public void testTokenizeButton() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			boolean result=false;
			for(int i=0;i<web.size();i++){
				String line=web.get(i).getAttribute("value");
				if(line.contains("Tokenize")){
					result=true;
					break;
				}
			}
			assertTrue(result);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 2:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I click the "Tokenize" button
	// Then I should be able to see "Tokenize Operation" at the beginning line of the new page
	@Test
	public void testTokenizePage() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(2).click();
			String fline=driver.findElement(By.tagName("h1")).getText();
			assertTrue(fline.contains("Tokenize Operation"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 3:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I put the text "a + b" in the textbox
	// and I click the "Tokenize" button
	// Then I should be able to see ":on_ident", ":on_sp" and ":on_op" in the result part
	@Test
	public void testTokenizeToken() {
		driver.findElement(By.tagName("textarea")).sendKeys("a + b");
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(2).click();
			String fline=driver.findElement(By.tagName("code")).getText();
			assertTrue(fline.contains(":on_ident"));
			assertTrue(fline.contains(":on_sp"));
			assertTrue(fline.contains(":on_op"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 4:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I put the text "a" in the textbox and type return("\n")
	// and I click the "Tokenize" button
	// Then I should be able to see ":on_nl", "\n" in the result part
	@Test
	public void testTokenizeReturn() {
		driver.findElement(By.tagName("textarea")).sendKeys("a\n");
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(2).click();
			String fline=driver.findElement(By.tagName("code")).getText();
			assertTrue(fline.contains(":on_nl"));
			assertTrue(fline.contains("\n"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// User story 2: 
	// As a user 
	// I want parse function
	// So I can see the process of parse	
	
	// Scenario 5:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// Then I should be able to see a button with name "Parse"
	@Test
	public void testParseButton() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			boolean result=false;
			for(int i=0;i<web.size();i++){
				String line=web.get(i).getAttribute("value");
				if(line.contains("Parse")){
					result=true;
					break;
				}
			}
			assertTrue(result);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 6:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I click the "parse" button
	// Then I should be able to see "Parse Operation" at the beginning line of the new page
	@Test
	public void testParsePage() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(3).click();
			String fline=driver.findElement(By.tagName("h1")).getText();
			assertTrue(fline.contains("Parse Operation"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 7:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I put the text "a + b" in the textbox
	// and then I click the "Parse" button
	// Then I should be able to see "[1, 0]" (represent "a") and "[1, 4]" (represent "b") but no "[1, 1]" or "[1, 3]" which represent " " in the result part
	@Test
	public void testParseItems() {
		driver.findElement(By.tagName("textarea")).sendKeys("a + b");
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(3).click();
			String fline=driver.findElement(By.tagName("code")).getText();
			assertTrue(fline.contains("[1, 0]"));
			assertTrue(fline.contains("[1, 4]"));
			assertFalse(fline.contains("[1, 1]"));
			assertFalse(fline.contains("[1, 3]"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 8:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I click the "Parse" button
	// Then I should see a link named "Back"
	@Test
	public void testParseBack() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(3).click();
			WebElement backLink = driver.findElement(By.linkText("Back"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Failed, the javascript not working in selenium
	// Scenario 9:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// When I put the text "A new cat is here" in the textbox
	// And I click the "Parse" button
	// And I click on the "Back" link
	// Then I should now be returned to the previous page and able to see the textbox and my previous input "A new cat is here"
//	@Test
//	public void testParseReturn() {
//		driver.findElement(By.tagName("textarea")).sendKeys("A new cat is here");
//		try{
//			List<WebElement> web= driver.findElements(By.tagName("input"));
//			web.get(3).click();
//			WebElement backLink = driver.findElement(By.linkText("Back"));
//			backLink.click();
//          *failed at next line*
//			WebElement tb=driver.findElement(By.tagName("textarea"));
//			String fline=tb.getText();
//			assertTrue(fline.contains("A new cat is here"));
//		} catch (NoSuchElementException nseex) {
//			fail();
//		}
//	}
	// User story 3: 
	// As a user 
	// I want compile function
	// So I can see the process of compile and find my problem
	
	// Scenario 10:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// Then I should be able to see a button with name "Compile"
	@Test
	public void testCompileButton() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			boolean result=false;
			for(int i=0;i<web.size();i++){
				String line=web.get(i).getAttribute("value");
				if(line.contains("Compile")){
					result=true;
					break;
				}
			}
			assertTrue(result);
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 11:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// when I click the "Compile" button
	// Then I should be able to see "Compile Operation" at the beginning line of the new page
	@Test
	public void testCompilePage() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(4).click();
			String fline=driver.findElement(By.tagName("h1")).getText();
			assertTrue(fline.contains("Compile Operation"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 12:
	// Given I've gone the "http://lit-bayou-7912.herokuapp.com/" page
	// When I put the text "a + b" in the textbox
	// and I click the "Compile" button
	// Then I should be able to see at least a "opt_plus" in the result
	@Test
	public void testCompileOpt() {
		driver.findElement(By.tagName("textarea")).sendKeys("a + b");
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(4).click();
			String fline=driver.findElement(By.tagName("code")).getText();
			assertTrue(fline.contains("opt_plus"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	// Scenario 13:
	// Given I've gone to the "http://lit-bayou-7912.herokuapp.com/" page
	// when I click the "Compile" button
	// then I should see a link named "Back"
	@Test
	public void testCompileBack() {
		try{
			List<WebElement> web= driver.findElements(By.tagName("input"));
			web.get(4).click();
			WebElement backLink = driver.findElement(By.linkText("Back"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
}
