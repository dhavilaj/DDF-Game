package com.ddf.view.views.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.components.image.ImageUpload;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Image;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
@SuppressWarnings("serial")
@CDIView("test")
public class TestViewImpl extends DDFViewBase<TestViewListener> {

	/* static fields */

	/* instance variables */
	//@Inject
	//private MailServicesLocal mailServices;

	@SuppressWarnings("unused")
	private Image image;

	/* constructors */
	@SuppressWarnings({ "javadoc" })
	public TestViewImpl() {
		Button button = new Button("Test email");
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				List<String> emails = new ArrayList<>();
				emails.add("dmantil@hotmail.com");
				Map<String, String> params = new HashMap<>();
				params.put("PASSWORD_LINK", "http://www.google.com");
//				try {
//					mailServices.sendMail(BuildInMailType.PASSWORD_RECOVERY,
//						new Locale("en"), emails, params);
//				} catch (ServiceException e) {
//					throw new RuntimeException(e);
//				}
			}
		});
		addComponent(button);

		button = new Button("Test file navigate with params");
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("param1", "value1");
				map.put("param2", "value2");
				map.put("param3", "value3");

				navigateTo(DDFViewEnum.PASSWORD_RECOVERY, map);
			}
		});
		addComponent(button);

		// Test image upload
		ImageUpload imageUpload = new ImageUpload();
		addComponent(imageUpload);
		registerField("imageUpload", imageUpload.getUrlField());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#enterSocialView(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
		ViewChangeEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#initSocialViewl(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
		ViewChangeEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialViewBase#getTemplateType()
	 */
	@Override
	public TemplateType getTemplateType() {
		return TemplateType.PUBLIC;
	}

	/* Methods */

	/* Getters & Setters */
}
