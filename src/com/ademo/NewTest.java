package com.ademo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class NewTest {

  @BeforeClass
  public void beforeClass() {
	  System.out.println("----------test start----------");
  }

  @Test
  public void f() {
	  System.out.println("testing");
  }
  
  @AfterClass
  public void afterClass() {
	  System.out.println("----------test end----------");
  }

}
