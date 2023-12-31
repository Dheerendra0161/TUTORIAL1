package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;

//Updated Comment - Added more details

public class SearchTest extends Base {
	


	public WebDriver driver;
	
	
	public SearchTest() {
		super();
	}
	
	
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"There is no product that matches the search criteria.","No product message in search results is not displayed");
		
	}
	
	@Test(priority=3 ,dependsOnMethods={"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No product message in search results is not displayed");
		
	}
	
	@Test()
	public void verifySearchWithValidProductAfterLogin() {
		loginPage=homePage.naviageToLoginPage();
		accountPage=loginPage.login("dheeruvish1608@gmail.com","123456");
		searchPage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results");
		searchDetailsPage= searchPage.navigateFirstProduct();
		searchDetailsPage.validateProductDetails();
		searchDetailsPage.addToCart();
		
		
		
	}
}
