package com.atmecs.NinjaStore.retryanalyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

	/**
	 * 
	 * Re executing the failed tests depends on the count
	 *
	 */
public class RetryListener  implements IAnnotationTransformer 
{
	public void transform(ITestAnnotation testannotation, @SuppressWarnings("rawtypes") Class testClass, @SuppressWarnings("rawtypes") Constructor testConstructor,
			Method testMethod)
	{
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

		if (retry == null)
		{
			testannotation.setRetryAnalyzer(RetryFailedTests.class);
		}

	}
}
