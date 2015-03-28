package com.ddf.presenter.views.password;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class RecoveryLinkUtilsTest {

	public RecoveryLinkUtilsTest() {
	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	@Test
	public void testTokenValidationByTimeFail() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String token = RecoveryLinkUtils.generateToken("01022015130000",
			"email@hotmail.com");

		Date nowDate = format.parse("02022015130001"); // + 24h +1s
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));

		nowDate = format.parse("02022015140000"); // + 25h
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));

		nowDate = format.parse("02032015130000"); // + 1 month
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));

		nowDate = format.parse("02032015130000"); // + 1 year
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));
	}

	@Test
	public void testTokenValidationByTimeOk() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String token = RecoveryLinkUtils.generateToken("01022015130000",
			"email@hotmail.com");

		Date nowDate = format.parse("01022015140000"); // + 1h
		Assert.assertTrue(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));

		nowDate = format.parse("02022015120000"); // + 23h
		Assert.assertTrue(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));

		nowDate = format.parse("02022015125959"); // + 23h + 59m + 59s
		Assert.assertTrue(RecoveryLinkUtils.validateToken(token,
			"01022015130000", "email@hotmail.com", nowDate));
	}

	@Test
	public void testTokenValidationFailByEmail() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String token = RecoveryLinkUtils.generateToken("01022015130000",
			"email@hotmail.com");

		Date nowDate = format.parse("01022015140000"); // + 1h
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"02022015140000", "anotheremail@hotmail.com", nowDate));
	}

	@Test
	public void testEmailInUrlEncoded() throws UnsupportedEncodingException {
		String url = RecoveryLinkUtils.buildUrl("email@hotmail.com");
		Assert.assertTrue(url.contains(URLEncoder.encode("email@hotmail.com",
			"UTF-8")));

		Assert.assertFalse(url.contains("@"));
	}

	@Test
	public void testTokenValidationFailByTokenTime() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		String token = RecoveryLinkUtils.generateToken("01022015130000",
			"email@hotmail.com");

		Date nowDate = format.parse("02022015130000"); // + 24h
		Assert.assertFalse(RecoveryLinkUtils.validateToken(token,
			"01022015140000", "email@hotmail.com", nowDate));
	}

	/* Getters & Setters */
}
