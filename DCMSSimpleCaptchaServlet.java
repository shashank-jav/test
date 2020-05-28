package com.dgh.servlet;

import static nl.captcha.Captcha.NAME;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgh.utility.DCMSCaptchaServletUtil;

import nl.captcha.Captcha;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

/**
 * Generates, displays, and stores in session a 200x50 CAPTCHA image with
 * sheared black text, a solid dark grey background, and a straight, slanted red
 * line through the text.
 * 
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class DCMSSimpleCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_WIDTH = "width";

	protected int width = 67;
	protected int height = 34;

	@Override
	public void init() throws ServletException {
		if (getInitParameter(PARAM_HEIGHT) != null) {
			height = Integer.valueOf(getInitParameter(PARAM_HEIGHT));
		}

		if (getInitParameter(PARAM_WIDTH) != null) {
			width = Integer.valueOf(getInitParameter(PARAM_WIDTH));
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<java.awt.Color> textColors = Arrays.asList(Color.BLACK, Color.BLUE, Color.RED);
		List<java.awt.Font> textFonts = Arrays.asList(new Font("Arial", Font.BOLD, 21),
				new Font("Courier", Font.BOLD, 21));

		// java.awt.Color backgroundColor = Color.ORANGE;

		Captcha captcha = new Captcha.Builder(width, height)
				.addText(new DefaultTextProducer(), new DefaultWordRenderer(textColors, textFonts)).addBackground()
				.gimp().build();

		DCMSCaptchaServletUtil.writeImage(resp, captcha.getImage());
		req.getSession().setAttribute(NAME, captcha);
		req.setAttribute("currentCaptcha", captcha);
		req.getSession().setAttribute("currentCaptcha", captcha);
		System.out.println(captcha.getAnswer());
	}
}
